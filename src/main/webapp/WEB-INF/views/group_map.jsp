<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>다음 지도 API</title>
	<sec:csrfMetaTags />
	<link rel="stylesheet" href="/css/animation.css" />
	<link rel="stylesheet" href="/css/map.css" />
	<link rel="stylesheet" href="/css/schedule.css" />
	<!-- services 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae043202100ac9f674291ee85c05ebc2&libraries=services"></script>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<sec:csrfInput />
	<section class="content-wrapper">
		<div class="map_wrap">
			<div id="menu_wrap" class="bg_white">
				<div class="option">
					<div>
					<input type="text" placeholder="검색 키워드를 입력해주세요 " id="inputKeyword" size="30"> 
					<button onclick="searchPlace()">검색하기</button> 
					</div>
				</div>
				<div id="map" style="width:100%;position:relative;overflow:hidden; box-sizing:border-box;"></div>
				<div id="clickLatlng"></div>
				<hr>
				<ul id="placesList"></ul>
				<div id="pagination"></div>
			</div>
		</div>
	</section>
	<div class="schedule-modal-wrapper">
		<div class="schedule-modal-content">
			<div class="close"><i class="material-icons">close</i></div>
			<h2 class="info-msg-wrapper"><b class="info-msg">일정 생성하기</b></h2>
			<h3 class="group-name">${groupName }</h3>
			<label for="scheduleDate">일시 </label>
			<input type="date" id="scheduleDate" name="scheduleDate"  />
			<input type="time" id="beginTime" name="beginTime" value="00:00" /> &ensp; ~ &ensp;
			<input type="time" id="endTime" name="endTime" value="23:59" />
			<div class="location-name">
				<p class="building-name"></p>
				<p class="address"></p>
			</div>
			<button class="submit-schedule" onclick="submitSchedule(${groupId})">생성 </button>
		</div>
	</div>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_map.js"></script>
</body>
</html>