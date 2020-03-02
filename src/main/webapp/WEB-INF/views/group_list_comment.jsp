<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>스터디 그룹 리스트 </title>
</head>
<body>
	<c:set var="userIdd" value="${userId }">
	</c:set>
	<script>
		var listNum=1;
	</script>
	<ul>
		<c:forEach items="${commentList }" var="c">
		<c:set var="listNum" value="1">
		</c:set>
			<li>
				<p>
					${c.userVO.userName }
					<span>&nbsp; (<fmt:timeZone value="Asia/Seoul"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.regDate}" /></fmt:timeZone>)</span>
				</p>
				<div id="divCommentId${c.commentId}">
				<p id="pCommentId${c.commentId}">${c.commentMsg }</p>
				<c:if test = "${c.regDate ne c.modDate }">
					<span>최종수정날짜 &nbsp; (<fmt:timeZone value="Asia/Seoul"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.modDate}" /></fmt:timeZone>)</span>
				</c:if>
				<c:if test = "${c.userId eq userId }">
					<button type="button" id="updateBtn" onclick="updateComment(${c.commentId}, ${curPage})">수정하기</button>
					<button type="button" id="deleteBtn" onclick="deleteComment(${c.commentId}, ${curPage})">삭제하기</button>
				</c:if>
				</div>
			</li>
		</c:forEach>
	</ul>
	<ul>
		<c:if test="${commentPageMaker.prev}">
			<li><a
				onclick="listComment(${commentPageMaker.startPage-1})" style="cursor: pointer; text-decoration: underline; color: cornflowerblue; ">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${commentPageMaker.startPage }"
			end="${commentPageMaker.endPage }" var="idx">
			<li
				<c:if test="${commentPageMaker.cri.page == idx}"/>>
				<a onclick="listComment(${idx})" style="cursor: pointer; text-decoration: underline; color: cornflowerblue;">${idx}</a>

			</li>
		</c:forEach>
		<c:if test="${commentPageMaker.next && commentPageMaker.endPage > 0}">
			<li><a
				onclick="listComment(${commentPageMaker.endPage+1})" style="cursor: pointer; text-decoration: underline; color: cornflowerblue; ">&raquo;</a></li>
		</c:if>
	</ul>
</body>
</html>