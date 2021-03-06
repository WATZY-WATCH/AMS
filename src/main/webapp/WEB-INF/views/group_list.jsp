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
<title>스터디 그룹 리스트 </title>
<link rel="stylesheet" href="/css/formfield.css">
<link rel="stylesheet" href="/css/table.css">
<link rel="stylesheet" href="/css/card.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<section class="content-wrapper">
		<h2 class="menu-title">STUDY LIST</h2>
		<section id="container">
			<div class="filter-table clearfix">
				<div class="category-section clearfix">
					<p class="section-title">카테고리</p>
					<c:set var="categoryStr" value=""/>
					<c:forEach items="${cri.category }" var="item">
						<c:set var="categoryStr" value="${categoryStr}${item }"/>
					</c:forEach>
					<label class="col-4">
						<input type="checkbox" name="category" value="ce" <c:out value="${fn:contains(categoryStr,'ce')?'checked':''}"/>> 자격증
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="co" <c:out value="${fn:contains(categoryStr,'co')?'checked':''}"/>> 건설/설계
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="me" <c:out value="${fn:contains(categoryStr,'me')?'checked':''}"/>> 미디어/디자인
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="ma" <c:out value="${fn:contains(categoryStr,'ma')?'checked':''}"/>> 마케팅/기획/영업
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="ci" <c:out value="${fn:contains(categoryStr,'ci')?'checked':''}"/>> 유통/무역
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="it" <c:out value="${fn:contains(categoryStr,'it')?'checked':''}"/>> IT/연구개발
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="pr" <c:out value="${fn:contains(categoryStr,'pr')?'checked':''}"/>> 생산/제조/기계
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="op" <c:out value="${fn:contains(categoryStr,'op')?'checked':''}"/>> 경영/인사/사무
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="of" <c:out value="${fn:contains(categoryStr,'of')?'checked':''}"/>> 공무원
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="la" <c:out value="${fn:contains(categoryStr,'la')?'checked':''}"/>> 어학
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="category" value="ot" <c:out value="${fn:contains(categoryStr,'ot')?'checked':''}"/>> 기타
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
				</div>
				<div class="region-section clearfix">
					<p class="section-title">지역</p>
					<c:set var="areaStr" value=""/>
					<c:forEach items="${cri.area }" var="item">
						<c:set var="areaStr" value="${areaStr}${item }"/>
					</c:forEach>
					<label class="col-4">
						<input type="checkbox" name="area" value="sez" <c:out value="${fn:contains(areaStr,'sez')?'checked':''}"/>> 서울
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="gyz" <c:out value="${fn:contains(areaStr,'gyz')?'checked':''}"/>> 경기
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="inz" <c:out value="${fn:contains(areaStr,'inz')?'checked':''}"/>> 인천
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="gaz" <c:out value="${fn:contains(areaStr,'gaz')?'checked':''}"/>> 강원
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="daz" <c:out value="${fn:contains(areaStr,'daz')?'checked':''}"/>> 대전/충청
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="dgz" <c:out value="${fn:contains(areaStr,'dgz')?'checked':''}"/>> 대구
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="buz" <c:out value="${fn:contains(areaStr,'buz')?'checked':''}"/>> 부산/울산
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="gsz" <c:out value="${fn:contains(areaStr,'gsz')?'checked':''}"/>> 경상
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
					<label class="col-4">
						<input type="checkbox" name="area" value="gwz" <c:out value="${fn:contains(areaStr,'gwz')?'checked':''}"/>> 광주/전라/제주
						<svg viewBox="0 0 14 14" class="ic-checkbox">
							<path d="M0 7a7 7 0 0 1 7-7 7 7 0 0 1 7 7 7 7 0 0 1-7 7 7 7 0 0 1-7-7zm6.5 3.5l4.785-5.215-1.257-1.141-3.524 3.524L4.26 5.425 2.846 6.839l2.243 2.243L6.5 10.5z" fill-rule="evenodd"></path>
						</svg>
					</label>
				</div>
				<div class="age-section">
					<p class="section-title">연령</p>
					<div>
						<select class="select-box" id="startAge">
								<option value="0">---</option>
							<c:forEach var="i" begin="10" end="70" step="10">
								<option value="${i }"<c:out value="${cri.startAge == i ?'selected':''}"/>>${i }</option>
							</c:forEach>
						</select>
							대&nbsp;~&nbsp;
						<select class="select-box" id="endAge">
								<option value="0">---</option>
							<c:forEach var="i" begin="10" end="70" step="10">
								<option value="${i }" <c:out value="${cri.endAge == i ?'selected':''}"/>>${i }</option>
							</c:forEach>
						</select>
							대
					</div>
				</div>
			</div>
			<div class="search-table">
				<select class="select-box" id="searchType">
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
				<button id='searchBtn' onclick="searchGroup(${cri.page},${cri.perPageNum})">검색</button>
				<button id='newBtn' onclick="newGroup()">초기화</button>
			</div>
			<div class="board clearfix">
			<c:forEach items="${list}" var="GroupVO">
				<div class="card group-card col-4">
					<div class="clearfix">
						<span class="group-id">${GroupVO.groupId}</span>
						<span class="group-category">${GroupVO.groupCategory}</span>
					</div>
					<p class="group-status <c:if test='${GroupVO.groupStatus eq "모집중" }'>prerunning</c:if><c:if test='${GroupVO.groupStatus eq "진행중" }'>running</c:if>">${GroupVO.groupStatus}</p>
					<p class="group-name"><a class="ellip" href="./listRead${pageMaker.makeSearch(pageMaker.cri.page)}&groupId=${GroupVO.groupId}">${GroupVO.groupName} 
					</a></p>
					<div class="span-wrapper">
						<span class="group-master">${GroupVO.userVO.userName}</span>
						<span class="group-view-cnt"><i class="material-icons">visibility</i> ${GroupVO.groupViewCnt}</span>
					</div>
					<p class="group-area">${GroupVO.groupArea}</p>
					<c:set var = "period" value = "${fn:split(GroupVO.groupPeriod, '_')}" />
					<c:set var = "periodStr" value = "${fn:join(period, ' ')}" />
					<p class="group-period">${periodStr}회</p>
					<p class="group-member-limit">${GroupVO.groupMemberCnt } / ${GroupVO.groupMemberLimit}명</p>
					<p class="group-age">${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</p>
					<div class="sub-info">
						<i class="material-icons">
							chat_bubble_outline
							<span>${GroupVO.groupCommentCnt}</span>
						</i>
						<span class="group-reg-date">
							<fmt:timeZone value="Asia/Seoul">
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
							</fmt:timeZone>
						</span>
					</div>
				</div>
			</c:forEach>
			</div>
			<div class="pagination-wrapper">
				<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="page-num"><a
						href="./listCri${pageMaker.makeSearch(pageMaker.startPage-1) }">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="idx">
					<li class="page-num<c:if test='${pageMaker.cri.page == idx}'> active</c:if>">
						<a href="./listCri${pageMaker.makeSearch(idx)}">${idx}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-num"><a
						href="./listCri${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a></li class="page-num">
				</c:if>
			</ul>
			</div>
		</section>
	</section>	
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/group_list.js"></script>
</body>
</html>