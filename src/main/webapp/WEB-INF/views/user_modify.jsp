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
		<script type="text/javascript" src="/js/user_modify.js" />
</body>
</html>