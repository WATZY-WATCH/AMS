function searchGroup(page, perPageNum) {
	var categoryArray = new Array();
	var categoryString = "";
	const category = document.getElementsByName("category");
	category.forEach(function(e){
		if(e.checked){
			categoryArray.push(e.value);
		}
	});
	categoryString = categoryArray.join(",");
	
	var areaArray = new Array();
	var areaString = "";
	const area = document.getElementsByName("area");
	area.forEach(function(e){
		if(e.checked){
			areaArray.push(e.value);
		}
	});
	areaString = areaArray.join(","); 
	const startAge=document.getElementById("startAge").value;
	const endAge=document.getElementById("endAge").value;
	if(Number(startAge)>Number(endAge)){
		alert("연령대의 범위가 잘못되었습니다. 다시 확인해주세요.");
		return;
	}
	const searchType = document.getElementById("searchType").value;
	const keyword = document.getElementById("keywordInput").value;
	document.location.href="/group/listCri?page=1&perPageNum="+perPageNum+"&searchType="+searchType+"&keyword="+keyword
	+ "&startAge="+startAge + "&endAge=" +endAge
	+ (categoryString.length ? "&category=" + categoryString : "") +(areaString.length ? "&area=" + areaString : "");
}
function newGroup() {
	document.location.href="/group/listCri";
}

const mobile = isMobile();
var cardList = document.querySelectorAll(".card");
cardList.forEach(el=> {
	var groupLink = el.querySelector(".group-name > a");
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

var paging = document.querySelectorAll(".page-num");
paging.forEach(el=> {
	var pageLink = el.querySelector("a");
	const active = el.classList.contains("active");
	el.onclick = function() {
		location.href = pageLink.href;
	}
	if(!mobile && !active) {
		el.onmouseover = function() {
			pageLink.style.color = "#EF978F";
		}
		el.onmouseout = function() {
			pageLink.style.color = "#666";
		}
	}
})