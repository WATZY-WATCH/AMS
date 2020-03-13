<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<sec:csrfMetaTags />
<link rel="stylesheet" href="/css/schedule.css" />
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	
	<sec:csrfInput />
	<a href="/group/mapAPI?groupId=${groupId }">새로운 일정 생성하기 </a>

	<section id="calendar">
		<div>
			<div class="calendar-month">
				<div>
					<button onclick="getSchedule(-1)">&laquo;</button>
					<span class="year-name">2020</span> - <span class="month-name">3</span>
					<button onclick="getSchedule(1)">&raquo;</button>
				</div>
			</div>
			<div class="calendar-day clearfix">
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
			<h2 class="groupName">일정 수정하기 </h2>
			<c:if test="${isAdmin }">
				<button class="modify-btn" >수정 </button>
				<button class="delete-btn">삭제</button>
			</c:if>
			<h3>${groupName }</h3>
			<label for="scheduleDate">일시 </label>
			<input type="date" id="scheduleDate" name="scheduleDate"  />
			<input type="time" id="beginTime" name="beginTime" value="00:00" /> &ensp; ~ &ensp;
			<input type="time" id="endTime" name="endTime" value="23:59" />
			<div class="location-name">
				<p class="building-name"></p>
				<p class="address"></p>
			</div>
			<div id="map" style="width:80%;height:50%;margin:24px auto;position:relative;overflow:hidden; box-sizing:border-box;"></div>
			<c:if test="${isAdmin }">
				<button class="submit-btn">저장 </button>
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