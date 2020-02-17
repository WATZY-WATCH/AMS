const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function pwChk() {
	const userId = document.getElementById("userId").value;
	const userPw = document.getElementById("userPw").value;
	let newPw = document.getElementById("userNewPw").value;
	let newPwCmp = document.getElementById("userPwCmp").value;
	const data = {userId : userId , userPw : userPw};
	const msg = document.getElementById("pwMsg");
	const xhr = new XMLHttpRequest();
	if(!newPw || !newPwCmp) {
		alert("변경할 비밀번호를 입력해주세요. ");
		return;
	}
	
	xhr.open("POST", "./pwChk", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(xhr.responseText == "true") {
				msg.innerHTML = "";
				if(newPw === newPwCmp) {
					data.userPw = newPw;
					xhr.open("POST", "./modifyPw", true);
					xhr.setRequestHeader(header, token);
					xhr.setRequestHeader("Content-Type", "application/json");
					xhr.send(JSON.stringify(data));
					xhr.onload = function () {
						if(xhr.status == 200 || xhr.status == 201) {
							if(Number(xhr.responseText) >= 1) {
								document.location.href="./modifyPwSuccess";
							} else {
								alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
							}
						}
					}
				} else {
					alert("새로운 비밀번호가 일치하지 않습니다. ");
				}
			} else {
				msg.innerHTML = "비밀번호가 틀렸습니다.";
				msg.style.display = "inline";
				msg.style.fontSize = "14px";
				msg.style.color = "red";
			}
		}
	}
}