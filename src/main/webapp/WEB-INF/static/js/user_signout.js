const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function pwChk() {
	const userId = document.getElementById("userId").value;
	const userPw = document.getElementById("userPw").value;
	const data = {userId : userId , userPw : userPw};
	const msg = document.getElementById("pwMsg");
	const xhr = new XMLHttpRequest();
	
	xhr.open("POST", "./pwChk", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
	
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			if(xhr.responseText == "true") {
				msg.innerHTML = "";
				let confirmed = confirm("회원님과 관련된 모든 정보가 소멸됩니다. 정말 회원탈퇴를 하시겠습니까?");
				if(confirmed) {
					xhr.open("POST", "./signout", true);
					xhr.setRequestHeader(header, token);
					xhr.setRequestHeader("Content-Type", "application/json");
					xhr.send(JSON.stringify(data));
					xhr.onload = function () {
						if(xhr.status == 200 || xhr.status == 201) {
							if(Number(xhr.responseText) >= 1) {
								alert("성공적으로 탈퇴 되었습니다.");
								document.location.href="/";
							} else {
								alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
							}
						}
					}
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