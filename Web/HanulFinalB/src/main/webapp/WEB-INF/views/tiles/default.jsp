<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">



<c:choose>
	<c:when test="${category eq 'product'}">
		<c:set var="title" value="제품소개: " />
	</c:when>
	<c:when test="${category eq 'notice'}">
		<c:set var="title" value="공지사항: " />
	</c:when>
	<c:when test="${category eq 'qna'}">
		<c:set var="title" value="Q&A: " />
	</c:when>
	<c:when test="${category eq 'shop'}">
		<c:set var="title" value="제품구매: " />
	</c:when>
	<c:when test="${category eq 'login'}">
		<c:set var="title" value="로그인: " />
	</c:when>
	<c:when test="${category eq 'signin'}">
		<c:set var="title" value="회원가입: " />
	</c:when>
</c:choose>

<title>${title}MySmart Home</title>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/common.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/shop/shop.css'/>">
<link rel="stylesheet"
	href="<c:url value='https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css'/>" />
<link rel="preconnect"
	href="<c:url value='https://fonts.googleapis.com'/>">
<link rel="preconnect"
	href="<c:url value='https://fonts.gstatic.com" crossorigin'/>">
<link
	href="https://fonts.googleapis.com/css2?family=Cinzel:wght@400;500;600;700&family=Poppins:wght@200;300;400;500&display=swap"
	rel="stylesheet">
<!-- script
    ================================================== -->
<script src="<c:url value='/js/modernizr.js'/>"></script>
<script src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='/js/plugins.js'/>"></script>
<script src="<c:url value='/js/script.js'/>"></script>
<script src="<c:url value='/js/common.js'/>"></script>
<script src="https://kit.fontawesome.com/fb330bb215.js"
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="container-fluid mid">
		<tiles:insertAttribute name="container" />
	</div>
</body>
</html>