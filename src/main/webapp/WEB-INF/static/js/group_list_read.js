function listBoard(page, perPageNum, searchType, keyword, startAge, endAge, category, area) {
	var url="/group/listCri?page="+page+"&perPageNum="+perPageNum+"&searchType="+searchType+"&keyword="+keyword+"&startAge="+startAge+"&endAge="+endAge;
	if(category!=""){
		category=category.substring(1, category.length-1).split(", ").join(",");
		url+="&category="+category;
	}
	if(area!=""){
		area=area.substring(1, area.length-1).split(", ").join(",");
		url+="&area="+area;
	}
	document.location.href=url;
}

var listApplyFlag=0;
function applyGroup(groupId, userId, userName, listApplyChk) {
	if(listApplyChk>0||listApplyFlag>0){
		alert("이미 해당 스터디 그룹장에게 신청서를 보냈습니다. 스터디 관리에서 확인해주세요!");
		return;
	}
	const postMsg = document.getElementById("postMsg").value;
	const data = { 
			groupId : groupId, 
			userId : userId, 
			userName : userName,
			 };
	const xhr = new XMLHttpRequest();
	let confirmed = confirm("스터디 그룹장에게 가입신청을 보내시겠습니까?");
	if(postMsg != "") {
		data.msg=postMsg;
	}
	if(confirmed) {
		xhr.open("POST", "./listApply", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(Number(xhr.responseText) >= 1) {
					alert("그룹장에게 스터디 가입 신청을 보냈습니다!");
					listApplyFlag++;
				} else {
					alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
				}
			}
		}
	}
}

function commentCreate(groupId, userId) {
	const commentMsg = document.getElementById("commentMsg").value;
	const data = {
			groupId : groupId,
			userId : userId,
			commentMsg : commentMsg
		};
	if(commentMsg === "") {
		alert("댓글 내용을 입력해주세요.");
	} else {
		const xhr = new XMLHttpRequest();
		xhr.open("POST", "/comment/create", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				alert("댓글이 등록되었습니다. ");
				document.getElementById("commentMsg").value="";
				groupCommentCnt++;
				console.log("Math.ceil(groupCommentCnt/10): "+Math.ceil(groupCommentCnt/10));
				listComment(Math.ceil(groupCommentCnt/10));
			}
		}
	}
}