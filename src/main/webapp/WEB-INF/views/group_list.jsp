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
<h2>신청가능 스터디 목록입니다.</h2>
<section id="container">
	<div>
		<select id="searchType">
			<option value="n"
				<c:out value="${cri.searchType == null?'selected':''}"/>>
			---</option>
			<option value="t"
				<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
			제목</option>
			<option value="c"
				<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
			내용</option>
			<option value="w"
				<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
			작성자</option>
			<option value="tc"
				<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
			제목+내용</option>
			<option value="cw"
				<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
			내용+작성자</option>
			<option value="tcw"
				<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
			제목+내용+작성자</option>
		</select>
		
		<input type="text" id="keywordInput" value='${cri.keyword }'>
		<button id='searchBtn' onclick="searchGroup(${cri.page},${cri.perPageNum})">Search</button>
		<button id='newBtn' onclick="newGroup()">New Board</button>
	</div>

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
	<c:forEach items="${list}" var="GroupVO">
		<tr>
			<td>${GroupVO.groupId}</td>
			<td>${GroupVO.groupCategory}</td>
			<td>${GroupVO.userVO.userName}</td>
			<td><a href="./listRead${pageMaker.makeSearch(pageMaker.cri.page)}&groupId=${GroupVO.groupId}">${GroupVO.groupName}</a></td>
			<td>${GroupVO.groupMemberLimit}</td>
			<td>${GroupVO.groupPeriod}</td>
			<td>${GroupVO.groupArea}</td>
			<td>${GroupVO.groupStatus}</td>
			<td>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</td>
			<td>${GroupVO.groupViewCnt}</td>
			<td><fmt:setTimeZone value="UTC" />
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" /></td>
		</tr>
	</c:forEach>
	</table>
	<ul>
		<c:if test="${pageMaker.prev}">
			<li><a
				href="./listCri${pageMaker.makeSearch(pageMaker.startPage-1) }">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPage }"
			end="${pageMaker.endPage }" var="idx">
			<li
				<c:out value="${pageMaker.cri.page == idx}"/>>
				<a href="./listCri${pageMaker.makeSearch(idx)}">${idx}</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a
				href="./listCri${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a></li>
		</c:if>
	</ul>
	<script type="text/javascript" src="/js/group_list.js"></script>
</section>
</body>
</html>