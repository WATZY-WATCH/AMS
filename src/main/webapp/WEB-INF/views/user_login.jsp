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
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<div id="wrapper">
	<sec:authorize access="isAnonymous()">
		<h3>로그인 폼 </h3>
			<c:if test="${param.containsKey('error') }">
				<span style="color: red;">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }" />
				</span>
			</c:if>
			<c:url var="signUpUrl" value="/user/signup" />
			<a href="${signUpUrl }" >회원가입 </a>
			<c:url var="loginUrl" value="/login" />
			<form:form action="${loginUrl }">
				<sec:csrfInput />
				<table>
					<tr>
						<td><label for="userId">사용자명 </label></td>
						<td><input type="text" id="userId" name="userId"></td>
					</tr>
					<tr>
						<td><label for="userPw">패스워드 </label></td>
						<td><input type="password" id="userPw" name="userPw"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><button>로그인 </button></td>
				</table>
		</form:form>
		<a href="/klogin">카카오 로그인 </a>
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