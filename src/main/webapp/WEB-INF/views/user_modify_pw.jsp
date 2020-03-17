<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정 </title>
<link rel="stylesheet" href="/css/form.css">
<sec:csrfMetaTags />
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<sec:csrfInput />
	<section id="wrapper">
		<div>
			<label for="userId">아이디</label>
			<input type="text" id="userId" name="userId" value=${userId} readonly />
		</div>
		<div>
			<label for="userPw">현재 비밀번호 </label>
			<input type="password" id="userPw" name="userPw" />
			<p id="pwMsg" style="display:none;">비밀번호가 일치하지 않습니다. </p>
		</div>
		<div>
			<label for="userNewPw">새 비밀번호 </label>
			<input type="password" id="userNewPw" name="userNewPw" />
		</div>
		<div>
			<label for="userPwCmp">비밀번호 확인 </label>
			<input type="password" id="userPwCmp" name="userPwCmp" />
			<button type="button" id="pwChkBtn" onclick="pwChk()">비밀번호 변경 </button>
		</div>
	</section>
	<script type="text/javascript" src="/js/user_modify_pw.js"></script>
</body>
</html>