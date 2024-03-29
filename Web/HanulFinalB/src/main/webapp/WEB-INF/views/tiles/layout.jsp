<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>

<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">



		<c:choose>
			<c:when test="${category eq 'product'}"><c:set var="title" value="제품소개: "/></c:when> 
			<c:when test="${category eq 'notice'}"><c:set var="title" value="공지사항: "/></c:when>  
			<c:when test="${category eq 'qna'}"><c:set var="title" value="Q&A: "/></c:when>
			<c:when test="${category eq 'shop'}"><c:set var="title" value="제품구매: "/></c:when>
			<c:when test="${category eq 'login'}"><c:set var="title" value="로그인: "/></c:when>
			<c:when test="${category eq 'signin'}"><c:set var="title" value="회원가입: "/></c:when> 
		</c:choose>

<title>${title}My Smart Home</title>


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
<body class="bg-body" data-bs-spy="scroll" data-bs-target="#navbar"
	data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true"
	tabindex="0">
	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
	
      <!-- <symbol id="search" xmlns="http://www.w3.org/2000/svg"
			viewBox="0 0 32 32">
        <title>Search</title>
        <path fill="currentColor"
			d="M19 3C13.488 3 9 7.488 9 13c0 2.395.84 4.59 2.25 6.313L3.281 27.28l1.439 1.44l7.968-7.969A9.922 9.922 0 0 0 19 23c5.512 0 10-4.488 10-10S24.512 3 19 3zm0 2c4.43 0 8 3.57 8 8s-3.57 8-8 8s-8-3.57-8-8s3.57-8 8-8z" />
      </symbol> -->
      
      <symbol xmlns="http://www.w3.org/2000/svg" id="user"
			viewBox="0 0 16 16">
        <path
			d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="cart"
			viewBox="0 0 16 16">
        <path
			d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="arrow-left"
			viewBox="0 0 32 32">
        <path fill="currentColor"
			d="m13.281 6.781l-8.5 8.5l-.687.719l.687.719l8.5 8.5l1.438-1.438L7.938 17H28v-2H7.937l6.782-6.781z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="arrow-right"
			viewBox="0 0 32 32">
        <path fill="currentColor"
			d="M18.719 6.781L17.28 8.22L24.063 15H4v2h20.063l-6.782 6.781l1.438 1.438l8.5-8.5l.687-.719l-.687-.719z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="shipping-fast"
			viewBox="0 0 32 32">
        <path fill="currentColor"
			d="M0 6v2h19v15h-6.156c-.446-1.719-1.992-3-3.844-3c-1.852 0-3.398 1.281-3.844 3H4v-5H2v7h3.156c.446 1.719 1.992 3 3.844 3c1.852 0 3.398-1.281 3.844-3h8.312c.446 1.719 1.992 3 3.844 3c1.852 0 3.398-1.281 3.844-3H32v-8.156l-.063-.157l-2-6L29.72 10H21V6zm1 4v2h9v-2zm20 2h7.281L30 17.125V23h-1.156c-.446-1.719-1.992-3-3.844-3c-1.852 0-3.398 1.281-3.844 3H21zM2 14v2h6v-2zm7 8c1.117 0 2 .883 2 2s-.883 2-2 2s-2-.883-2-2s.883-2 2-2zm16 0c1.117 0 2 .883 2 2s-.883 2-2 2s-2-.883-2-2s.883-2 2-2z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="shopping-cart"
			viewBox="0 0 32 32">
        <path fill="currentColor"
			d="M5 7c-.55 0-1 .45-1 1s.45 1 1 1h2.219l2.625 10.5c.222.89 1.02 1.5 1.937 1.5H23.25c.902 0 1.668-.598 1.906-1.469L27.75 10H11l.5 2h13.656l-1.906 7H11.781L9.156 8.5A1.983 1.983 0 0 0 7.22 7zm17 14c-1.645 0-3 1.355-3 3s1.355 3 3 3s3-1.355 3-3s-1.355-3-3-3zm-9 0c-1.645 0-3 1.355-3 3s1.355 3 3 3s3-1.355 3-3s-1.355-3-3-3zm0 2c.563 0 1 .438 1 1c0 .563-.438 1-1 1c-.563 0-1-.438-1-1c0-.563.438-1 1-1zm9 0c.563 0 1 .438 1 1c0 .563-.438 1-1 1c-.563 0-1-.438-1-1c0-.563.438-1 1-1z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="gift"
			viewBox="0 0 32 32">
        <path fill="currentColor"
			d="M12 5c-1.645 0-3 1.355-3 3c0 .352.074.684.188 1H4v6h1v13h22V15h1V9h-5.188A2.95 2.95 0 0 0 23 8c0-1.645-1.355-3-3-3c-1.75 0-2.938 1.328-3.719 2.438c-.105.148-.187.292-.281.437c-.094-.145-.176-.29-.281-.438C14.938 6.329 13.75 5 12 5zm0 2c.625 0 1.438.672 2.063 1.563c.152.218.128.23.25.437H12c-.566 0-1-.434-1-1c0-.566.434-1 1-1zm8 0c.566 0 1 .434 1 1c0 .566-.434 1-1 1h-2.313c.122-.207.098-.219.25-.438C18.563 7.673 19.375 7 20 7zM6 11h20v2h-9v-1h-2v1H6zm1 4h18v11h-8V16h-2v10H7z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="return"
			viewBox="0 0 32 32">
        <path fill="currentColor"
			d="M16 3C8.832 3 3 8.832 3 16s5.832 13 13 13s13-5.832 13-13h-2c0 6.086-4.914 11-11 11S5 22.086 5 16S9.914 5 16 5c3.875 0 7.262 1.984 9.219 5H20v2h8V4h-2v3.719C23.617 4.844 20.02 3 16 3z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="quote"
			viewBox="0 0 24 24">
        <path fill="currentColor"
			d="m15 17l2-4h-4V6h7v7l-2 4h-3Zm-9 0l2-4H4V6h7v7l-2 4H6Z" />
      </symbol>      
      <symbol xmlns="http://www.w3.org/2000/svg" id="nav-icon"
			viewBox="0 0 16 16">
        <path
			d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="close"
			viewBox="0 0 16 16">
        <path
			d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z" />
      </symbol>
      <symbol xmlns="http://www.w3.org/2000/svg" id="navbar-icon"
			viewBox="0 0 16 16">
        <path
			d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
      </symbol>
    </svg>

<!-- 	<div id="preloader">
		<span class="loader"> <span class="loader-inner"></span>
		</span>
	</div> -->

	<div class="search-box position-relative overflow-hidden w-100">
		<div class="search-wrap">
			<div class="close-button position-absolute">
				<svg class="close" width="22" height="22">
            <use xlink:href="#close"></use>
          </svg>
			</div>
			
			<!-- 검색**********************************************************************************************  -->
<!-- 			<form id="search-form" class="text-center pt-3" action="" method="">
				<input type="text" class="search-input fs-5 p-4 bg-transparent"	placeholder="검색어를 입력하세요">
					<svg class="search" width="22" height="22">
	            		<use xlink:href="#search"></use>
	          		</svg>
			</form> -->
			
			
		</div>
	</div>

	<header id="header" class="site-header text-black">
		<nav id="header-nav" class="navbar navbar-expand-lg px-5 mb-3">
			
				<div class="container-fluid">
							<a class="navbar-brand" href="<c:url value='/'/>">
							<img src="<c:url value='/images/logo-main-w-icon.png'/>" class="logo"
								style="margin-top: 16px;">
							</a>
							
							
							
							
							<button class="navbar-toggler d-flex d-lg-none order-3 p-2"
								type="button" data-bs-toggle="offcanvas" data-bs-target="#bdNavbar"
								aria-controls="bdNavbar" aria-expanded="false"
								aria-label="Toggle navigation">
								<svg class="navbar-icon" width="50" height="50">
			              <use xlink:href="#navbar-icon"></use>
			            </svg>
							</button>




			     <div class="offcanvas offcanvas-end" tabindex="-1" id="bdNavbar"
					aria-labelledby="bdNavbarOffcanvasLabel">
					<div class="offcanvas-header px-4 pb-0">
						<a class="navbar-brand" href="index.html"> <img
							src="<c:url value='/images/logo-main-w-icon.png'/>" class="logo">
						</a>
						<button type="button" class="btn-close btn-close-black"
							data-bs-dismiss="offcanvas" aria-label="Close"
							data-bs-target="#bdNavbar"></button>
					</div>






					<div class="offcanvas-body">
						<ul id="navbar"
							class="navbar-nav text-uppercase justify-content-end align-items-center flex-grow-1 pe-3"
							style="font-size:1.2em; font-weight: bold; ">

							<li class="nav-item"><a class="nav-link me-4" href="<c:url value='/prod/list'/>">제품소개</a></li>

							<li class="nav-item"><a class="nav-link me-4" href="<c:url value="/notice/list"/>">공지사항</a></li>


							<li class="nav-item"><a class="nav-link me-4" href="<c:url value="/qna/list"/>">Q&A</a></li>


							<li class="nav-item"><a class="nav-link me-4" href="<c:url value='/shop/list'/>">제품구매</a></li>





							<!-- 로그인을 하지 않은 경우  -->
							<c:if test="${empty loginInfo }">
							
								<li class="nav-item dropdown me-4">
									<a class="nav-link dropdown-toggle" href="#" id="dropdownPages"
												data-bs-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">로그인</a>
										<ul class="dropdown-menu list-unstyled"
													aria-labelledby="dropdownPages">
										
											<li><a href="<c:url value='/member/login'/>"
												class="dropdown-item item-anchor">로그인 <span
													class="badge bg-secondary text-dark ms-2">기존</span></a></li>
													
													
											<li><a href="<c:url value='/member/joinView'/>"
													class="dropdown-item item-anchor">회원가입 <span
													class="badge bg-secondary text-dark ms-2">신규</span></a></li>
										</ul>
								</li>
							</c:if>


							<!-- 로그인을 한 경우 -->
							<c:if test="${ ! empty loginInfo }">
								<li class="nav-item dropdown me-4">
									<a class="nav-link dropdown-toggle" href="#" id="dropdownPages"
												data-bs-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">${loginInfo.name}님 환영합니다</a>
										<ul class="dropdown-menu list-unstyled"
													aria-labelledby="dropdownPages">
										
											<li><a href="<c:url value='/member/mypage'/>"
												class="dropdown-item item-anchor">마이페이지 <span
													class="badge bg-secondary text-dark ms-2">기존</span></a></li>
											<li><a href="<c:url value='/member/paymentList'/>"
												class="dropdown-item item-anchor">구매 내역 <span
													class="badge bg-secondary text-dark ms-2">기존</span></a></li>
													
													
											<li><a href="<c:url value='/member/logout'/>"
													class="dropdown-item item-anchor">로그아웃 <span
													class="badge bg-secondary text-dark ms-2">신규</span></a></li>
										</ul>
							     </li>
						 	</c:if>







							<li class="nav-item">
								<div class="user-items ps-5">
									<ul class="d-flex justify-content-end list-unstyled">
								 	
								    	<!-- 검색*************************************************************************************  -->
<!-- 								    	
										<li class="search-item pe-3" data-bs-toggle="collapse"
													data-bs-target="#search-box" aria-controls="search-box"
													aria-expanded="false" aria-label="Toggle navigation">
											<svg class="search" width="18" height="18">
                         				 		<use xlink:href="#search"></use>
                       					 	</svg>
                        				</li> -->
                        				
                        				
                        				
                        				
                        				
                        				
                        				
                        				
                        				
                        				
                        				
										<li class="pe-3">
										
											<!--로그인 한 경우  -->
											<c:if test="${! empty loginInfo }">
												<a href="<c:url value='/member/mypage'/>">
											</c:if>
											
											<!-- 로그인 안한 경우  -->
											<c:if test="${ empty loginInfo }">
												<a href="<c:url value='/member/login'/>">
											</c:if>
											
												<svg class="user" width="18" height="18">
		                           					<use xlink:href="#user"></use>
		                        				</svg>
											</a>
										</li>
										
										
										<li>
											<a href="<c:url value='/shop/cart'/>">
												<svg class="cart" width="18" height="18">
                           							<use xlink:href="#cart"></use>
                         						</svg>
											</a>
										</li>
										
										
										
										
									</ul>
								</div>
							</li>
							
							
							
							
						</ul>
					</div>
					
					
				</div>
			</div>
		</nav>
	</header>







	<div class="container-fluid mid">
		<tiles:insertAttribute name="container" />
	</div>







	<footer id="footer" class="overflow-hidden padding-small">
		<div class="container-fluid">
			<div class="row">
				<div class="row d-flex flex-wrap justify-content-between">
					<div class="col-lg-3 col-sm-6 pb-3 pe-4" style="padding-top: 20px;">
						<div class="footer-menu">

							<i class="fa-solid fa-house" style="margin-bottom: 10px;"></i>
							<p>한울201, 2024 프로젝트 B팀</p>
						</div>
						<div class="copyright">
							<p>Copyright © 2024 Hanul.</p>
						</div>
					</div>
					<div class="col-lg-2 col-sm-6 pb-3">
						<div class="footer-menu text-uppercase">
							<h5 class="widget-title pb-2">Quick Links</h5>
							<ul class="menu-list list-unstyled text-uppercase">
								<li class="menu-item pb-2"><a href="<c:url value='/prod/list'/>">제품소개</a></li>
								<li class="menu-item pb-2"><a href="<c:url value='/notice/list'/>">공지사항</a></li>
								<li class="menu-item pb-2"><a href="<c:url value='/qna/list'/>">Q&A</a>
								</li>
								<li class="menu-item pb-2"><a href="<c:url value='/shop/list'/>">제품구매</a>
								</li>
								<c:if test="${empty loginInfo }">
									<li class="menu-item pb-2"><a href="<c:url value='/member/login'/>">로그인</a></li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="col-lg-2 col-sm-6 pb-3">
						<div class="footer-menu text-uppercase">
							<h5 class="widget-title pb-2">Member</h5>
							<div class="social-links">
								<ul class="list-unstyled">
									<li class="pb-2">Yeong</a></li>
									<li class="pb-2">Hyun</a></li>
									<li class="pb-2">Hee</a></li>
									

								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6">
						<div class="footer-menu contact-item">
							<h5 class="widget-title text-uppercase pb-2">Contact Us</h5>
							<p>062-362-7797</p>
							<p>
								<a href="mailto:">hanul7797@naver.com</a>
							</p>
							<p>경열로 3 (농성동 271-4)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	
	

	
</body>
</html>