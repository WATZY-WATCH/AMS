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
<title>스터디 그룹 리스트 조회 </title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<h2>신청가능 스터디 목록입니다.</h2>
<section id="container">
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
		<tr>
			<td>${GroupVO.groupId}</td>
			<td>${GroupVO.groupCategory}</td>
			<td>${GroupVO.userVO.userName}</td>
			<td>${GroupVO.groupName}</td>
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
	</table>
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
	<script>
	const elementToken = document.querySelector('meta[name="_csrf"]');
	const token = elementToken && elementToken.getAttribute("content");
	const elementHeader = document.querySelector('meta[name="_csrf_header"]');
	const header = elementHeader && elementHeader.getAttribute("content");
	
		listComment(1);
		function listComment(curPage){
			const xhr = new XMLHttpRequest();
			xhr.open("GET", "/comment/list?groupId=${GroupVO.groupId}&curPage="+curPage, true);
			xhr.setRequestHeader(header, token);
			
			xhr.send();
			xhr.onload = function () {
				if(xhr.status == 200 || xhr.status == 201) {
					let ret = xhr.responseText;
					const listComment = document.getElementById("listComment");
					listComment.innerHTML = ret;
				}
			}
		}
		
		function deleteComment(commentId, curPage){
			let confirmed = confirm("댓글을 삭제하시겠습니까?");
			if(confirmed) {
				const data={ commentId : commentId , groupId: ${GroupVO.groupId } };
				const xhr = new XMLHttpRequest();
				xhr.open("DELETE", "/comment/delete", true);
				xhr.setRequestHeader(header, token);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.send(JSON.stringify(data));
				xhr.onload = async function () {
					if(xhr.status == 200 || xhr.status == 201) {
						if(Number(xhr.responseText) == 1) {
							alert("삭제되었습니다.")
							let groupCommentCnt= await currentCommentCount(${GroupVO.groupId }, commentId);
							listComment(Math.ceil(groupCommentCnt/10));
						} else {
							alert("에러가 발생했습니다. 잠시후 다시 시도해주세요.");
						}
					}
				}
			};
		}
		var divCommentId;
		var tempComment;
		function updateComment(commentId, curPage){
			console.log("updatecomment");
			divCommentId=document.getElementById("divCommentId"+commentId);
			const pCommentId=document.getElementById("pCommentId"+commentId).innerHTML;
			console.log("p: "+pCommentId);
			tempComment=divCommentId.innerHTML;
			console.log("commentMsg: "+commentMsg);
			divCommentId.innerHTML="<textarea id='updateCommentMsg' cols="+"'80'"+" rows="+"'2'"+" placeholder='내용을 입력해주세요'></textarea>"
								+"<br><button type='button' onclick='updateCommentConfirm("+commentId+","+curPage+")'>확인</button>&nbsp;"
								+"<button type='button' onclick='cancelUpdate()'>취소</button>";
			document.getElementById("updateCommentMsg").value=pCommentId;			
		}
		function cancelUpdate(){
			divCommentId.innerHTML=tempComment;
		}
		function updateCommentConfirm(commentId, curPage){
			let confirmed = confirm("댓글을 수정하시겠습니까?");
			if(confirmed) {
				const commentMsg=document.getElementById("updateCommentMsg").value;	
				const data={ commentId : commentId , commentMsg : commentMsg };
				const xhr = new XMLHttpRequest();
				xhr.open("PUT", "/comment/update", true);
				xhr.setRequestHeader(header, token);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.send(JSON.stringify(data));
				xhr.onload = async function () {
					if(xhr.status == 200 || xhr.status == 201) {
						if(Number(xhr.responseText) == 1) {
							alert("수정되었습니다.")
							let groupCommentCnt= await currentCommentCount(${GroupVO.groupId }, commentId);
							listComment(Math.ceil(groupCommentCnt/10));
						} else {
							alert("에러가 발생했습니다. 잠시후 다시 시도해주세요.");
						}
					}
				}
			}
		}
	</script>
	<script type="text/javascript" src="/js/group_list_read.js"></script>
	<script type="text/javascript" src="/js/group_list.js"></script>
</section>
</body>
</html>