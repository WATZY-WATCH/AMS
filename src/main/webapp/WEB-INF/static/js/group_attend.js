var dist;

async function init() {
	await getCurrLocation();
	console.log(dist);
}

init();

function attend(groupId, userId, base) {
	if(dist === -1) {
		alert("현재 위치 정보를 사용할 수 없습니다. ");
		return;
	} else if(dist > 1000) {
		alert("출석 가능 지역이 아닙니다. ");
		return;
	}
	
	const xhr = new XMLHttpRequest();
	const data = {groupId: groupId, userId:userId, date: new Date().format("yyyy-MM-dd HH:mm")};
	
	xhr.open("POST", "./attend", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.setRequestHeader("Data-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			let ret = xhr.responseText;
			let retObj = JSON.parse(ret);
			if(retObj.error) alert(retObj.error);
			else {
				alert(retObj.chkTime+"\n"+userId + "님 " + retObj.status + "처리 되었습니다. ");
				const body = document.querySelector("body");
				const form = document.querySelector("form");
				const attendBtn = document.querySelector(".attendBtn");
				form.removeChild(attendBtn);
				let h2 = document.createElement("h2");
				let text = document.createTextNode(retObj.status);
				h2.appendChild(text);
				body.appendChild(h2);
			}
		}
	}
}

let timer = setInterval(function() {
	let now = new Date();
	let baseCmp = new Date(startTime);
	if(parseInt((now- baseCmp) / 60000) > 10) {
		clearInterval(timer);
		const body = document.querySelector("body");
		const form = document.querySelector("form");
		let attendBtn = document.querySelector(".attendBtn");
		if(attendBtn) form.removeChild(attendBtn);
		let h2 = document.createElement("h2");
		let text = document.createTextNode("출석종료 ");
		h2.appendChild(text);
		body.appendChild(h2);
	}
}, 1000);

function getCurrLocation() {
	return new Promise(function(resolve, reject) {
		if (navigator.geolocation) {
			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
				//현재 위치와 거리 계산할 기준점 
				let base = new kakao.maps.LatLng(33.450701, 126.570667);
			    var lat = position.coords.latitude, // 위도
			    	lon = position.coords.longitude; // 경도
			    let currLoc = new kakao.maps.LatLng(lat, lon);
			    base = currLoc;
			    clickLine = new kakao.maps.Polyline({
		            path: [base, currLoc] // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
		        });
			    console.log("기준점: ", base, "현재위치: ", currLoc);
			    console.log("거리 ", Math.round(clickLine.getLength()));
			    dist = Math.round(clickLine.getLength());
			    resolve();
			});
		    
		} else {
			dist = -1;
			reject();
		}
	})
}