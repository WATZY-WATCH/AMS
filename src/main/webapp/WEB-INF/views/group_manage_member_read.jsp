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
<title>소속된 스터디 정보</title>
<link rel="stylesheet" href="/css/table.css">
<link rel="stylesheet" href="/css/post.css">
<link rel="stylesheet" href="/css/card.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section class="content-wrapper" style="width:90%;margin:0 auto;">
		<div class="btn-wrapper" style="width:100%;">
			<button type="button" id="listBtn" onclick="listHome()"><i class="material-icons">keyboard_backspace</i>목록으로</button>
			<button type="button" id="leaveGroupBtn" onclick="leaveGroup('${GroupMemberVO.groupId}','${GroupMemberVO.userId}')">그룹 나가기<i class="material-icons">exit_to_app</i></button>
		</div>
		<h2 class="menu-title">GROUP INFORMATION</h2>
		<div class="user-info-wrapper">
			<div class="user-info">
				<p><span class="info-label">권한</span><strong>${GroupMemberVO.groupAuthority}</strong></p>
				<p><span class="info-label">경고점수</span>${GroupMemberVO.demeritCnt}</p>
				<p><span class="info-label">가입일자</span>
					<fmt:timeZone value="Asia/Seoul">
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupMemberVO.regDate}" />
					</fmt:timeZone>
				</p>
			</div>
		</div>
		<div class="group-info">
			<div class="span-wrapper">
				<span class="group-id">${GroupVO.groupId}</span>
				<span class="group-category">${GroupVO.groupCategory}</span>
			</div>
			<p class="group-status <c:if test='${GroupVO.groupStatus eq "모집중" }'>prerunning</c:if><c:if test='${GroupVO.groupStatus eq "진행중" }'>running</c:if>">${GroupVO.groupStatus}</p>
			<p class="group-name">${GroupVO.groupName}</p>
			<div class="sub-info" style="display: inherit;">
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
			<p class="group-area" style="color:#333;"><i class="material-icons info-label">map</i>${GroupVO.groupArea}</p>
			<p class="group-age"><i class="material-icons info-label">grade</i>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
		</div>
		<p class="group-detail"><span>${GroupVO.groupDetail}</span></p>
	</section>
	<script>
		const groupDetail = document.querySelector(".group-detail");
		groupDetail.innerHTML = groupDetail.innerHTML.replace(/(\n|\r\n)/g, '<br>');
	</script>
	<script type="text/javascript" src="/js/group_manage_member_read.js"></script>
</body>

</html>