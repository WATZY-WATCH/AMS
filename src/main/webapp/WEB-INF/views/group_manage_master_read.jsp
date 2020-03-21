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
<link rel="stylesheet" href="/css/table.css">
<link rel="stylesheet" href="/css/application.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section class="content-wrapper">
		<button type="button" id="listBtn" onclick="listMaster(${gavo.groupId})"><i class="material-icons">keyboard_backspace</i>목록으로</button>
		<h2 class="menu-title">APPLICATION</h2>
		<section class="application">
			<div class="user-info">
				<p class="user-name">${gavo.userVO.userName}</p>
				<p class="user-msg"><span>${gavo.msg }</span></p>
				<p class="reg-date">
					<fmt:timeZone value="Asia/Seoul">
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${gavo.regDate}" />
					</fmt:timeZone>
				</p>
			</div>
			<div class="btn-wrapper">
				<button type="button" id="acceptBtn" onclick="acceptApplication(${gavo.groupId},'${gavo.userId }')">수락하기</button>
				<button type="button" id="rejectBtn" onclick="rejectApplication(${gavo.groupId},'${gavo.userId }')">거절하기</button>
			</div>
		</section>
	</section>
	<script type="text/javascript" src="/js/group_manage_master_read.js"></script>
</body>
</html>