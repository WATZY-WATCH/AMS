<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<sec:csrfMetaTags />
	<title>Insert title here</title>
	<!-- services 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae043202100ac9f674291ee85c05ebc2&libraries=services"></script>
</head>
<body>
	<c:choose>
		<c:when test="${isTimeout && (attendanceStatus eq false) }">
			<form action="./attend" method="post">
				<sec:csrfInput />
				<button class="attendBtn" type="button" onclick="attend(${groupId}, '${userId }', '${base }')">출석하기 </button>
			</form>
		</c:when>
		<c:when test="${attendanceStatus && (isTimeout eq false)}">
			<h2>지각 </h2>
		</c:when>
		<c:when test="${attendanceStatus eq true }">
			<h2>출석완료 </h2>
		</c:when>
		<c:otherwise>
			<h2>결석 </h2>
		</c:otherwise>
	</c:choose>
</body>
<script type="text/javascript" src="/js/group_attend.js"></script>
</html>