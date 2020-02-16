<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world2!  
</h1>

<p id="userName"> ${userName } 님 환영합니다! </p>
<form action="<c:url value='/logout' />" method="post">
	<sec:csrfInput />
	<button>로그아웃 </button>
</form>
<sec:authorize access="hasRole('USER')">
	<a href="/user/modify">정보수정</a>
	<a href="/user/signout">회원탈퇴</a>
</sec:authorize>
<sec:authorize access="hasRole('OAUTH_USER')">
	<a href="/user/modify">정보수정</a>
	<a href="/user/signout">회원탈퇴</a>
</sec:authorize>
<div>
<a href="/group/create">그룹생성</a>
</div>
</body>
</html>
