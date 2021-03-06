<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<sec:csrfMetaTags />
	<title>스터디 출석</title>
	<link rel="stylesheet" href="/css/attend.css">
	<!-- services 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae043202100ac9f674291ee85c05ebc2&libraries=services"></script>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section id="wrapper" class="content-wrapper">
		<div class="attend-circle circle-loader">
			<div class="checkmark draw" style="display:none;"></div>
		<c:choose>
			<c:when test="${isTimeOn >= -10 && finished eq false && attendanceStatus eq null }">
				<span class="effect-halo effect-halo-running"></span>
				<span class="effect-halo effect-halo-running"></span>
				<form action="./attend" method="post">
					<sec:csrfInput />
					<button class="attendBtn" type="button" onclick="attend('${userId }', '${start }')">출석하기</button>
				</form>
			</c:when>
			<c:when test="${attendanceStatus ne null }">
				<h2 class="attendanceStatus">${attendanceStatus } </h2>
			</c:when>
			<c:when test="${finished eq true &&  attendanceStatus eq null}">
				<h2 class="attendanceStatus">결석</h2>
			</c:when>
			<c:otherwise>
				<h2 class="attendanceStatus">출석시간이 아닙니다</h2>
			</c:otherwise>
		</c:choose>
		</div>
	</section>
</body>
<script>
	var startTime = "${start}",
			endTime = "${end}",
			scheduleId = ${schedule.scheduleId},
			groupId = ${schedule.groupId},
			plat = ${schedule.placeLatitude},
			plon = ${schedule.placeLongitude};
</script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/group_attend.js"></script>
</html>