<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>다음 지도 API</title>
	<link href="/css/map.css" />
	<!-- services 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae043202100ac9f674291ee85c05ebc2&libraries=services"></script>
</head>
<body>
	<div class="map_wrap">
    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
              <input type="text" placeholder="검색 키워드를 입력해주세요 " id="inputKeyword" size="30"> 
              <button onclick="searchPlace()">검색하기</button> 
            </div>
        </div>
        <div id="map" style="width:100%;height:80vh;position:relative;overflow:hidden;"></div>
        <div id="clickLatlng"></div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
	</div>
	<script src="/js/group_map.js"></script>
</body>
</html>