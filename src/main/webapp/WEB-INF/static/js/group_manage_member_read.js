const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function leaveGroup(groupId, userId) {
	const data = {
			groupId : groupId,
			userId : userId,
		};
	let confirmed = confirm("그룹을 나가면 다시 신청해 그룹장의 신청을 받아야만 합니다. 정말 나가시겠습니까?");
	if(confirmed) {
		const xhr = new XMLHttpRequest();
		xhr.open("DELETE", "./member", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(xhr.responseText != 0) {
					alert("해당 그룹을 탈퇴하였습니다!");
					document.location.href="/groupManage/home";
				} else {
					alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
				}
			}
		}
	}
}

function listHome() {
	document.location.href="/groupManage/home";
}
