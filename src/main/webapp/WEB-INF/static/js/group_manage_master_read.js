const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function acceptApplication(groupId, userId) {
	let query = new URLSearchParams(location.search),
		page = query.get("page"),
		perPageNum = query.get("perPageNum"),
		applicationId = query.get("applicationId");
	
	const data = {
			applicationId : applicationId,
			groupId : groupId,
			userId : userId
		};
	let confirmed = confirm("해당 회원을 스터디 멤버로 영입합니다.");
	if(confirmed) {
		const data2 = {
				groupId : groupId
		};
		const xhr2 = new XMLHttpRequest();
		xhr2.open("POST", "./master/application", true);
		xhr2.setRequestHeader(header, token);
		xhr2.setRequestHeader("Content-Type", "application/json");
		xhr2.send(JSON.stringify(data2));
		xhr2.onload = function () {
			if(xhr2.status == 200 || xhr2.status == 201) {
				if(Number(xhr2.responseText) == 1) {
					const xhr = new XMLHttpRequest();
					xhr.open("POST", "./master", true);
					xhr.setRequestHeader(header, token);
					xhr.setRequestHeader("Content-Type", "application/json");
					xhr.send(JSON.stringify(data));
					xhr.onload = function () {
						if(xhr.status == 200 || xhr.status == 201) {
							console.log(xhr.responseText);
							if(Number(xhr.responseText) >= 1) {
								alert("해당 회원을 스터디 멤버로 수락하였습니다! ");
								document.location.href="/groupManage/master?groupId="+groupId;
							} else {
								alert("에러가 발생하였습니다. 잠시후 다시 시도해주세요.");
							}
						}
					}
				} else {
					alert("현재멤버가 가득 찼습니다.");
				}
			}
		}
		
	}
}

function rejectApplication(groupId, userId) {
	let query = new URLSearchParams(location.search),
		page = query.get("page"),
		perPageNum = query.get("perPageNum"),
		applicationId = query.get("applicationId");
	
	const data = {
			applicationId : applicationId,
			groupId : groupId,
			userId : userId
		};
	let confirmed = confirm("해당 회원의 신청을 거절합니다.");
	if(confirmed) {
		const xhr = new XMLHttpRequest();
		xhr.open("DELETE", "./master/application", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				console.log(xhr.responseText);
				if(xhr.responseText != 0) {
					alert("해당 회원을 거절하였습니다! ");
					document.location.href="/groupManage/master?groupId="+groupId;
				} else {
					alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
				}
			}
		}
	}
}

function listMaster(groupId) {
	let query = new URLSearchParams(location.search),
		page = query.get("page"),
		perPageNum = query.get("perPageNum");
	
	document.location.href="/groupManage/master?page="+page+"&perPageNum="+perPageNum+"&groupId="+groupId;
}
