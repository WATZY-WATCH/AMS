<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/animation.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="/css/all.min.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="/css/sb-admin-2.css" rel="stylesheet">
<sec:csrfMetaTags />
</head>
<body>
	<header>
		<h1 class="logo"><a href="/">WATZY</a></h1>
	</header>
	<section class="about-page">
		<button class="about-btn" onclick="location.href='/about'">ABOUT WATZY</button>
	</section>
	<!-- Sidebar -->
	<ul class="side-menu navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
		<!-- Nav Item - Dashboard -->
		<li class="nav-item active" style="margin-bottom: 1rem;">
			<a class="nav-link" href="/about">
			<i class="fas fa-fw fa-tachometer-alt"></i>
			<span>ABOUT WATZY</span></a>
		</li>
		<!-- Heading -->
		<div class="sidebar-heading">
			Public
		</div>
	
		<!-- Nav Item - Pages Collapse Menu -->
		<li class="nav-item">
			<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
			<i class="fas fa-fw fa-folder"></i>
			<span>STUDY GROUP</span>
			</a>
			<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">스터디</h6>
				<a class="collapse-item" href="/group/create">새로운 그룹 생성</a>
				<a class="collapse-item" href="/group/listCri">그룹 찾기</a>
				<a class="collapse-item" href="/groupManage/home">그룹 관리</a>
			</div>
			</div>
		</li>
	
		<!-- Divider -->
		<hr class="sidebar-divider">
	
		<!-- Heading -->
		<div class="sidebar-heading">
			Personal
		</div>
	
		<!-- Nav Item - Pages Collapse Menu -->
		<li class="nav-item">
			<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
			<i class="fas fa-fw fa-cog"></i>
			<span>MY PAGE</span>
			</a>
			<div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Login</h6>
				<sec:authorize access="isAnonymous()">
					<a class="collapse-item" href="/login">로그인</a>
					<a class="collapse-item" href="/user/signup">회원가입</a>
				</sec:authorize>
				<sec:authorize access='!hasAuthority("KAKAO")'>
					<form class="logout-btn" action="<c:url value='/logout' />" method="post">
						<sec:csrfInput />
						<button class="collapse-item" style="width:auto;">로그아웃 </button>
					</form>
				</sec:authorize>
				<sec:authorize access='hasAuthority("KAKAO")'>
					<form class="logout-btn" action="<c:url value='/klogout' />" method="post">
						<sec:csrfInput />
						<button class="collapse-item" style="width:auto;">로그아웃 </button>
					</form>
				</sec:authorize>
				<div class="collapse-divider"></div>
				<h6 class="collapse-header">My page</h6>
				<a class="collapse-item" href="/user/modify">회원정보 수정</a>
				<sec:authorize access='hasAuthority("KAKAO")'>
					<a class="collapse-item" onclick="signoutChk()" style="cursor: pointer;">회원탈퇴</a>
				</sec:authorize>
				<sec:authorize access='!hasAuthority("KAKAO")'>
					<a class="collapse-item" href="/user/modifyPw">비밀번호 변경</a>
					<a class="collapse-item" href="/user/signout">회원탈퇴</a>
				</sec:authorize>
			</div>
			</div>
		</li>
	
		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">
	
		<!-- Sidebar Toggler (Sidebar) -->
		<div class="text-center d-none d-md-inline">
			<button class="rounded-circle border-0" id="sidebarToggle"></button>
		</div>
	
		</ul>
		<!-- End of Sidebar -->
	<script src="/js/oauth_user_signout.js"></script>
	
	<!-- Bootstrap core JavaScript-->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.bundle.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="/js/sb-admin-2.min.js"></script>
</body>
</html>