const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function applicationDelete(groupId, userId) {
	const data = {
			groupId : groupId,
			userId : userId,
		};
	let confirmed = confirm("신청서를 삭제하시겠습니까?");
	if(confirmed) {
		const xhr = new XMLHttpRequest();
		xhr.open("POST", "./applicationDelete", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(xhr.responseText != 0) {
					alert("신청서가 삭제되었습니다!");
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
