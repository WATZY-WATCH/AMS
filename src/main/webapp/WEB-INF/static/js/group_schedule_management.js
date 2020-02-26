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
    // 마커 위치를 클릭한 위치로 옮깁니다
    initMarker.setPosition(latlng);
});

function getParentNode(element) {
  return element.parentNode;
}

function getElementIndex(element) {
  return [].indexOf.call(element.parentNode.children, element);
}

function modifySchedule(evt, scheduleId, lat, lon) {
	let el = getParentNode(evt).parentNode;
	let idx = getElementIndex(el) - 1;
	let scheduleDate = document.querySelectorAll(".scheduleDate")[idx].innerText,
		scheduleBeginTime = document.querySelectorAll(".scheduleBeginTime")[idx].innerText,
		scheduleEndTime = document.querySelectorAll(".scheduleEndTime")[idx].innerText,
		locPosition = new kakao.maps.LatLng(lat, lon);
	map.setCenter(locPosition); //일정 장소를 지도의 중심으로 이동 
	initMarker.setPosition(locPosition);
	
	dateInput.value = scheduleDate;
	beginTime.value = scheduleBeginTime;
	endTime.value = scheduleEndTime;
	
	if(scheduleModalWrapper.classList.contains("fade-out")) {
		scheduleModalWrapper.classList.remove("fade-out");
	}
	scheduleModalWrapper.classList.add("fade-in");
}

function submitModify() {
	let el = getParentNode(evt).parentNode;
	let idx = getElementIndex(el) - 1;
	
	let scheduleBegin = dateInput.value + " " + beginTime.value,
		scheduleEnd = dateInput.value + " " + endTime.value;
	
	const xhr = new XMLHttpRequest();
	
	if(beginTime.value >= endTime.value) {
		alert("일정 시작 시간은 종료 시간보다 빨라야 합니다. ");
	}
	const data = {};
	data.groupId = groupId;
	data.position = initMarker.position;
	if(initMarker.building_name !== "") {
		data.building_name = initMarker.building_name;
	} else {
		data.building_name = "NULL";
	}
	data.address = initMarker.address_name;
	data.beginTime = new Date(scheduleBegin).format("yyyy-MM-dd HH:mm");
	data.endTime = new Date(scheduleEnd).format("yyyy-MM-dd HH:mm");
	
	xhr.open("POST", "./modifySchedule");
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(Number(xhr.responseText) >= 1) {
				alert("일정이 성공적으로 수정되었습니다. ");
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

function deleteSchedule(evt, scheduleId) {
	let el = getParentNode(evt).parentNode;
	let idx = getElementIndex(el);
	
	let ret = confirm("일정 삭제 이후 복구가 불가능합니다.\n삭제하시겠습니까?");
	if(ret) {
		const xhr = new XMLHttpRequest();
		
		const data = {scheduleId : scheduleId};
		xhr.open("POST", "./deleteSchedule");
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(Number(xhr.responseText) >= 1) {
					alert("일정이 성공적으로 삭제되었습니다. ");
					if(scheduleModalWrapper.classList.contains("fade-in")) {
						scheduleModalWrapper.classList.remove("fade-in");
				}
				scheduleModalWrapper.classList.add("fade-out");
				el.parentNode.removeChild(el);
				}
			} else {
				alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
			}
		}
	}
}