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
<body>
	<sec:csrfInput />
	<a href="./mapAPI?groupId=${groupId }">새로운 일정 생성하기 </a>
	<table>
		
		<tr>
			<th>그룹명 </th>
			<th>날짜 </th>
			<th>시작시간 </th>
			<th>종료시간 </th>
			<th>장소 </th>
			<th>수정 </th>
			<th>삭제 </th>
		</tr>
		<c:forEach items="${schedules }" var="schedule">
			<tr class="schedule" schedule-id = "${schedule.scheduleId }">
				<td>${schedule.groupName }</td>
				<td class="scheduleDate"><fmt:formatDate pattern="yyyy-MM-dd" value="${schedule.beginTime}" /></td>
				<td class="scheduleBeginTime"><fmt:formatDate pattern="HH:mm" value="${schedule.beginTime}" /></td>
				<td class="scheduleEndTime"><fmt:formatDate pattern="HH:mm" value="${schedule.endTime}" /></td>
				<td class="scheduleAddress">
						<c:if test="${schedule.buildingName ne 'NULL' }">
							${schedule.buildingName }<br/>
						</c:if>
							${schedule.placeAddress }
				</td>
				<td><button onclick="modifySchedule(this, ${schedule.scheduleId }, ${schedule.placeLatitude }, ${schedule.placeLongitude })">수정 </button></td>
				<td><button onclick="deleteSchedule(this, ${schedule.groupId }, ${schedule.scheduleId })">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<table>
		<thead>
			<tr class="calendar-month">
				<td colspan="7">
					<button onclick="getSchedule(-1)">&laquo;</button>
					<span class="year-name">2020</span> - <span class="month-name">3</span>
					<button onclick="getSchedule(1)">&raquo;</button>
				</td>
			</tr>
			<tr class="calendar-day">
				<td class="day-name">일</td>
				<td class="day-name">월</td>
				<td class="day-name">화</td>
				<td class="day-name">수</td>
				<td class="day-name">목</td>
				<td class="day-name">금</td>
				<td class="day-name">토</td>
			</tr>
		</thead>
		<tbody class="calendar-body">
		</tbody>
	</table>
	
	<div class="schedule-modal-wrapper">
		<div class="schedule-modal-content">
			<h2>일정 수정하기 </h2>
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
			<button onclick="submitModify(${groupId})">생성 </button>
		</div>
	</div>
	<!-- services 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae043202100ac9f674291ee85c05ebc2&libraries=services"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_calendar.js"></script>
	<script type="text/javascript" src="/js/group_schedule_management.js"></script>
</body>
</html>