const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

var oAuthNameChked = false;
var saveOAuthName=document.getElementById("userName").value;
const initOAuthName = document.getElementById("userName").value;

var idChked = false, nameChked = false, emailChked = false;
var saveId = document.getElementById("userId").value;
var saveName=document.getElementById("userName").value;
var saveEmail=document.getElementById("userEmail").value;

function idCheck() {
	const userId = document.getElementById("userId").value;
	
	if(userId === "") {
		alert("아이디를 입력해주세요.");
		return;
	}
	
	const data = {userId : userId};
	const msg = document.getElementById("idMsg");
	const submitBtn = document.getElementById("submitBtn");
	const xhr = new XMLHttpRequest();
	
	xhr.open("GET", "./id/"+userId, true);
	xhr.setRequestHeader(header, token);
	xhr.send();
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(xhr.responseText == 0) {
				msg.innerHTML = "사용 가능한 아이디입니다.";
				msg.style.display = "block";
				msg.style.fontSize = "14px";
				msg.style.color = "green";
			} else {
				msg.innerHTML = "중복된 아이디가 존재합니다.";
				msg.style.display = "block";
				msg.style.fontSize = "14px";
			}
		}
	}
}

function formChk() {
	const form = document.querySelector("#wrapper > form");
	const userId = document.getElementById("userId").value;
	const userPw = document.getElementById("userPw").value;
	const pwMsg = document.getElementById("pwMsg");
	const userName = document.getElementById("userName").value;
	const userEmail = document.getElementById("userEmail").value;
	const RegExp = /[^A-Za-z0-9]/gi;
	const EmailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	if(userId === "") {
		alert("아이디를 입력해주세요. ");
	} else if(userPw === "") {
		alert("비밀번호를 입력해주세요. ");
	} else if(RegExp.test(userPw)) {
		pwMsg.style.display = "block";
	} else if(userName === "") {
		alert("이름을 입력해주세요. ");
	} else if(userEmail === "") {
		alert("이메일을 입력해주세요. ");
	} else if(!EmailRegExp.test(userEmail)) {
		alert("올바른 이메일 형식이 아닙니다.");
	} else {
		form.submit();
	}
}

function oAuthNameChk() {
	const userName = document.getElementById("userName").value;
	const submitBtn = document.getElementById("submitBtn");
	const data = {userName : userName};
	const msg = document.getElementById("nameMsg");
	const xhr = new XMLHttpRequest();
	
	if(userName === "") {
		alert('닉네임은 비워둘 수 없습니다.');
		return;
	}
	
	xhr.open("POST", "/user/name", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(xhr.responseText == 0) {
				msg.innerHTML = "사용 가능한 닉네임입니다.";
				msg.style.display = "inline";
				msg.style.fontSize = "14px";
				msg.style.color = "green";
				oAuthNameChked = true;
				saveOAuthName= document.getElementById("userName").value;
				submitBtn.removeAttribute("disabled");
			} else {
				msg.innerHTML = "중복된 닉네임이 존재합니다.";
				msg.style.display = "inline";
				msg.style.fontSize = "14px";
				msg.style.color = "#EF978F";
				nameChked = false;
			}
		}
	}
}

function oAuthformChk() {
	const data = user;
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "./", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	const userName = document.getElementById("userName").value;
	if(!user) {
		alert("올바른 접근이 아닙니다.");
	} else if(initOAuthName !== userName && !oAuthNameChked){
		alert("닉네임 중복확인을 해주세요.");
	} else if(saveOAuthName!=userName){
		alert("닉네임이 바뀌었습니다. 중복확인을 다시 해주세요.");
	} else {
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				location.href = "/";
			} else {
				alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
	}
}