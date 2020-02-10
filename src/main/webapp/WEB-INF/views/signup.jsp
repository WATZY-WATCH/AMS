<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>회원가입 </title>
<style>
#pwMsg {
	color: red;
	font-size: 14px;
	transition: 0.5s all;
}
</style>
</head>
<body>
<section id="container">
			<form action="/user/signup" method="post">
			<sec:csrfInput />
				<div>
					<label for="userId">아이디</label>
					<input type="text" id="userId" name="userId" />
					<button type="button" id="idChk" onclick="idCheck()">중복확인 </button>
					<p id="idMsg" style="display:none">중복된 아이디가 존재합니다. </p>
				</div>
				<div>
					<label for="userPw">패스워드</label>
					<input type="password" id="userPw" name="userPw" placeholder="영문 및 숫자만 입력 가능합니다. " />
					<p id="pwMsg" style="display:inline; opacity:0">영문 및 숫자만 입력 가능합니다.</p>
				</div>
				<div>
					<label for="userName">성명</label>
					<input type="text" id="userName" name="userName" />
				</div>
				<div>
					<label for="userEmail">이메일 </label>
					<input type="email" id="userEmail" name="userEmail" />
				</div>
				<div>
					<button type="button" id="submitBtn" onclick="formChk()" disabled>회원가입</button>
				</div>
			</form>
		</section>
		<script type="text/javascript">
			function idCheck() {
				const elementToken = document.querySelector('meta[name="_csrf"]');
				const token = elementToken && elementToken.getAttribute("content");
				const elementHeader = document.querySelector('meta[name="_csrf_header"]');
				const header = elementHeader && elementHeader.getAttribute("content");
				const userId = document.getElementById("userId").value;
				const data = {userId : userId};
				const msg = document.getElementById("idMsg");
				const submitBtn = document.getElementById("submitBtn");
				const xhr = new XMLHttpRequest();
				
				xhr.open("POST", "./idChk", true);
				xhr.setRequestHeader(header, token);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.send(JSON.stringify(data));
				
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
		</script>
</body>
</html>