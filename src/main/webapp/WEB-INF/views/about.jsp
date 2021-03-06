<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ABOUT WATZY</title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template -->
    <link href="/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template -->
    <link href="/css/agency.css" rel="stylesheet">
</head>
<body id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">WATZY</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav text-uppercase ml-auto">
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#services">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#about">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#team">Developers</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/login">LogIn</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  
    <!-- Header -->
    <header class="masthead">
      <div class="container">
        <div class="intro-text">
          <div class="intro-lead-in">스터디 모집 & 관리 웹 서비스</div>
          <div class="intro-heading text-uppercase">WATZY</div>
          <a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="#services" style="margin-bottom: 54px;">MORE READ</a>
        </div>
      </div>
    </header>
  
    <!-- Services -->
    <section class="page-section" id="services">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="section-heading text-uppercase">Services</h2>
            <h3 class="section-subheading text-muted"><i class="fa fa-hashtag" aria-hidden="true"></i>왔지의 주요 기능을 살펴보세요</h3>
          </div>
        </div>
        <div class="row text-center">
          <div class="col-md-4">
            <span class="fa-stack fa-4x">
              <i class="fas fa-circle fa-stack-2x text-primary"></i>
              <i class="fas fa-handshake fa-stack-1x fa-inverse"></i>
            </span>
            <h4 class="service-heading">스터디 모집</h4>
            <p class="text-muted">분야, 지역, 연령대에 맞는 스터디를 찾을 수 있어요<br>
                스터디는 스터디장의 승인 후 가입 가능합니다<br>
                원하는 스터디가 없다면 직접 만들어보세요!</p>
          </div>
          <div class="col-md-4">
            <span class="fa-stack fa-4x">
              <i class="fas fa-circle fa-stack-2x text-primary"></i>
              <i class="fas fa-calendar-check fa-stack-1x fa-inverse"></i>
            </span>
            <h4 class="service-heading">일정 관리</h4>
            <p class="text-muted">운영중인 스터디의 일정을 생성할 수 있어요<br>
                번거로운 일정 관리는 그만<br>
                왔지에서 바로 관리하세요!<br>
                <sup class="sub-msg">소속된 스터디의 일정은 조회만 가능합니다</sup>
            </p>
          </div>
          <div class="col-md-4">
            <span class="fa-stack fa-4x">
              <i class="fas fa-circle fa-stack-2x text-primary"></i>
              <i class="fas fa-location-arrow fa-stack-1x fa-inverse"></i>
            </span>
            <h4 class="service-heading">출결 관리</h4>
            <p class="text-muted">위치 기반 출결 시스템<br>
                스터디원들의 출결 관리에 골머리를 앓고 계셨다면<br>
                이젠 왔지가 도와드릴게요!
            </p>
          </div>
        </div>
      </div>
    </section>
  
    <!-- About -->
    <section class="page-section" id="about">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="section-heading text-uppercase">About</h2>
            <h3 class="section-subheading text-muted"><i class="fa fa-hashtag" aria-hidden="true"></i>왔지의 서비스를 한눈에</h3>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="mock-up mock-up-1"></div>
            <div class="mock-up mock-up-2"></div>
            <div class="mock-up mock-up-3"></div>
            <div class="mock-up mock-up-4"></div>
            <div class="mock-up mock-up-5"></div>
          </div>
        </div>
      </div>
    </section>
  
    <!-- Team -->
    <section class="bg-light page-section" id="team">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <h2 class="section-heading text-uppercase">WATZY Developers</h2>
            <h3 class="section-subheading text-muted"><i class="fa fa-hashtag" aria-hidden="true"></i>왔지의 개발자</h3>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-6">
            <div class="team-member">
              <img class="mx-auto rounded-circle" src="img/development.svg" alt="">
              <h4>정태용</h4>
              <p class="text-muted">Back End<br>
                <sup>Deploying<br>
                    Email: ckck5050@naver.com<br>
                    Github @jungtaeyong
                </sup>
            </p>
              <ul class="list-inline social-buttons">
                <li class="list-inline-item">
                  <a href="mailto:ckck5050@naver.com">
                    <i class="fab fa fa-envelope"></i>
                  </a>
                </li>
                <li class="list-inline-item">
                  <a href="https://github.com/jungtaeyong">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="team-member">
              <img class="mx-auto rounded-circle" src="img/code.svg" alt="">
              <h4>정예원</h4>
              <p class="text-muted">Front End<br>
                <sup>API<br>
                    Email: yeyeah_1@daum.net<br>
                    Github @yuuil
                </sup>
             </p>
              <ul class="list-inline social-buttons">
                <li class="list-inline-item">
                  <a href="mailto:yeyeah_1@daum.net">
                    <i class="fab fa fa-envelope"></i>
                  </a>
                </li>
                <li class="list-inline-item">
                  <a href="https://github.com/yuuil">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-8 mx-auto text-center">
            <p class="large text-muted">2020.03<br>Based in SEOUL</p>
          </div>
        </div>
      </div>
    </section>
  
    <!-- Footer -->
    <footer class="footer">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-4">
            <span class="copyright">Copyright &copy; WATZY 2020</span>
          </div>
        </div>
      </div>
    </footer>
  
    <!-- Bootstrap core JavaScript -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
  
    <!-- Plugin JavaScript -->
    <script src="/js/jquery.easing.min.js"></script>
  
    <!-- Contact form JavaScript -->
    <script src="/js/jqBootstrapValidation.js"></script>
    <script src="/js/contact_me.js"></script>
  
    <!-- Custom scripts for this template -->
    <script src="js/agency.js"></script>  
</body>
</html>