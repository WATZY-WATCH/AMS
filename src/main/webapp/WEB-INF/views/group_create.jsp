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
		<script type="text/javascript" src="/js/group_create.js"></script>
</body>
</html>