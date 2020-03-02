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
</head>
<body>
<h2>신청가능 스터디 목록입니다.</h2>
<section id="container">
	<div>
		<table border="1">
			<tr>
	            <th>카테고리</th>
	            <th>지역</th> 
	            <th>연령대</th>
	        </tr>
	        <tr>
                <td>
              		<c:set var="categoryStr" value=""/>
	                <c:forEach items="${cri.category }" var="item">
	                	<c:set var="categoryStr" value="${categoryStr}${item }"/>
	                </c:forEach>

                	<label><input type="checkbox" name="category" value="ce" <c:out value="${fn:contains(categoryStr,'ce')?'checked':''}"/>> 자격증</label>&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="category" value="co" <c:out value="${fn:contains(categoryStr,'co')?'checked':''}"/>> 건설/설계</label>&nbsp;&nbsp;
					<label><input type="checkbox" name="category" value="me" <c:out value="${fn:contains(categoryStr,'me')?'checked':''}"/>> 미디어/디자인</label>&nbsp;
					<label><input type="checkbox" name="category" value="ma" <c:out value="${fn:contains(categoryStr,'ma')?'checked':''}"/>> 마케팅/기획/영업</label>
					<br>
					<label><input type="checkbox" name="category" value="ci" <c:out value="${fn:contains(categoryStr,'ci')?'checked':''}"/>> 유통/무역</label>
					<label><input type="checkbox" name="category" value="it" <c:out value="${fn:contains(categoryStr,'it')?'checked':''}"/>> IT/연구개발</label>
					<label><input type="checkbox" name="category" value="pr" <c:out value="${fn:contains(categoryStr,'pr')?'checked':''}"/>> 생산/제조/기계</label>
					<label><input type="checkbox" name="category" value="op" <c:out value="${fn:contains(categoryStr,'op')?'checked':''}"/>> 경영/인사/사무</label>
					<br>
					<label><input type="checkbox" name="category" value="of" <c:out value="${fn:contains(categoryStr,'of')?'checked':''}"/>> 공무원</label>&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="category" value="la" <c:out value="${fn:contains(categoryStr,'la')?'checked':''}"/>> 어학</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="category" value="ot" <c:out value="${fn:contains(categoryStr,'ot')?'checked':''}"/>> 기타</label>
					<br>
                </td>
                <td>
	                <c:set var="areaStr" value=""/>
	                <c:forEach items="${cri.area }" var="item">
	                	<c:set var="areaStr" value="${areaStr}${item }"/>
	                </c:forEach>
					<label><input type="checkbox" name="area" value="sez" <c:out value="${fn:contains(areaStr,'sez')?'checked':''}"/>> 서울</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<label><input type="checkbox" name="area" value="gyz" <c:out value="${fn:contains(areaStr,'gyz')?'checked':''}"/>> 경기</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="area" value="inz" <c:out value="${fn:contains(areaStr,'inz')?'checked':''}"/>> 인천</label>&nbsp;&nbsp;
					<br>
					<label><input type="checkbox" name="area" value="gaz" <c:out value="${fn:contains(areaStr,'gaz')?'checked':''}"/>> 강원</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="area" value="daz" <c:out value="${fn:contains(areaStr,'daz')?'checked':''}"/>> 대전/충천</label>
					<label><input type="checkbox" name="area" value="dgz" <c:out value="${fn:contains(areaStr,'dgz')?'checked':''}"/>> 대구</label>
					<br>
					<label><input type="checkbox" name="area" value="buz" <c:out value="${fn:contains(areaStr,'buz')?'checked':''}"/>> 부산/울산</label>
					<label><input type="checkbox" name="area" value="gsz" <c:out value="${fn:contains(areaStr,'gsz')?'checked':''}"/>> 경상</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="area" value="gwz" <c:out value="${fn:contains(areaStr,'gwz')?'checked':''}"/>> 광주/전라/제주</label>
					<br>
                </td>
                <td>
                    <select id="startAge">
                    		<option value="0">---</option>
                        <c:forEach var="i" begin="10" end="70" step="10">
                      		<option value="${i }"<c:out value="${cri.startAge == i ?'selected':''}"/>>${i }</option>
                      	</c:forEach>
                    </select>
                    	대&nbsp;~&nbsp;
                    <select id="endAge">
                    		<option value="0">---</option>
                        <c:forEach var="i" begin="10" end="70" step="10">
                      		<option value="${i }" <c:out value="${cri.endAge == i ?'selected':''}"/>>${i }</option>
                      	</c:forEach>
                    </select>
                    	대	
                </td>
           	</tr>

		</table>
	</div>
	<br>
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
	<br>
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
			<td><a href="./listRead${pageMaker.makeSearch(pageMaker.cri.page)}&groupId=${GroupVO.groupId}">${GroupVO.groupName} 
				<c:if test="${GroupVO.groupCommentCnt > 0}">
					(${GroupVO.groupCommentCnt})
				</c:if>
			</a></td>
			<td>${GroupVO.groupMemberLimit}</td>
			<td>${GroupVO.groupPeriod}</td>
			<td>${GroupVO.groupArea}</td>
			<td>${GroupVO.groupStatus}</td>
			<td>${GroupVO.groupStartAge}대 ~ ${GroupVO.groupEndAge}대</td>
			<td>${GroupVO.groupViewCnt}</td>
			<td>
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupVO.regDate}" />
				</fmt:timeZone>
			</td>
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