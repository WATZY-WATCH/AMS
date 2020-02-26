//const elementToken = document.querySelector('meta[name="_csrf"]');
//const token = elementToken && elementToken.getAttribute("content");
//const elementHeader = document.querySelector('meta[name="_csrf_header"]');
//const header = elementHeader && elementHeader.getAttribute("content");

function searchGroup(page, perPageNum) {
	const searchType = document.getElementById("searchType").value;
	const keyword = document.getElementById("keywordInput").value;
	document.location.href="/group/listCri?page="+page+"&perPageNum="+perPageNum+"&searchType="+searchType+"&keyword="+keyword;
}

function newGroup() {
	document.location.href="/group/listCri";
}
