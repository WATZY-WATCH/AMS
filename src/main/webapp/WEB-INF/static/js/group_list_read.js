const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function listBoard(page, perPageNum) {
	document.location.href="/group/listCri?page="+page+"&perPageNum="+perPageNum;
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

function formChk() {
	const userId = document.getElementById("userId").value;
	const userName = document.getElementById("userName").value;
	const userEmail = document.getElementById("userEmail").value;
	const data = {userId:userId, userName: userName, userEmail: userEmail};
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "./modify", true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader("Content-Type", "application/json");
	if(userName === "") {
		alert("닉네임을 입력해주세요. ");
	} else if(userEmail === "") { 
		alert("이메일을 입력해주세요.");
	} else if(initName !== userName && !nameChked){
		alert("닉네임 중복확인을 해주세요.");
	} else if(initEmail !== userEmail && !emailChked) {
		alert("이메일 중복확인을 해주세요.");
	} else if(saveName!=userName){
		alert("닉네임이 바뀌었습니다. 중복확인을 다시 해주세요.");
	} else if(saveEmail!=userEmail){
		alert("이메일이 바뀌었습니다. 중복확인을 다시 해주세요.");
	} else {
		let confirmed = confirm("정보를 변경하시겠습니까?");
		if(confirmed) {
			xhr.send(JSON.stringify(data));
			xhr.onload = function () {
				if(xhr.status == 200 || xhr.status == 201) {
					console.log(xhr.responseText);
					if(xhr.responseText == 1) {
						alert("성공적으로 변경되었습니다. ");
						document.location.href="/";
					} else {
						alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
					}
				}
			}
		}
	}
}