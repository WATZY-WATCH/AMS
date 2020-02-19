const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


function attend() {
	const xhr = new XMLHttpRequest();
	const data = {groupId: groupId, date: new Date().format("yyyy-MM-dd HH:mm")};
	
	xhr.open("POST", "./attend", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.setRequestHeader("Data-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			let ret = xhr.responseText;
			let retObj = JSON.parse(ret);
			alert(userId + "님 " + retObj.status + "했습니다. ");
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

let timer = setInterval(function() {
	let now = new Date();
	let baseCmp = new Date(baseTime);
	if(parseInt((now- baseCmp) / 60000) > 10) {
		clearInterval(timer);
		const body = document.querySelector("body");
		const form = document.querySelector("form");
		const attendBtn = document.querySelector(".attendBtn");
		form.removeChild(attendBtn);
		let h2 = document.createElement("h2");
		let text = document.createTextNode("출석종료 ");
		h2.appendChild(text);
		body.appendChild(h2);
	}
}, 1000);