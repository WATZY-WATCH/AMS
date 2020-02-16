<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags />
<title>스터디 그룹 생성 </title>
</head>
<body>
<h2>스터디 그룹을 생성합니다. 정보를 입력해주세요.</h2>
<section id="container">
		<form id="groupCreateForm" action="/group/create" method="post">
		<sec:csrfInput />
		
			<table>
				<tr>
                    <td id="title">스터디 분야</td>
                    <td>
                        <select id="groupCategory">
                            <option>어학</option>
                            <option>IT/연구개발</option>
                            <option>미디어/디자인</option>
                            <option>마케팅/기획/영업</option>
                            <option>유통/무역</option>
                            <option>건설/설계</option>
                            <option>생산/제조/기계</option>
                            <option>경영/인사/사무</option>
                            <option>자격증</option>
                            <option>공무원</option>
                            <option>기타</option>                        
                        </select>
                    </td>
                </tr>
                <tr>
                    <td id="title">그룹명</td>
                    <td>
						<input type="text" id="groupName" />  
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">지역</td>
                    <td>
                        <select id="groupArea">
                            <option>서울</option>
                            <option>경기</option>
                            <option>인천</option>
                            <option>강원</option>
                            <option>대전/충청</option>
                            <option>대구</option>
                            <option>부산/울산</option>
                            <option>경상</option>
                            <option>광주/전라/제주</option>                      
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td id="title">최대 인원수</td>
                    <td>
                        <select id="groupLimit">
                            <c:forEach var="i" begin="2" end="20" step="1">
                          		<option>${i }</option>
                          	</c:forEach>           
                        </select>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">주/월</td>
                    <td>
                        <select id="groupPeriod" onchange="periodSelect()">
                            <option>주</option>
                            <option>월</option>        
                        </select>
                        &emsp;
                        <select id="groupMonth" style="display: none;">
                          	<c:forEach var="i" begin="1" end="31" step="1">
                          		<option>${i }</option>
                          	</c:forEach>    
                        </select>
                        <select id="groupWeek">
                          	<c:forEach var="i" begin="1" end="7" step="1">
                          		<option>${i }</option>
                          	</c:forEach>    
                        </select>
                        	회
                    </td>
                </tr>  
                
                <tr>
                    <td id="title">연령대</td>
                    <td>
                   
                        <select id="startAge">
                            <c:forEach var="i" begin="10" end="70" step="10">
                          		<option>${i }</option>
                          	</c:forEach>
                        </select>
                        	대&nbsp;~&nbsp;
                        
                        <select id="endAge">
                            <c:forEach var="i" begin="10" end="70" step="10">
                          		<option>${i }</option>
                          	</c:forEach>
                        </select>
                        	대	
                    </td>
                </tr>
                <tr>
                    <td id="title">스터디 활동 소개 및 내용</td>
                    <td>
                        <textarea id="groupDetail" cols="40" rows="10" placeholder="스터디 활동 소개 및 내용을 입력해주세요."   ></textarea>
                    </td>
                </tr>
            </table>
            <br>
            <button type="button" onclick="formChk()">생성하기</button> <a href="/">메인으로</a>
        </form>


		</section>
		<script type="text/javascript">
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
		</script>
</body>
</html>