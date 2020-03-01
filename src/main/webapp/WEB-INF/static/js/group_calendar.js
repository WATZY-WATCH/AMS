const calendarBody = document.querySelector(".calendar-body");
const yearName = document.querySelector(".year-name");
const monthName = document.querySelector(".month-name");

getSchedule(0);

function getSchedule(term) {
	let groupId = new URLSearchParams(location.search).get("groupId"),
		year = Number(yearName.innerText);
		month = Number(monthName.innerText);
	
	const data = {groupId : groupId, year:year, month: month, term: term}
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "./getCalendar");
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
				
				for(let i=0; i<calendar[day].length; i++) {
					let schedule = calendar[day][i],
						p = document.createElement("p"),
						content = "";
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
			daysStr += "<tr class='weeks week-" + (i+1) + "'>"
			for(let j=0; j<7; j++) {
				daysStr += "<td class='day-" + (j+1) + "'>" +
						"<div class='day-label'></div>" +
						"<div class='days'></div>" +
						"</td>";
			}
			daysStr += "</tr>";
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