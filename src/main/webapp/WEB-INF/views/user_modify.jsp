<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>회원가입 </title>
</head>
<body>
<section id="container">
			<form>
			<sec:csrfInput />
				<div>
					<label for="userId">아이디</label>
					<input type="text" id="userId" name="userId" value=${userId} readonly />
				</div>
				<div>
					<label for="userName">성명</label>
					<input type="text" id="userName" name="userName" value=${setName} />
					<button type="button" id="nameChkBtn" onclick="nameChk()">중복확인 </button>
					<p id="nameMsg" style="display:none">중복된 닉네임이 존재합니다. </p>
				</div>
				<div>
					<label for="userEmail">이메일 </label>
					<input type="email" id="userEmail" name="userEmail" value=${setEmail} />
					<button type="button" id="emailChkBtn" onclick="emailChk()">중복확인 </button>
					<p id="emailMsg" style="display:none">중복된 이메일이 존재합니다. </p>
				</div>
				<div>
					<button type="button" id="submitBtn" onclick="formChk()">정보수정</button>
				</div>
			</form>
		</section>
		<script type="text/javascript">
			const elementToken = document.querySelector('meta[name="_csrf"]');
			const token = elementToken && elementToken.getAttribute("content");
			const elementHeader = document.querySelector('meta[name="_csrf_header"]');
			const header = elementHeader && elementHeader.getAttribute("content");
			
			var nameChked = false, emailChked = false;
			var saveName=document.getElementById("userName").value;
			var saveEmail=document.getElementById("userEmail").value;
			const initName = document.getElementById("userName").value;
			const initEmail = document.getElementById("userEmail").value;
			
			function nameChk() {
				const userName = document.getElementById("userName").value;
				const data = {userName : userName};
				const msg = document.getElementById("nameMsg");
				const xhr = new XMLHttpRequest();
				
				xhr.open("POST", "./nameChk", true);
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
							nameChked = true;
							saveName= document.getElementById("userName").value;
						} else {
							msg.innerHTML = "중복된 닉네임이 존재합니다.";
							msg.style.display = "inline";
							msg.style.fontSize = "14px";
							msg.style.color = "red";
							nameChked = false;
						}
					}
				}
			}
			function emailChk() {
				const userEmail = document.getElementById("userEmail").value;
				const data = {userEmail : userEmail};
				const msg = document.getElementById("emailMsg");
				const xhr = new XMLHttpRequest();
				
				xhr.open("POST", "./emailChk", true);
				xhr.setRequestHeader(header, token);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.send(JSON.stringify(data));
				
				xhr.onload = function () {
					if(xhr.status == 200 || xhr.status == 201) {
						if(xhr.responseText == 0) {
							msg.innerHTML = "사용 가능한 이메일입니다.";
							msg.style.display = "inline";
							msg.style.fontSize = "14px";
							msg.style.color = "green";
							emailChked = true;
							saveEmail= document.getElementById("userEmail").value;
						} else {
							msg.innerHTML = "중복된 이메일이 존재합니다.";
							msg.style.display = "inline";
							msg.style.fontSize = "14px";
							msg.style.color = "red";
							emailChked = false;
						}
					}
				}
			}
			
			function formChk() {
				const userId = document.getElementById("userId").value;
				const userName = document.getElementById("userName").value;
				const userEmail = document.getElementById("userEmail").value;
				const data = {userId:userId, userName: userName, userEmail: userEmail};
				const xhr = new XMLHttpRequest();
				xhr.open("POST", "./modify", true);
				xhr.setRequestHeader(header, token);
				xhr.setRequestHeader("Content-Type", "application/json");
				if(userName === "") {
					alert("닉네임을 입력해주세요. ");
				} else if(userEmail === "") { 
					alert("이메일을 입력해주세요.");
				} else if(initName !== userName && !nameChked){
					alert("닉네임 중복확인을 해주세요.");
				} else if(initEmail !== userEmail && !emailChked) {
					alert("이메일 중복확인을 해주세요.");
				} else if(saveName!=userName){
					alert("닉네임이 바뀌었습니다. 중복확인을 다시 해주세요.");
				} else if(saveEmail!=userEmail){
					alert("이메일이 바뀌었습니다. 중복확인을 다시 해주세요.");
				} else {
					let confirmed = confirm("정보를 변경하시겠습니까?");
					if(confirmed) {
						xhr.send(JSON.stringify(data));
						xhr.onload = function () {
							if(xhr.status == 200 || xhr.status == 201) {
								console.log(xhr.responseText);
								if(xhr.responseText == 1) {
									alert("성공적으로 변경되었습니다. ");
									document.location.href="/";
								} else {
									alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
								}
							}
						}
					}
				}
			}
		</script>
</body>
</html>