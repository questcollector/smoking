<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/ol.css'/>" />
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/leaflet.css'/>" />
</head>
<body>
    <h2>광진구 최적 흡연부스 설치 지점 탐구</h2>
    <div id="map" class="map" style="width:100%; height:90%;"></div>
    <div id="popup" class="ol-popup" style="width: 330px; height: 330px;">
        <a href="javascript:;" id="popup-closer" class="ol-popup-closer"></a>
        <div id="pano" style="width:90%; height:90%;"></div>
    </div>
    <script src="<c:url value='/js/ol.js'/>"></script>
    <script src="<c:url value='/js/leaflet.js'/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.6.0/proj4.js"></script>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=uy8sgf6h4j&submodules=panorama"></script>
    <script src="<c:url value='/js/main.js'/>"></script>
</body>
</html>
