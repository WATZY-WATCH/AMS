const calendarBody = document.querySelector(".calendar-body");
const yearName = document.querySelector(".year-name");
const monthName = document.querySelector(".month-name");

getSchedule(0);

function getSchedule(term) {
	let groupId = new URLSearchParams(location.search).get("groupId"),
		year = Number(yearName.innerText);
		month = Number(monthName.innerText);
	
	const data = {year:year, month: month, term: term}
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "./calendar/"+groupId);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = async function () {
		if(xhr.status == 200 || xhr.status == 201) {
			let ret = JSON.parse(xhr.responseText),
				calendar = ret.calendar,
				weeks = ret.weeks,
				firstDateStr,
				lastDateStr;
			
			
			firstDateStr = ret.firstDate.year + "-"
						 + ret.firstDate.monthValue.zf(2)+ "-"
						 + ret.firstDate.dayOfMonth.zf(2);
			lastDateStr = ret.lastDate.year + "-"
						 + ret.lastDate.monthValue.zf(2)+ "-"
						 + ret.lastDate.dayOfMonth.zf(2);
			
			firstDate = new Date(firstDateStr);
			lastDate = new Date(lastDateStr);
			
			await setCalendar(weeks);
			
			yearName.innerText = ret.year;
			monthName.innerText = ret.month;
			
			let days = document.querySelectorAll(".days"),
				dayLabel = document.querySelectorAll(".day-label");
			
			for(let day in calendar) {
				let gap = new Date(day).between(firstDate);
				
				dayLabel[gap].innerText = new Date(day).getDate();
				days[gap].date = day;
				
				for(let i=0; i<calendar[day].length; i++) {
					let schedule = calendar[day][i],
						p = document.createElement("p"),
						content = "";
					
					p.scheduleId = schedule.scheduleId;
					p.date = day;
					p.onclick = function (e) {
						let target = e.target,
							date = target.date,
							id = target.scheduleId,
							schedules = calendar[date],
							evt = schedules.filter(v=> {
								return v.scheduleId == id;
							})[0];
						
						let modifyBtn = document.querySelector(".modify-btn"),
							deleteBtn = document.querySelector(".delete-btn"),
							submitBtn = document.querySelector(".submit-btn");
						
						if(!!modifyBtn) {
							modifyBtn.addEventListener('click', modifySchedule, false);
						}
						
						//클릭이벤트에 파라미터를 전달해주는 경우 addEventListener를 사용하면 재귀호출로 인해 여러 번 실행되는 문제가 있기 때문에 onclick 사용. 
						if(!!deleteBtn) {
							deleteBtn.onclick = function(e) {
								deleteSchedule(evt.groupId, evt.scheduleId);
							};
						}
						if(!!submitBtn) {
							submitBtn.onclick = function(e) {
								submitModify(evt.groupId, evt.scheduleId);
							};
						}
						
						console.log(evt);
						
						let center = new kakao.maps.LatLng(evt.placeLatitude, evt.placeLongitude);
						initMarker.setPosition(center);
						panTo(center);
						map.setDraggable(false);
						map.setZoomable(false);
						
						initMarker.position = {x: evt.placeLongitude, y: evt.placeLatitude};
						initMarker.building_name = (evt.buildingName !== "NULL") ? evt.buildingName : "";
						initMarker.address_name = (evt.placeAddress !== "NULL") ? evt.placeAddress : "";
						
						if(evt.buildingName && evt.buildingName !== "NULL") buildingName.innerText = evt.buildingName;
						if(evt.placeAddress && evt.placeAddress !== "NULL") addressName.innerText = evt.placeAddress;
						
						groupName.innerText = evt.groupName;
						dateInput.readOnly = beginTime.readOnly = endTime.readOnly = true;
						dateInput.value = new Date(evt.beginTime).format("yyyy-MM-dd");
						beginTime.value = new Date(evt.beginTime).format("HH:mm");
						endTime.value = new Date(evt.endTime).format("HH:mm");
						
						if(scheduleModalWrapper.classList.contains("fade-out")) {
							scheduleModalWrapper.classList.remove("fade-out");
						}
						scheduleModalWrapper.classList.add("fade-in");
					}
					
					content += new Date(schedule.beginTime).format("HH:mm") + "&nbsp;"
							+ schedule.groupName;
					
					p.innerHTML = content;
					days[gap].appendChild(p);
				}
			}
			
			
		} else {
			alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
		}
	}
}

function setCalendar(weeks) {
	let daysStr = "";
	return new Promise(function (resolve, reject) {
		for(let i=0; i<weeks; i++) {
			daysStr += "<div class='weeks week-" + (i+1) + " clearfix'>"
			for(let j=0; j<7; j++) {
				daysStr += "<div class='day-" + (j+1) + "'>" +
						"<div class='day-label'></div>" +
						"<div class='days'></div>" +
						"</div>";
			}
			daysStr += "</div>";
		}
		calendarBody.innerHTML = daysStr;
		resolve();
	})
}

Date.prototype.plusDays = function (day) {
	let today = this;
		next = today.getTime() + 24 * 60 * 60 * 1000 * day;
	return new Date(next);
}

Date.prototype.between = function (std) {
	let end = this;
	return parseInt( (end.getTime() - std.getTime()) / (24 * 60 * 60 * 1000));
}