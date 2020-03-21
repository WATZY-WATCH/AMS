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
<link rel="stylesheet" href="/css/post.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section class="content-wrapper" style="width:90%;margin:0 auto;">
		<button type="button" id="listBtn" onclick="listHome()"><i class="material-icons">keyboard_backspace</i>목록으로</button>
		<h2 class="menu-title">APPLICATION</h2>
			<div class="group-info">
				<div class="span-wrapper">
					<span class="group-id">${GroupVO.groupId}</span>
					<span class="group-category">${GroupVO.groupCategory}</span>
				</div>
				<p class="group-status <c:if test='${GroupVO.groupStatus eq "모집중" }'>prerunning</c:if><c:if test='${GroupVO.groupStatus eq "진행중" }'>running</c:if>">${GroupVO.groupStatus}</p>
				<p class="group-name">${GroupVO.groupName}</p>
				<div class="sub-info">
					<span class="group-master">${GroupVO.userVO.userName}&nbsp;</span>
					<span><i class="material-icons">query_builder</i>
						<fmt:timeZone value="Asia/Seoul">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
						</fmt:timeZone>
						&nbsp;
					</span>
				</div>
				<p class="group-member-limit"><i class="material-icons info-label">account_circle</i>${GroupVO.groupMemberCnt } / ${GroupVO.groupMemberLimit}명</p>
				<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
				<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
				<p class="group-period"><i class="material-icons info-label">turned_in_not</i>${periodStr}회</p>
				<p class="group-area"><i class="material-icons info-label">map</i>${GroupVO.groupArea}</p>
				<p class="group-age"><i class="material-icons info-label">grade</i>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
			</div>
			<p class="group-detail"><span>${GroupVO.groupDetail}</span></p>
		<h2 class="info-msg-wrapper">
			<span class="info-msg">회원님이 작성한 신청서 내용입니다</span>
		</h2>
		<p id="applicationMsg"><span>${GroupApplicationVO.msg }</span></p>
		<button type="button" id="applicationDeleteBtn" onclick="applicationDelete('${GroupApplicationVO.groupId}','${GroupApplicationVO.userId}')">삭제하기</button>
	</section>
	<script type="text/javascript" src="/js/group_manage_application_read.js"></script>
</body>

</html>