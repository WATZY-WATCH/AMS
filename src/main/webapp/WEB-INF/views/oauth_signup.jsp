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
<section id="wrapper" class="content-wrapper">
	<form action="/oauth/user/" method="post" style="position:relative;">
	<sec:csrfInput />
			<label for="userId">아이디</label>
			<input type="text" id="userId" name="userId" value="${user.userId }" readOnly/>
			
			<label for="userName">성명</label>
			<input type="text" id="userName" name="userName" value="${user.userName }" />
			<button type="button" id="nameChkBtn" onclick="oAuthNameChk()">중복확인 </button>
			<p id="nameMsg" style="display:none">중복된 닉네임이 존재합니다. </p>

			<button type="button" id="submitBtn" onclick="oAuthformChk('${user}')" disabled>회원가입</button>
	</form>
</section>
<script>
	const user = {};
	user.userId = "${user.userId}";
	user.userName = "${user.userName}";
	user.userEmail = "${user.userEmail}";
	user.userToken = "${user.userToken}";
	user.userType = "${user.userType}";
</script>
<script type="text/javascript" src="/js/user_signup.js"></script>
</body>
</html>