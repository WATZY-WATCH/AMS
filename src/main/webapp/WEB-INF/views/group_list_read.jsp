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
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
<section id="container">
	<div class="group-info">
		<p class="group-id">${GroupVO.groupId}</p>
		<p class="group-category">${GroupVO.groupCategory}</p>
		<p class="group-master">${GroupVO.userVO.userName}</p>
		<p class="group-name">${GroupVO.groupName}</p>
		<p class="group-member-limit">${GroupVO.groupMemberLimit}</p>
		<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
      	<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
		<p class="group-period">${periodStr}회</p>
		<p class="group-area">${GroupVO.groupArea}</p>
		<p class="group-status">${GroupVO.groupStatus}</p>
		<p class="group-age">${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
		<p class="group-view-cnt">${GroupVO.groupViewCnt}</p>
		<p>
			<fmt:timeZone value="Asia/Seoul">
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
			</fmt:timeZone>
		</p>
	</div>
	<h2>스터디 활동 소개 ${memberChk}</h2>
	<textarea name="groupDetail" cols="80" rows="10" readonly="readonly">${GroupVO.groupDetail}</textarea>
	<c:if test="${memberChk eq 0}">
		<h2>스터디장에게 보낼 메세지를 입력해주세요.</h2>
		<div>
			<textarea id="postMsg" cols="80" rows="5"></textarea>
		</div>
		<button type="button" id="applyBtn" onclick="applyGroup(${GroupVO.groupId},'${UserVO.userId}','${UserVO.userName}',${listApplyChk})">신청하기</button>
	</c:if>
	<button type="button" id="listBtn" onclick="listBoard(${cri.page},${cri.perPageNum},'${cri.searchType}','${cri.keyword}','${cri.startAge}','${cri.endAge}','${cri.category}','${cri.area}')">목록으로</button>
	<div>	
		<h2>댓글을 입력해주세요.</h2>
		<label for="commentMsg">메세지를 작성해주세요</label>
		<textarea id="commentMsg" cols="80" rows="2" placeholder="내용을 입력해주세요"></textarea>
		<button type="button" id="commentBtn" onclick="commentCreate(${GroupVO.groupId},'${userId}')">댓글등록</button>
	</div>	

	
	<h2>댓글 목록입니다.</h2>
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