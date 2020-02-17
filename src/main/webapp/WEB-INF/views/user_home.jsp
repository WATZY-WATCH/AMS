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
<sec:authorize access="hasAuthority('USER')">
	<form action="<c:url value='/logout' />" method="post">
		<sec:csrfInput />
		<button>로그아웃 </button>
	</form>
	<a href="/user/modify">정보수정</a>
	<a href="/user/modifyPw">비밀번호 변경 </a>
	<a href="/user/signout">회원탈퇴</a>
</sec:authorize>
<sec:authorize access="hasAuthority('OAUTH_USER')">
	<a href="/oauth/user/logout">로그아웃 </a>
	<a href="/oauth/user/modify">정보수정</a>
	<button onclick="signoutChk()">회원탈퇴</button>
</sec:authorize>
<div>
<a href="/group/create">그룹생성</a>
</div>
<script>
	function signoutChk() {
		let ret = confirm('카카오 계정과 서비스 연동이 해제됩니다.\n연동 해제 이후 데이터 복구가 불가능합니다.');
		if(ret) {
			document.location.href = "/oauth/user/signout";
		}
	}
</script>
</body>
</html>
