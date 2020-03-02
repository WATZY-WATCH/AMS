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
<style type="text/css">
	.ellip {
		width        : 100px;    
  		text-overflow: ellipsis;  
  		white-space  : nowrap;   
  		overflow     : hidden;    
  		display      : block;     
	}
</style>
</head>
<body>
	<h2> 스터디를 신청한 인원 목록입니다.</h2>
	<table border="1">
		<tr>
			<th>닉네임</th>
			<th>신청서 내용</th>
			<th>신청 날짜</th>
		</tr>
	<c:forEach items="${gavoList}" var="GroupApplicationVO">
		<tr>
			<td>${GroupApplicationVO.userVO.userName}</td>
			<td><a class="ellip" href="/groupManage/masterRead${pageMaker.makeQuery(idx)}&applicationId=${GroupApplicationVO.applicationId}">${GroupApplicationVO.msg}</a></td>
			<td>
				<fmt:timeZone value="Asia/Seoul">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${GroupApplicationVO.regDate}" />
				</fmt:timeZone>
			</td>
		</tr>
	</c:forEach>
	</table>
	<ul>
		<c:if test="${pageMaker.prev}">
			<li><a
				href="/groupManage/master${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPage }"
			end="${pageMaker.endPage }" var="idx">
			<li
				<c:out value="${pageMaker.cri.page == idx}"/>>
				<a href="/groupManage/master${pageMaker.makeQuery(idx)}&groupId=${groupId}">${idx}</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a
				href="/groupManage/master${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
		</c:if>
	</ul>
	<br>
</body>
</html>