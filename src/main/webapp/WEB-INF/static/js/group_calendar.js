getSchedule(0);

function getSchedule(term) {
	let groupId = new URLSearchParams(location.search).get("groupId");
	const data = {groupId : groupId}
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "./getCalendar");
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			let ret = JSON.parse(xhr.responseText),
				calendar = ret.calendar,
				firstDate = ret.firstDate,
				lastDate = ret.lastDate,
				firstDateStr,
				lastDateStr;
			
			firstDateStr = firstDate.year + " "
						 + firstDate.monthValue.zf(2)+ " "
						 + firstDate.dayOfMonth.zf(2);
			lastDateStr = lastDate.year + " "
						 + lastDate.monthValue.zf(2)+ " "
						 + lastDate.dayOfMonth.zf(2);
			
			firstDateStr = new Date(firstDateStr);
			lastDateStr = new Date(lastDateStr);
			console.log(calendar);
			
		} else {
			alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
		}
	}
}

Date.prototype.plusDays = function(day) {
	let today = this;
		next = today.getTime() + 24 * 60 * 60 * 1000 * day;
	return new Date(next);
}