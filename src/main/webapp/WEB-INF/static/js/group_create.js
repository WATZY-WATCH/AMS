const elementToken = document.querySelector('meta[name="_csrf"]');
const token = elementToken && elementToken.getAttribute("content");
const elementHeader = document.querySelector('meta[name="_csrf_header"]');
const header = elementHeader && elementHeader.getAttribute("content");

function periodSelect(){
	const groupPeriod=document.getElementById("groupPeriod");
	const PeriodOption=groupPeriod.options[groupPeriod.selectedIndex].value;
	
	if(PeriodOption=='월'){
		document.getElementById('groupMonth').style.display="inline-block";
		document.getElementById('groupWeek').style.display="none";
	}else{
		document.getElementById('groupWeek').style.display="inline-block";
		document.getElementById('groupMonth').style.display="none";
	}
}

function formChk() {
	const form = document.getElementById("groupCreateForm");
	const groupName=document.getElementById("groupName").value;
	const groupDetail=document.getElementById("groupDetail").value;
	const startAge=document.getElementById("startAge");
	const startAgeValue=startAge.options[startAge.selectedIndex].value;
	const endAge=document.getElementById("endAge");
	const endAgeValue=endAge.options[endAge.selectedIndex].value;
	const groupCategory=document.getElementById("groupCategory");
	const groupCategoryValue=groupCategory.options[groupCategory.selectedIndex].value;
	const groupArea=document.getElementById("groupArea");
	const groupAreaValue=groupArea.options[groupArea.selectedIndex].value;
	const groupLimit=document.getElementById("groupLimit");
	const groupLimitValue=groupLimit.options[groupLimit.selectedIndex].value;
	const groupPeriod=document.getElementById("groupPeriod");
	const groupPeriodValue=groupPeriod.options[groupPeriod.selectedIndex].value;
	const groupMonth=document.getElementById("groupMonth");
	const groupMonthValue=groupMonth.options[groupMonth.selectedIndex].value;
	const groupWeek=document.getElementById("groupWeek");
	const groupWeekValue=groupWeek.options[groupWeek.selectedIndex].value;
	
	const data = {
			groupCategory : groupCategoryValue,
			groupName : groupName, 
			groupDetail : groupDetail,
			groupMemberLimit : groupLimitValue,
			groupArea : groupAreaValue,
			groupStartAge : startAgeValue,
			groupEndAge : endAgeValue
		};
	
	if(groupName === "") {
		alert("스터디 이름을 입력해주세요.");
	} else if(groupDetail === "") {
		alert("스터디 소개글을 작성해주세요.");
	} else if(Number(startAgeValue)>Number(endAgeValue)) {
		alert("연령대의 범위를 확인해주세요.");
	} else {
		const xhr = new XMLHttpRequest();
		xhr.open("POST", "./create", true);
		xhr.setRequestHeader(header, token);
		xhr.setRequestHeader("Content-Type", "application/json");
		if(groupPeriodValue=='월') data.groupPeriod="월_"+ groupMonthValue;
		else data.groupPeriod="주_"+ groupWeekValue;
		xhr.send(JSON.stringify(data));
		xhr.onload = function () {
			if(xhr.status == 200 || xhr.status == 201) {
				console.log(xhr.responseText);
				if(xhr.responseText != 0) {
					alert("성공적으로 생성하였습니다. ");
					document.location.href="/";
				} else {
					alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요. ");
				}
			}
		}
	}
}