<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>회원가입 </title>
<link rel="stylesheet" href="/css/form.css">
<link rel="stylesheet" href="/css/signup.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
<section id="wrapper">
			<form action="/user/signup" method="post" style="position:relative;">
			<sec:csrfInput />
					<label for="userId">아이디</label>
					<input type="text" id="userId" name="userId" />
					<a id="idChk" onclick="idCheck()">중복확인 </a>
					<p id="idMsg" style="display:none">중복된 아이디가 존재합니다. </p>

					<label for="userPw">비밀번호</label>
					<input type="password" id="userPw" name="userPw" placeholder="영문 및 숫자만 입력 가능합니다. " />
					<p id="pwMsg">영문 및 숫자만 입력 가능합니다.</p>

					<label for="userName">성명</label>
					<input type="text" id="userName" name="userName" />

					<label for="userEmail">이메일 </label>
					<input type="email" id="userEmail" name="userEmail" />

					<button type="button" id="submitBtn" onclick="formChk()" disabled>회원가입</button>
			</form>
		</section>
		<script type="text/javascript" src="/js/user_signup.js"></script>
</body>
</html>