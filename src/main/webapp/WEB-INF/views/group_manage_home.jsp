<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>스터디 그룹 관리 </title>
</head>
<body>
	<h2> 운영중인 스터디 목록입니다.</h2>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>그룹장</th>
			<th>제목</th>
			<th>최대 인원수</th>
			<th>주기</th>
			<th>지역</th>
			<th>진행상황</th>
			<th>연령대</th>
			<th>생성 날짜</th>
		</tr>
	<c:forEach items="${masterList}" var="GroupVO">
		<tr>
			<td>${GroupVO.groupId}</td>
			<td>${GroupVO.groupCategory}</td>
			<td>${GroupVO.userVO.userName}</td>
			<td><a href="./listRead?groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></td>
			<td>${GroupVO.groupMemberLimit}</td>
			<td>${GroupVO.groupPeriod}</td>
			<td>${GroupVO.groupArea}</td>
			<td>${GroupVO.groupStatus}</td>
			<td>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</td>
			<td><fmt:setTimeZone value="UTC" />
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" /></td>
		</tr>
	</c:forEach>
	</table>
	<br>
	<h2> 소속된 스터디 목록입니다.</h2>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>그룹장</th>
			<th>제목</th>
			<th>최대 인원수</th>
			<th>주기</th>
			<th>지역</th>
			<th>진행상황</th>
			<th>연령대</th>
			<th>가입 날짜</th>
		</tr>
	<c:forEach items="${memberList}" var="GroupVO">
		<tr>
			<td>${GroupVO.groupId}</td>
			<td>${GroupVO.groupCategory}</td>
			<td>${GroupVO.userVO.userName}</td>
			<td><a href="./listRead?groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></td>
			<td>${GroupVO.groupMemberLimit}</td>
			<td>${GroupVO.groupPeriod}</td>
			<td>${GroupVO.groupArea}</td>
			<td>${GroupVO.groupStatus}</td>
			<td>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</td>
			<td><fmt:setTimeZone value="UTC" />
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.groupMemberVO.regDate}" /></td>
		</tr>
	</c:forEach>
	</table>
	<br>
	<h2> 신청한 스터디 목록입니다.</h2>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>그룹장</th>
			<th>제목</th>
			<th>최대 인원수</th>
			<th>주기</th>
			<th>지역</th>
			<th>진행상황</th>
			<th>연령대</th>
			<th>가입 날짜</th>
		</tr>
	<c:forEach items="${applicationList}" var="GroupVO">
		<tr>
			<td>${GroupVO.groupId}</td>
			<td>${GroupVO.groupCategory}</td>
			<td>${GroupVO.userVO.userName}</td>
			<td><a href="./listRead?groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></td>
			<td>${GroupVO.groupMemberLimit}</td>
			<td>${GroupVO.groupPeriod}</td>
			<td>${GroupVO.groupArea}</td>
			<td>${GroupVO.groupStatus}</td>
			<td>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</td>
			<td><fmt:setTimeZone value="UTC" />
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.groupMemberVO.regDate}" /></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>