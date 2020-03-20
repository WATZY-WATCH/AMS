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
<link rel="stylesheet" href="/css/table.css">
<link rel="stylesheet" href="/css/form.css">
<link rel="stylesheet" href="/css/formfield.css">
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
    <section class="content-wrapper">
        <h2 class="menu-title">스터디 그룹 생성</h2>
        <form id="groupCreateForm" action="/group/create" method="post">
        <sec:csrfInput />
            <p id="title">스터디 분야</p>
            <select id="groupCategory" class="select-box">
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
            <p id="title">그룹명</p>
            <input type="text" id="groupName" />  
            <p id="title">지역</p>
            <select id="groupArea" class="select-box">
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
            <p id="title">최대 인원수</p>
            <select id="groupLimit" class="select-box">
                <c:forEach var="i" begin="2" end="20" step="1">
                    <option>${i }</option>
                </c:forEach>           
            </select>
            <p id="title">주/월</p>
            <p>
                <select id="groupPeriod" class="select-box" onchange="periodSelect()">
                    <option>주</option>
                    <option>월</option>        
                </select>
                &emsp;
                <select id="groupMonth" class="select-box" style="display: none;">
                    <c:forEach var="i" begin="1" end="31" step="1">
                        <option>${i }</option>
                    </c:forEach>    
                </select>
                <select id="groupWeek" class="select-box">
                    <c:forEach var="i" begin="1" end="7" step="1">
                        <option>${i }</option>
                    </c:forEach>    
                </select>
                    회
            </p>
            <p id="title">연령대</p>
            <p>
            <select id="startAge" class="select-box">
                <c:forEach var="i" begin="10" end="70" step="10">
                    <option>${i }</option>
                </c:forEach>
            </select>
                대&nbsp;~&nbsp;
            <select id="endAge" class="select-box">
                <c:forEach var="i" begin="10" end="70" step="10">
                    <option>${i }</option>
                </c:forEach>
            </select>
                대	
            </p>
            <p id="title">스터디 활동 소개 및 내용</p>
            <textarea id="groupDetail" rows="10" placeholder="스터디 활동 소개 및 내용을 입력해주세요."   ></textarea>
            <button type="button" class="group-create" onclick="formChk()">생성하기</button>
        </form>
    </section>
    <script type="text/javascript" src="/js/group_create.js"></script>
</body>
</html>