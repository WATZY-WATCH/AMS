function leaveGroup(groupId, userId) {
	const data = {
			groupId : groupId,
			userId : userId,
		};
	let confirmed = confirm("그룹을 나갈 경우 가장 오래된 멤버에게 그룹장의 권한을 양도하게 됩니다.그룹을 나가시겠습니까?");
	if(confirmed) {
		const xhr = new XMLHttpRequest();
		xhr.open("DELETE", "./master", true);
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

var cardList = document.querySelectorAll(".card");
cardList.forEach(el=> {
	var groupLink = el.querySelector(".application-content > a");
	el.onclick = function() {
		location.href = groupLink.href;
	}
	if(!mobile) {
		el.onmouseover = function() {
			groupLink.style.color = "#EF978F";
		}
		el.onmouseout = function() {
			groupLink.style.color = "#333";
		}
	}
})