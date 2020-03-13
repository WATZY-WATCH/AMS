const scheduleModalWrapper = document.querySelector(".schedule-modal-wrapper");
const scheduleModalContent = document.querySelector(".schedule-modal-content");
const buildingName = document.querySelector(".location-name > .building-name");
const addressName = document.querySelector(".location-name > .address");
var dateInput = document.getElementById("scheduleDate");
var beginTime = document.getElementById("beginTime");
var endTime = document.getElementById("endTime");

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


async function initMap() {
	await setCurrLocation();
	await getCurrLocation();
}

initMap();


//지도를 클릭한 위치에 표출할 마커입니다
var initMarker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
});

// 지도에 마커를 표시합니다
initMarker.setMap(map);

scheduleModalWrapper.onclick = function (e) {
	if(e.target === scheduleModalWrapper && e.target != scheduleModalContent) {
		scheduleModalWrapper.classList.add("fade-out");
		scheduleModalWrapper.classList.remove("fade-in");
	}
}

//지도 중심을 현재 위치로, 현재 위치를 얻을 수 없을 때는 특정 위치로 이동 
function setCurrLocation() {
	return new Promise(function(resolve, reject) {
		//HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
		if (navigator.geolocation) {

			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
			    var lat = position.coords.latitude, // 위도
			        lon = position.coords.longitude; // 경도
			    
			    var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
			    initMarker.setPosition(locPosition);
			    // 지도 중심좌표를 접속위치로 변경합니다
			    map.setCenter(locPosition);  
			    resolve();
			});
		    
		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
		    
		    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
		        message = 'geolocation을 사용할수 없어요..'
		    displayMarker(locPosition, message);
		    reject();
		}
	})
}

function getCurrLocation() {
	return new Promise(function(resolve, reject) {
		//HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
		if (navigator.geolocation) {
			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
				//현재 위치와 거리 계산할 기준점 
				let base = map.getCenter();
			    var lat = position.coords.latitude, // 위도
			    	lon = position.coords.longitude; // 경도
			    let currLoc = new kakao.maps.LatLng(lat, lon);
			    clickLine = new kakao.maps.Polyline({
		            path: [base, currLoc] // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
		        });
			    console.log("기준점: ", base, "현재위치: ", currLoc);
			    console.log("거리 ", Math.round(clickLine.getLength()));
			    resolve();
			});
		    
		} else {
		    alert("현재 위치 정보를 사용할 수 없습니다. ");
		    reject();
		}
		
	})
}


//지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {
    // 마커를 생성합니다
    initMarker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    })
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, initMarker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}   

//지도에 클릭 이벤트를 등록합니다
//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
	 // 클릭한 위도, 경도 정보를 가져옵니다 
	var latlng = mouseEvent.latLng;
	const data = {x: latlng.Ga, y: latlng.Ha};
	const xhr = new XMLHttpRequest();
	
	xhr.open("POST", "/schedule/locInfo");
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
				message = "해당 위치의 장소 정보가 존재하지 않습니다. ";
				initMarker.building_name = initMarker.address_name = "";
			} else if(addressInfo.road_address === null) {
				message = initMarker.address_name = addressInfo.address.address_name;
				initMarker.building_name = "";
			} else if(addressInfo.road_address.building_name !== "") {
				message = initMarker.building_name = addressInfo.road_address.building_name;
				initMarker.address_name = addressInfo.road_address.address_name; //클릭됐을 때 마커에 해당 위치 저장 
			} else {
				message = initMarker.address_name = addressInfo.road_address.address_name;
				initMarker.building_name = "";
			}
			
			// 마커 위치를 클릭한 위치로 옮깁니다
			initMarker.setPosition(latlng);
			panTo(latlng);
			
			initMarker.position = data; //클릭한 위치 마커에 저장 
			//마커 위치에 인포윈도우 출력
			displayInfowindow(initMarker, message);
		} else {
			console.log("위치 정보를 받아오지 못했습니다. ");
		}
	}
	 
//	var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
//	message += '경도는 ' + latlng.getLng() + ' 입니다';
//	 
//	var resultDiv = document.getElementById('clickLatlng'); 
//	resultDiv.innerHTML = message;
});

function searchPlace() {
	const keyword = document.getElementById("inputKeyword").value;
	const options = {};
	options.size = 15;
	options.location = map.getCenter();
	options.SortBy = "DISTANCE";
	options.useMapCenter = true;
	options.useMapBounds = true;
	
	//sort, useMapCenter, useMapBounds 옵션으로 검색옵션 커스터마이징 가능.
	
	if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB, options);
}

//장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    mapObj = document.getElementById('map');
    mapObj.style.width = "calc(100% - 300px)";
    listEl.after(mapObj);
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i) 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
        	
        	marker.index = i;
        	marker.clicekd = false;
        	//검색 결과 항목을 클릭하면 해당 위치를 지도 중심이 되도록 이동 
        	itemEl.index = i;
        	itemEl.onclick = function () {
        		
        		let idx = this.index;
        		let x = places[idx].x, y = places[idx].y;
        		let currPlace = {Ga: x, Ha: y};
        		panTo(currPlace);
        		displayInfowindow(markers[idx], places[idx].place_name);
        	}
        	
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', makerMouseOutEvt);
            
            kakao.maps.event.addListener(marker, 'click', function() {
            	if(marker.clicked) {
            		kakao.maps.event.addListener(marker, 'mouseout', makerMouseOutEvt);
            		marker.clicked = false;
            	} else {
            		kakao.maps.event.removeListener(marker, 'mouseout', makerMouseOutEvt);
            		marker.clicked = true;
            	}
            	let idx = marker.index;
            	let x = places[idx].x, y = places[idx].y;
        		let currPlace = {Ga: x, Ha: y};
        		panTo(currPlace);
        		
        		console.log(marker, initMarker);
        		initMarker = marker;
        		initMarker.position = {x: x, y: y};
        		initMarker.building_name = places[idx].place_name;
        		initMarker.address_name = places[idx].address_name;
            });
            
        })(marker, places[i].place_name);
        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

function makerMouseOutEvt() {
	infowindow.close();
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
	
    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>'
    	+ '<button onclick="saveLocation()">일정 생성하기 </button>';
    
    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

function panTo(latlng) {
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(latlng.Ha, latlng.Ga);
    
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);            
}  

function saveLocation() {
	const xhr = new XMLHttpRequest();
	const data = {};
	
	data.position = initMarker.position;
	data.building_name = initMarker.building_name;
	buildingName.innerText = initMarker.building_name;
	data.address = initMarker.address_name;
	addressName.innerText = initMarker.address_name;
	scheduleDate.value = new Date().format("yyyy-MM-dd");
	beginTime.value = "00:00";
	endTime.value = "23:59";
	
	if(scheduleModalWrapper.classList.contains("fade-out")) {
			scheduleModalWrapper.classList.remove("fade-out");
	}
	scheduleModalWrapper.classList.add("fade-in");
}

function submitSchedule(groupId) {
	let scheduleBegin = dateInput.value + " " + beginTime.value;
	let scheduleEnd = dateInput.value + " " + endTime.value;
	
	const xhr = new XMLHttpRequest();
	
	if(beginTime.value >= endTime.value) {
		alert("일정 시작 시간은 종료 시간보다 빨라야 합니다. ");
		return;
	}
	const data = {};
	data.groupId = groupId;
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
	
	xhr.open("POST", "/schedule");
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(Number(xhr.responseText) >= 1) {
				alert("일정이 성공적으로 등록되었습니다. ");
				if(scheduleModalWrapper.classList.contains("fade-in")) {
					scheduleModalWrapper.classList.remove("fade-in");
			}
			scheduleModalWrapper.classList.add("fade-out");
			}
		} else {
			console.log("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
		}
	}
}