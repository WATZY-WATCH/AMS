const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function acceptApplication(page, perPageNum, groupId, userId, applicationId) {
	const data = {
			applicationId : applicationId,
			groupId : groupId,
			userId : userId
		};
	let confirmed = confirm("해당 회원을 스터디 멤버로 영입합니다.");
	if(confirmed) {
		const xhr = new XMLHttpRequest();
		xhr.open("POST", "./masterAccept", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				console.log(xhr.responseText);
				if(xhr.responseText != 0) {
					alert("해당 회원을 스터디 멤버로 수락하였습니다! ");
					document.location.href="/groupManage/master?page="+page+"&perPageNum="+perPageNum+"&groupId="+groupId;
				} else {
					alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
				}
			}
		}
	}
}