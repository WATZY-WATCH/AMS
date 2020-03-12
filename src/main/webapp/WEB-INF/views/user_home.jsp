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
	<link rel="stylesheet" href="/css/home.css">
	<link rel="stylesheet" href="/css/weekly.css">
	<sec:csrfMetaTags />
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
<nav>
	<sec:authorize access="hasAuthority('USER')">
		<p id="userName"> <span> ${userName }</span> 님 환영합니다! </p>
		<form class="logout-btn" action="<c:url value='/logout' />" method="post">
			<sec:csrfInput />
			<button>로그아웃 </button>
		</form>
	</sec:authorize>
</nav>
<div class="menu-wrapper clearfix">
	<div class="main-menu">
	<a href="/group/create">그룹생성</a>
	</div>
	<div class="main-menu">
	<a href="/group/listCri">그룹찾기</a>
	</div>
	<div class="main-menu">
	<a href="/groupManage/home">그룹관리</a>
	</div>
</div>

<section class="weekly">
	<div class="week-day">
		<c:forEach items="${weeks }" var="d">
			<c:set var="len" value="${fn:length(d) }" />
			<div class="schedule-wrapper">
				<div class="day-label day-${d }">${fn:substring(d, 5, len) }</div>
				<div class="schedule">
					<ul class="schedule-${d }">
					</ul>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
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
			<span class="schedule-time"><fmt:formatDate pattern="HH:mm" value="${s.beginTime}" /></span>
			<a href="/attend?groupId=${s.groupId }&scheduleId=${s.scheduleId}">
				<span class="group-name">${s.groupName }</span>&ensp;
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
<script type="text/javascript" src="/js/user_home.js"></script>
<script type="text/javascript" src="/js/user_schedule.js"></script>
</body>
</html>
