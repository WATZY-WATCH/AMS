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
<link rel="stylesheet" href="/css/list.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section class="study-section content-wrapper">
		<article class="admin-study">
			<h2 class="list-title">운영 스터디 목록</h2>
			<c:forEach items="${masterList}" var="GroupVO">
				<div class="study">
					<div class="sub-info">
						<p class="group-id">${GroupVO.groupId}</p>
						<p class="group-category">${GroupVO.groupCategory}</p>
					</div>
					<div class="sub-info">
						<p class="group-status <c:if test='${GroupVO.groupStatus eq "모집중" }'>prerunning</c:if><c:if test='${GroupVO.groupStatus eq "진행중" }'>running</c:if>">${GroupVO.groupStatus}</p>
						<p class="group-member-limit">${GroupVO.groupMemberCnt } / ${GroupVO.groupMemberLimit}명</p>
					</div>
					<p class="group-name"><a href="groupManage/master?groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></p>
					<p class="group-master">${GroupVO.userVO.userName}</p>
					<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
			    	<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
					<span class="group-area">${GroupVO.groupArea}</span>
					<span>•</span>
					<span class="group-period">${periodStr}회</span>
					<p class="group-age">${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
					<p class="group-reg-date">
						<fmt:timeZone value="Asia/Seoul">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
						</fmt:timeZone>
					</p>
					<p class="group-schedule"><a href="/schedule?groupId=${GroupVO.groupId }">일정관리</a></p>
				</div>
			</c:forEach>
		</article>
		<article class="member-study">
			<h2 class="list-title"> 소속 스터디 목록</h2>
			<c:forEach items="${memberList}" var="GroupVO">
				<div class="study">
					<div class="sub-info">
						<p class="group-id">${GroupVO.groupId}</p>
						<p class="group-category">${GroupVO.groupCategory}</p>
					</div>
					<div class="sub-info">
						<p class="group-status <c:if test='${GroupVO.groupStatus eq "모집중" }'>prerunning</c:if><c:if test='${GroupVO.groupStatus eq "진행중" }'>running</c:if>">${GroupVO.groupStatus}</p>
						<p class="group-member-limit">${GroupVO.groupMemberCnt } / ${GroupVO.groupMemberLimit}명</p>
					</div>
					<p class="group-name"><a href="memberRead?groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></p>
					<p class="group-master">${GroupVO.userVO.userName}</p>
					<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
			    	<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
					<span class="group-area">${GroupVO.groupArea}</span>
					<span>•</span>
					<span class="group-period">${periodStr}회</span>
					<p class="group-age">${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
					<p class="group-reg-date">
						<fmt:timeZone value="Asia/Seoul">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
						</fmt:timeZone>
					</p>
					<p class="group-schedule"><a href="/schedule?groupId=${GroupVO.groupId }">일정관리</a></p>
				</div>
			</c:forEach>
		</article>
		<article class="apply-study">
			<h2 class="list-title">신청 스터디 목록</h2>
			<c:forEach items="${applicationList}" var="GroupVO">
				<div class="study">
					<div class="sub-info">
						<p class="group-id">${GroupVO.groupId}</p>
						<p class="group-category">${GroupVO.groupCategory}</p>
					</div>
					<div class="sub-info">
						<p class="group-status <c:if test='${GroupVO.groupStatus eq "모집중" }'>prerunning</c:if><c:if test='${GroupVO.groupStatus eq "진행중" }'>running</c:if>">${GroupVO.groupStatus}</p>
						<p class="group-member-limit">${GroupVO.groupMemberCnt } / ${GroupVO.groupMemberLimit}명</p>
					</div>
					<p class="group-name"><a href="applicationRead?groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></p>
					<p class="group-master">${GroupVO.userVO.userName}</p>
					<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
			    	<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
					<span class="group-area">${GroupVO.groupArea}</span>
					<span>•</span>
					<span class="group-period">${periodStr}회</span>
					<p class="group-age">${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
					<p class="group-reg-date">
						<fmt:timeZone value="Asia/Seoul">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
						</fmt:timeZone>
					</p>
				</div>
			</c:forEach>
		</article>
	</section>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_manage_home.js"></script>
</body>
</html>