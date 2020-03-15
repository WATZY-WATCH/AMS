function commentTemplate (userId, commentList) {
	let commentSection = document.createElement("section");
	for(let comment of commentList) {
		let div = document.createElement("div");
			
		let	userName="";
		if(comment.userVO == undefined) userName="탈퇴한 사용자";
		else userName = comment.userVO.userName;
		let	regDate = new Date(comment.regDate),
			modDate = new Date(comment.modDate),
			regDateStr = regDate.format("yyyy/MM/dd HH:mm"),
			modDateStr = modDate.format("yyyy/MM/dd HH:mm"),
			commentId = comment.commentId,
			curPage = comment.curPage;

		let userNameNode = createNode("p", userName),
			regDateNode = createNode("span", regDateStr),
			content = createNode("p", comment.commentMsg);
		
		content.classList.add("comment-content");
		userNameNode.classList.add("user-name");
		regDateNode.classList.add("reg-date");
		
		userNameNode.appendChild(regDateNode);
		div.appendChild(userNameNode);
		
		if(userId === comment.userId) {
			let modBtn = document.createElement("button"),
				delBtn = document.createElement("button");
			modBtn.innerHTML = "<i class='material-icons'>edit</i>";
			delBtn.innerHTML = "<i class='material-icons'>delete</i>";
			modBtn.onclick = function (e) {
				updateComment(e, commentId, curPage);
			}
			delBtn.onclick = function () {
				deleteComment(commentId, curPage);
			}
			modBtn.classList.add("mod-btn");
			delBtn.classList.add("del-btn");
			div.appendChild(modBtn);
			div.appendChild(delBtn);
		}
		
		div.appendChild(content);
		
		if(regDate < modDate) {
			let modDateNode = createNode("span", "수정됨 " + modDateStr);
			modDateNode.classList.add("mod-date");
			div.appendChild(modDateNode);
		}
		
		div.commentId = commentId;
		div.classList.add("comment");
		
		commentSection.appendChild(div);
	}
	return commentSection;
}

function createNode(elName, content) {
	let el = document.createElement(elName),
		text = document.createTextNode(content);
	el.appendChild(text);
	return el;
}

function listComment(curPage){
	const xhr = new XMLHttpRequest();
	xhr.open("GET", "/comment/" + groupId + "/"+curPage, true);
	xhr.setRequestHeader(header, token);
	
	xhr.send();
	xhr.onload = function () {
		if(xhr.status == 200 || xhr.status == 201) {
			let ret = JSON.parse(xhr.response);
			const listComment = document.getElementById("listComment");
			listComment.innerHTML = "";
			listComment.appendChild(commentTemplate(userId, ret.commentList));
			listComment.appendChild(pagination(curPage, ret.commentPageMaker));
		}
	}
}

function updateComment(e, commentId, curPage){
	console.log("updatecomment");
	const target = e.target.parentNode;
	let comment = target.parentNode,
		commentChild = comment.childNodes,
		content;
	
	const initContent = [];
	
	for(let i=0; i<commentChild.length; i++) {
		let evt = commentChild[i].onclick,
			cloneNode = commentChild[i].cloneNode(true);
		cloneNode.onclick = evt;
		initContent.push(cloneNode);
	}
	for(let i=0; i<commentChild.length; i++) {
		if(commentChild[i].classList.contains("comment-content")) {
			content = commentChild[i];
			break;
		}
	}
	comment.innerHTML="<textarea id='updateCommentMsg' placeholder='내용을 입력해주세요'></textarea>"
						+"<br>";
	let updateBtn = createNode("button", "확인"),
		cancelBtn = createNode("button", "취소");
	updateBtn.classList.add("update-btn");
	cancelBtn.classList.add("cancel-btn");
	updateBtn.onclick = function (e) {
		updateCommentConfirm(e, commentId, curPage);
	}
	cancelBtn.onclick = function () {
		cancelUpdate(comment, initContent);
	}
	
	comment.appendChild(updateBtn);
	comment.appendChild(cancelBtn);
	document.getElementById("updateCommentMsg").value= content.innerText;
}

function cancelUpdate(target, content){
	target.innerHTML="";
	for(let i=0; i<content.length; i++) {
		target.appendChild(content[i]);
	}
}

function updateCommentConfirm(e, commentId, curPage){
	let confirmed = confirm("댓글을 수정하시겠습니까?");
	if(confirmed) {
		const commentMsg=document.getElementById("updateCommentMsg").value;	
		const data={ commentId : commentId , commentMsg : commentMsg };
		const xhr = new XMLHttpRequest();
		xhr.open("PUT", "/comment/"+commentId+"/"+commentMsg, true);
		xhr.setRequestHeader(header, token);
		xhr.send();
		xhr.onload = async function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(xhr.responseText == 'SUCCESS') {
					alert("수정되었습니다.");
					let groupCommentCnt= await currentCommentCount(groupId, commentId);
					listComment(Math.ceil(groupCommentCnt/10));
				} else {
					alert("에러가 발생했습니다. 잠시후 다시 시도해주세요.");
				}
			}
		}
	}
}

function deleteComment(commentId, curPage){
	let confirmed = confirm("댓글을 삭제하시겠습니까?");
	if(confirmed) {
		const data={ commentId : commentId , groupId: groupId };
		const xhr = new XMLHttpRequest();
		xhr.open("DELETE", "/comment/"+commentId+"/"+groupId, true);
		xhr.setRequestHeader(header, token);
		xhr.send();
		xhr.onload = async function () {
			if(xhr.status == 200 || xhr.status == 201) {
				if(xhr.responseText == 'SUCCESS') {
					alert("삭제되었습니다.")
					let groupCommentCnt= await currentCommentCount(groupId, commentId);
					listComment(Math.ceil(groupCommentCnt/10));
				} else {
					alert("에러가 발생했습니다. 잠시후 다시 시도해주세요.");
				}
			}
		}
	};
}

function pagination(curPage, pageMaker) {
	let div = document.createElement("div"),
		ul = document.createElement("ul"),
		startPage = pageMaker.startPage,
		endPage = pageMaker.endPage;
	
	div.classList.add("pagination-wrapper");
	ul.classList.add("pagination");
	
	if(pageMaker.prev) {
		let li = createNode("li", "<<");
		li.classList.add("page-num");
		li.onclick = function () {
			let active = document.querySelector(".pagination .active");
			active.classList.remove("active");
			li.classList.add("active");
			listComment(startPage-1);
		}
		ul.appendChild(li);
	}
	for(let i=startPage; i<=endPage; i++) {
		let li = createNode("li", i);
		li.classList.add("page-num");
		if(i === curPage) li.classList.add("active");
		li.onclick = function () {
			let active = document.querySelector(".pagination .active");
			active.classList.remove("active");
			li.classList.add("active");
			listComment(i);
		}
		ul.appendChild(li);
	}
	if(pageMaker.next) {
		let li = createNode("li", ">>");
		li.classList.add("page-num");
		li.onclick = function () {
			let active = document.querySelector(".pagination .active");
			active.classList.remove("active");
			li.classList.add("active");
			listComment(endPage+1);
		}
		ul.appendChild(li);
	}
	div.appendChild(ul);
	return div;
}