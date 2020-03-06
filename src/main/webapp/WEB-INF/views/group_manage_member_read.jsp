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
<title>스터디 신청서 내용 </title>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<h2> 스터디 신청서 내용입니다.</h2>
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
			<th>조회수</th>
			<th>작성 날짜</th>
		</tr>
		<tr>
			<td>${GroupVO.groupId}</td>
			<td>${GroupVO.groupCategory}</td>
			<td>${GroupVO.userVO.userName}</td>
			<td>${GroupVO.groupName}</td>
			<td>${GroupVO.groupMemberLimit}</td>
			<td>${GroupVO.groupPeriod}</td>
			<td>${GroupVO.groupArea}</td>
			<td>${GroupVO.groupStatus}</td>
			<td>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</td>
			<td>${GroupVO.groupViewCnt}</td>
			<td>
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
				</fmt:timeZone>
			</td>
		</tr>
	</table>
	<br>
	<h2>그룹 소개글</h2>
	<textarea id="msg" cols="80" rows="5" readonly="readonly">${GroupVO.groupDetail }</textarea>
	<br>
	<h2>이 그룹에 대한 회원님의 정보입니다.</h2>
	<table border="1">
		<tr>
			<th>권한</th>
			<th>경고 점수</th>
			<th>가입 날짜</th>
		</tr>
		<tr>
			<td>${GroupMemberVO.groupAuthority}</td>
			<td>${GroupMemberVO.demeritCnt}</td>
			<td>
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupMemberVO.regDate}" />
				</fmt:timeZone>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<button type="button" id="leaveGroupBtn" onclick="leaveGroup('${GroupMemberVO.groupId}','${GroupMemberVO.userId}')">그룹 나가기</button> &nbsp;
	<button type="button" id="listHomeBtn" onclick="listHome()">목록으로</button> &nbsp;
	<script type="text/javascript" src="/js/group_manage_member_read.js"></script>
</body>

</html>