const query = window.location.search.substring(1).split("&");
const queryMap = {};
query.forEach(v=> {
	let [key, val] = v.split("=");
	queryMap[key] = val;
})

// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.56608, 126.98031), // 지도의 중심좌표
        level: 5, // 지도의 확대 레벨
        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
    }; 

// 지도를 생성한다 
var map = new kakao.maps.Map(mapContainer, mapOption); 

//장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(); 

//마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

//지도를 클릭한 위치에 표출할 마커입니다
var initMarker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
});

// 지도에 마커를 표시합니다
initMarker.setMap(map);

const groupName = document.querySelector(".groupName");
const scheduleModalWrapper = document.querySelector(".schedule-modal-wrapper");
const scheduleModalContent = document.querySelector(".schedule-modal-content");
const buildingName = document.querySelector(".location-name > .building-name");
const addressName = document.querySelector(".location-name > .address");
var dateInput = document.getElementById("scheduleDate");
var beginTime = document.getElementById("beginTime");
var endTime = document.getElementById("endTime");

scheduleModalWrapper.onclick = function (e) {
	if(e.target === scheduleModalWrapper && e.target != scheduleModalContent) {
		scheduleModalWrapper.classList.add("fade-out");
		scheduleModalWrapper.classList.remove("fade-in");
	}
}

kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;
    const data = {x: latlng.Ga, y: latlng.Ha};
	const xhr = new XMLHttpRequest();
	
	xhr.open("POST", "./locInfo");
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			let message;
			const ret = JSON.parse(xhr.responseText),
				  cnt = ret.meta.total_count,
				  addressInfo = ret.documents[0];
			if(cnt === 0) {
				alert("해당 위치의 장소 정보가 존재하지 않습니다. ");
				buildingName.innerText = "";
				addressName.innerText = "";
				initMarker.building_name = initMarker.address_name = "";
			} else if(addressInfo.road_address === null) {
				initMarker.address_name = addressName.innerText = addressInfo.address.address_name;
				initMarker.building_name = buildingName.innerText = "";
			} else if(addressInfo.road_address.building_name !== "") {
				initMarker.building_name = buildingName.innerText = addressInfo.road_address.building_name;
				initMarker.address_name = addressName.innerText = addressInfo.road_address.address_name; //클릭됐을 때 마커에 해당 위치 저장 
			} else {
				initMarker.address_name = addressName.innerText = addressInfo.road_address.address_name;
				initMarker.building_name = buildingName.innerText = "";
			}
			// 마커 위치를 클릭한 위치로 옮깁니다
			initMarker.setPosition(latlng);
			panTo(latlng);
			
			initMarker.position = data; //클릭한 위치 마커에 저장 
			
		} else {
			alert("위치 정보를 받아오지 못했습니다. ");
		}
	}
});

function panTo(latlng) {
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(latlng.Ha, latlng.Ga);
    
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);            
}  

function getParentNode(element) {
  return element.parentNode;
}

function getElementIndex(element) {
  return [].indexOf.call(element.parentNode.children, element);
}

function modifySchedule() {
	map.setZoomable(true);
	map.setDraggable(true);
	
	dateInput.removeAttribute("readOnly");
	beginTime.removeAttribute("readOnly");
	endTime.removeAttribute("readOnly");
}

function submitModify(groupId, scheduleId) {
	const xhr = new XMLHttpRequest();
	
	let scheduleBegin = dateInput.value + " " + beginTime.value,
		scheduleEnd = dateInput.value + " " + endTime.value;
	console.log(endTime.value);
	if(beginTime.value >= endTime.value) {
		alert("일정 시작 시간은 종료 시간보다 빨라야 합니다. ");
		return;
	}
	const data = {};
	data.groupId = groupId;
	data.scheduleId = scheduleId;
	data.position = initMarker.position;
	if(initMarker.building_name !== "") {
		data.building_name = initMarker.building_name;
	} else {
		data.building_name = "NULL";
	}
	if(initMarker.address_name !== "") {
		data.address = initMarker.address_name;
	} else {
		data.address = "NULL";
	}
	data.beginTime = new Date(scheduleBegin).format("yyyy-MM-dd HH:mm");
	data.endTime = new Date(scheduleEnd).format("yyyy-MM-dd HH:mm");
	
	xhr.open("PUT", "./schedule");
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	console.log(data);
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(Number(xhr.responseText) >= 1) {
				getSchedule(0);
				alert("일정이 성공적으로 수정되었습니다. ");
				dateInput.readOnly = beginTime.readOnly = endTime.readOnly = true;
				map.setDraggable(false);
				map.setZoomable(false);
			}
		} else {
			alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
		}
	}
}

function deleteSchedule(groupId, scheduleId) {
	let ret = confirm("일정 삭제 이후 복구가 불가능합니다.\n삭제하시겠습니까?");
	if(ret) {
		const xhr = new XMLHttpRequest();
		
		const data = {groupId: groupId, scheduleId : scheduleId};
		xhr.open("DELETE", "./schedule");
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(Number(xhr.responseText) >= 1) {
					getSchedule(0);
					alert("일정이 성공적으로 삭제되었습니다. ");
					if(scheduleModalWrapper.classList.contains("fade-in")) {
						scheduleModalWrapper.classList.remove("fade-in");
				}
				scheduleModalWrapper.classList.add("fade-out");
				}
			} else {
				alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
			}
		}
	}
}