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
<title>스터디 그룹 운영 </title>
<link rel="stylesheet" href="/css/table.css">
<link rel="stylesheet" href="/css/card.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section class="content-wrapper">
		<div class="btn-wrapper">
			<button type="button" id="listBtn" onclick="listHome()"><i class="material-icons">keyboard_backspace</i>목록으로</button>
			<button type="button" id="leaveGroupBtn" onclick="leaveGroup(${groupId },'${userId }')">그룹 나가기<i class="material-icons">exit_to_app</i></button>
		</div>
		<h2 class="menu-title">APPLICATION LIST</h2>
		<section class="application-list">
		<c:forEach items="${gavoList}" var="GroupApplicationVO">
			<div class="card application-card">
				<p class="user-name ellip">${GroupApplicationVO.userVO.userName}</p>
				<p class="application-content"><a class="ellip" href="/groupManage/masterRead${pageMaker.makeQuery(pageMaker.cri.page)}&groupId=${GroupApplicationVO.groupId }&applicationId=${GroupApplicationVO.applicationId}">${GroupApplicationVO.msg}</a></p>
				<p class="reg-date">
					<fmt:timeZone value="Asia/Seoul">
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupApplicationVO.regDate}" />
					</fmt:timeZone>
				</p>
			</div>
		</c:forEach>
		</section>
		<div class="pagination-wrapper">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="page-num"><a
						href="/groupManage/master${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="idx">
					<li class="page-num<c:if test='${pageMaker.cri.page == idx}'> active</c:if>">
						<a href="/groupManage/master${pageMaker.makeQuery(idx)}&groupId=${groupId}">${idx}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-num"><a
						href="/groupManage/master${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</section>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_manage_master.js"></script>
</body>
</html>