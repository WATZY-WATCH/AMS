<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지 </title>
<link rel="stylesheet" href="/css/form.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<div id="wrapper">
	<sec:authorize access="isAnonymous()">
		<h3 class="title">로그인</h3>
			<c:if test="${param.containsKey('error') }">
				<span style="color: red;">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }" />
				</span>
			</c:if>
			<c:url var="loginUrl" value="/login" />
			<form:form action="${loginUrl }">
				<sec:csrfInput />
				<label for="userId">아이디</label>
				<input type="text" id="userId" name="userId" placeholder="아이디">
				<label for="userPw">비밀번호</label>
				<input type="password" id="userPw" name="userPw" placeholder="비밀번호">
				<button class="login-btn">로그인 하기</button>
		</form:form>
		<c:url var="signUpUrl" value="/user/signup" />
		<p class="sign-up-msg">아직 계정이 없으신가요? <a href="${signUpUrl }" class="sign-up-btn" >회원가입</a></p>
		<p class="divider"><span>또는</span></p>
		<a href="/klogin" class="kakao-login"></a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<script type="text/javascript">
			alert("이미 로그인한 사용자입니다. ");
			document.location.href="/";
		</script>
	</sec:authorize>
	</div>
</body>
</html>