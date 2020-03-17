<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>정보 수정 </title>
<link rel="stylesheet" href="/css/form.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section id="wrapper">
		
		<form class="user-modify-form">
		<sec:csrfInput />
			<a href="/user/modifyPw">비밀번호 변경<i class="material-icons">call_made</i></a>
			<a href="/user/signout">회원탈퇴<i class="material-icons">exit_to_app</i></a>
			<div>
				<label for="userId">아이디</label>
				<input type="text" id="userId" name="userId" value=${userId} readonly />
			</div>
			<div class="user-name">
				<label for="userName">성명</label><br>
				<input type="text" id="userName" name="userName" value=${setName} />
				<button type="button" id="nameChkBtn" onclick="nameChk()">중복확인 </button>
				<p id="nameMsg" style="display:none">중복된 닉네임이 존재합니다. </p>
			</div>
			<div class="user-email">
				<label for="userEmail">이메일 </label><br>
				<input type="email" id="userEmail" name="userEmail" value=${setEmail} />
				<button type="button" id="emailChkBtn" onclick="emailChk()">중복확인 </button>
				<p id="emailMsg" style="display:none">중복된 이메일이 존재합니다. </p>
			</div>
			<div>
				<button type="button" id="submitBtn" onclick="formChk()">정보수정</button>
			</div>
		</form>
	</section>
	<script type="text/javascript" src="/js/user_modify.js"></script>
</body>
</html>