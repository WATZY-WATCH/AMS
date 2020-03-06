<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>회원탈퇴 </title>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
<h2>기존 비밀번호를 다시 입력해주세요.</h2>
<section id="container">
			<form>
			<sec:csrfInput />
				<div>
					<label for="userId">아이디</label>
					<input type="text" id="userId" name="userId" value=${userId} readonly />
				</div>
				<div>
					<label for="userPw">비밀번호</label>
					<input type="password" id="userPw" name="userPw" />
					<br>
					<button type="button" id="pwChkBtn" onclick="pwChk()">회원탈퇴 </button>
					<p id="pwMsg" style="display:none">비밀번호가 틀렸습니다. </p>
				</div>
			</form>
		</section>
		<script type="text/javascript" src="/js/user_signout.js"></script>
</body>
</html>