<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<title>Home</title>
	<sec:csrfMetaTags />
</head>
<body>
<h1>
	Hello world2!  
</h1>

<p id="userName"> ${userName } 님 환영합니다! </p>
<a href="/user/myPage">마이페이지 </a>
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
<div>
<a href="/group/listCri">그룹찾기</a>
</div>
<div>
<a href="/groupManage/home">그룹관리</a>
</div>
<table border="1">
	<tr class="week-day">
		<c:forEach items="${weeks }" var="d">
			<c:set var="len" value="${fn:length(d) }" />
			<th class="day-${d }">${fn:substring(d, 5, len) }</th>
		</c:forEach>
	</tr>
	<tr>
		<c:forEach items="${weeks }" var="d">
			<td class="schedule">
				<ul class="schedule-${d }">
				</ul>
			</td>
		</c:forEach>
	</tr>
</table>
<div class="schedule-list" style="display:none;">
	<fmt:parseDate value="${beginDate }" var="base" pattern="yyyy-MM-dd"/>
	<fmt:parseNumber value="${base.time / (1000*60*60*24)}" integerOnly="true" var="baseStr"></fmt:parseNumber>
	
	<c:forEach items="${schedules}" var="s">
		<fmt:timeZone value="Asia/Seoul">
			<fmt:formatDate pattern="yyyy-MM-dd" value="${s.beginTime}" var="curr" />
		</fmt:timeZone>
		<fmt:parseDate value="${curr }" var="cmp" pattern="yyyy-MM-dd"/>
		<fmt:parseNumber value="${cmp.time / (1000*60*60*24)}" integerOnly="true" var="cmpStr"></fmt:parseNumber>

		<li class="schedule-${cmpStr - baseStr}">
			<a href="/attend?groupId=${s.groupId }&scheduleId=${s.scheduleId}">
				<span><fmt:formatDate pattern="HH:mm" value="${s.beginTime}" /></span>
				<span>${s.groupName }</span>&ensp;
				<span>${s.groupCategory }</span>&ensp;
			</a>
			
		</li>
	</c:forEach>
</div>
<script>
	function signoutChk() {
		let ret = confirm('카카오 계정과 서비스 연동이 해제됩니다.\n연동 해제 이후 데이터 복구가 불가능합니다.');
		if(ret) {
			document.location.href = "/oauth/user/signout";
		}
	}
</script>
<script type="text/javascript" src="/js/user_schedule.js"></script>
</body>
</html>
