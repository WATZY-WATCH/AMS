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
			<th>그룹권한</th>
			<th>가입일자</th>
			<th>비고 </th>
		</tr>
		<c:forEach items="${gList}" var="GroupVO">
			<tr>
				<td>${GroupVO.groupCategory}</td>
				<td><a href="/attend?groupId=${GroupVO.groupId }">${GroupVO.groupName }</a></td>
				<td>${GroupVO.groupPeriod}</td>
				<td>${GroupVO.groupArea}</td>
				<td>${GroupVO.groupStatus}</td>
				<td>
					<c:choose>
						<c:when test="${GroupVO.groupMemberVO.groupAuthority == 'MASTER' }">
							관리자 
						</c:when>
						<c:otherwise>
							멤버
						</c:otherwise>
					</c:choose>
				</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${GroupVO.groupMemberVO.regDate}" /></td>
				<c:if test="${GroupVO.groupMemberVO.groupAuthority == 'MASTER' }">
				<td><a href="/group/mapAPI/?groupId=${GroupVO.groupId }">일정생성하기</a></td>
			</c:if>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>