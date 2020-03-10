const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function idCheck() {
	const userId = document.getElementById("userId").value;
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
				msg.style.display = "inline";
				msg.style.fontSize = "14px";
				msg.style.color = "green";
				submitBtn.removeAttribute("disabled");
			} else {
				msg.innerHTML = "중복된 아이디가 존재합니다.";
				msg.style.display = "inline";
				msg.style.fontSize = "14px";
				msg.style.color = "red";
				submitBtn.setAttribute("disabled", "true");
			}
		}
	}
}

function formChk() {
	const form = document.querySelector("#container > form");
	const userId = document.getElementById("userId").value;
	const userPw = document.getElementById("userPw").value;
	const pwMsg = document.getElementById("pwMsg");
	const userName = document.getElementById("userName").value;
	const userEmail = document.getElementById("userEmail").value;
	const RegExp = /[^A-Za-z0-9]/gi;
	console.log(userPw);
	if(userId === "") {
		alert("아이디를 입력해주세요. ");
	} else if(userPw === "") {
		alert("비밀번호를 입력해주세요. ");
	} else if(RegExp.test(userPw)) {
		pwMsg.style.opacity = 1;
	} else if(userName === "") {
		alert("이름을 입력해주세요. ");
	} else if(userEmail === "") {
		alert("이메일을 입력해주세요. ");
	} else {
		form.submit();
	}
}