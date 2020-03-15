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
<title>스터디 그룹 리스트 조회 </title>
<link rel="stylesheet" href="/css/table.css">
<link rel="stylesheet" href="/css/post.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
<section id="container">
	<button type="button" id="listBtn" onclick="listBoard(${cri.page},${cri.perPageNum},'${cri.searchType}','${cri.keyword}','${cri.startAge}','${cri.endAge}','${cri.category}','${cri.area}')"><i class="material-icons">
keyboard_backspace
</i> 목록으로</button>
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
			<span class="group-view-cnt"><i class="material-icons">visibility</i> ${GroupVO.groupViewCnt}</span>
		</div>
		<p class="group-member-limit"><i class="material-icons info-label">account_circle</i>${GroupVO.groupMemberCnt } / ${GroupVO.groupMemberLimit}명</p>
		<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
      	<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
		<p class="group-period"><i class="material-icons info-label">turned_in_not</i>${periodStr}회</p>
		<p class="group-area"><i class="material-icons info-label">map</i>${GroupVO.groupArea}</p>
		<p class="group-age"><i class="material-icons info-label">grade</i>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
	</div>
	<p class="group-detail"><span>${GroupVO.groupDetail}</span></p>
	<c:if test="${memberChk eq 0}">	
		<section class="apply-wrapper">
			<h3 class="section-title">가입신청</h3>
			<textarea id="postMsg" rows="5" placeholder="스터디장에게 보낼 메세지를 입력해주세요"></textarea>
			<button type="button" id="applyBtn" onclick="applyGroup(${GroupVO.groupId},'${UserVO.userId}','${UserVO.userName}',${listApplyChk})">신청하기</button>
		</section>
	</c:if>
	<section class="comment-wrapper">
		<h3 class="section-title">댓글</h3>
		<textarea id="commentMsg" rows="2" placeholder="내용을 입력해주세요"></textarea>
		<button type="button" id="commentBtn" onclick="commentCreate(${GroupVO.groupId},'${userId}')">댓글등록</button>
	</section>
	<div id="listComment"></div>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_list_comment.js"></script>
	<script>
		const groupId = ${GroupVO.groupId},
					userId = "${userId}";
		listComment(1);
		
		var divCommentId;
		var tempComment;
	</script>
	<script type="text/javascript" src="/js/group_list_read.js"></script>
	<script type="text/javascript" src="/js/group_list.js"></script>
</section>
</body>
</html>