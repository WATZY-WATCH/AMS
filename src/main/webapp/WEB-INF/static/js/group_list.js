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

