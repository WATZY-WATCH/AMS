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
<body>
	<h2> 스터디 신청서 내용입니다.</h2>
	<table border="1">
		<tr>
			<th>닉네임</th>
			<th>신청 날짜</th>
		</tr>
		<tr>
			<td>${gavo.userVO.userName}</td>
			<td>
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${gavo.regDate}" />
				</fmt:timeZone>
			</td>
		</tr>
	</table>
	<br>
	<textarea id="msg" cols="80" rows="5" readonly="readonly">${gavo.msg }</textarea>
	<br>
	<button type="button" id="acceptBtn" onclick="acceptApplication(${gavo.groupId},'${gavo.userId }')">수락하기</button> &nbsp;
	<button type="button" id="rejectBtn" onclick="rejectApplication(${gavo.groupId},'${gavo.userId }')">거절하기</button> &nbsp;
	<button type="button" id="listBtn" onclick="listMaster(${gavo.groupId})">목록으로</button> &nbsp;
	<script type="text/javascript" src="/js/group_manage_master_read.js"></script>
</body>

</html>