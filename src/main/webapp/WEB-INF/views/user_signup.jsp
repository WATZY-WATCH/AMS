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
		<script type="text/javascript" src="/js/user_signup.js" />
</body>
</html>