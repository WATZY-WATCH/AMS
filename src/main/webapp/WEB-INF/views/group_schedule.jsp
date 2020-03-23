<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 일정</title>
<sec:csrfMetaTags />
<link rel="stylesheet" href="/css/form.css">
<link rel="stylesheet" href="/css/schedule.css" />
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<sec:csrfInput />
	<section id="calendar" class="content-wrapper">
		<c:if test="${isAdmin}"><a href="/group/mapAPI?groupId=${groupId }">새로운 일정 생성하기<i class="material-icons">event_available</i></a></c:if>
		<div>
			<div class="calendar-month">
				<button onclick="getSchedule(-1)"><i class="material-icons">chevron_left</i></button>
				<span class="year-name">2020</span> / <span class="month-name">3</span>
				<button onclick="getSchedule(1)"><i class="material-icons">chevron_right</i></button>
			</div>
			<div class="calendar-day">
				<div class="day-name">일</div>
				<div class="day-name">월</div>
				<div class="day-name">화</div>
				<div class="day-name">수</div>
				<div class="day-name">목</div>
				<div class="day-name">금</div>
				<div class="day-name">토</div>
			</div>
		</div>
		<div class="calendar-body">
		</div>
	</section>

	<div class="schedule-modal-wrapper">
		<div class="schedule-modal-content">
			<div class="close"><i class="material-icons">close</i></div>
			<h2 class="groupName">일정 수정하기 </h2>
			<c:if test="${isAdmin }">
				<button class="modify-btn" ><i class='material-icons'>edit</i></button>
				<button class="delete-btn"><i class='material-icons'>delete</i></button>
			</c:if>
			<br>
			<label for="scheduleDate">일시</label>
			<input type="date" id="scheduleDate" name="scheduleDate"  />
			<div class="time-wrapper">
				<input type="time" id="beginTime" name="beginTime" value="00:00" />
				<span> ~ </span>
				<input type="time" id="endTime" name="endTime" value="23:59" />
			</div>
			<div class="location-name">
				<p class="building-name"></p>
				<p class="address"></p>
			</div>
			<div id="map" style="width:100%;height:40vh;margin:0.25rem auto;position:relative;overflow:hidden; box-sizing:border-box;"></div>
			<c:if test="${isAdmin }">
				<button class="submit-btn">저장</button>
			</c:if>
		</div>
	</div>
	<!-- services 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae043202100ac9f674291ee85c05ebc2&libraries=services"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_calendar.js"></script>
	<script type="text/javascript" src="/js/group_schedule_management.js"></script>
</body>
</html>