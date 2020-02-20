<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지 </title>
</head>
<body>
	<h2>가입한 그룹 목록 </h2>
	<table border="1">
		<tr>
			<th>카테고리 </th>
			<th>그룹명 </th>
			<th>주기 </th>
			<th>지역 </th>
			<th>그룹상태 </th>
		</tr>
		<c:forEach items="${gList}" var="GroupVO">
			<tr>
				<td>${GroupVO.groupCategory}</td>
				<td><a href="/attend?groupId=${GroupVO.groupId }">${GroupVO.groupName }</a></td>
				<td>${GroupVO.groupPeriod}</td>
				<td>${GroupVO.groupArea}</td>
				<td>${GroupVO.groupStatus}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>