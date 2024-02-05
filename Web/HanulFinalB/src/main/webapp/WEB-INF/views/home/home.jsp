<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>









	<section id="main">
            <div class="container-fluid" style="height: 85%;">
              <div class="row">
                <div class="col-md-12">		
		
			<div class="banner-item"
				style="background-image: url(images/banner-image1.png); background-repeat: no-repeat; background-position: right; height: 600px; position: relative; background-size: 66%">




				<div class="banner-content padding-large">
					<h1 class="display-1 text-uppercase pb-2 text-primary"
						style="color: #000063; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">My
						Smart</h1>
					<h1 class="display-1 text-uppercase pb-2"
						style="color: #00003F; font-weight: bold; margin-left: 2.7em; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">
						Home</h1>
					<p
						style="margin-left: 30px; margin-top: 30px; font-size: 1.6em; text-shadow: 5px 5px 5px #888888;">IoT
						지금 체험해보세요</p>
					<a href="<c:url value='/prod/list'/>"
						class="btn btn-medium btn-arrow position-relative mt-5"> <span
						class="text-uppercase"
						style="font-weight: bold; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">제품보기</span>
						<svg class="arrow-right position-absolute" width="18" height="20">
                          <use xlink:href="#arrow-right"></use>
                        </svg>
					</a>
				</div>
				
				
			</div>
		</div>
		</div>
		</div>
	</section>










	<section id="home-simple-icon" class="padding-large"
		style="padding-top: 0em; padding-bottom: 10em; padding-left: 6em;">
		<div class="container-fluid">
			<div class="row">
			
			
				<div class="col-lg-3 col-md-6 pb-3">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="gift">
                  				<use xlink:href="#gift" />
                			</svg>
						</div>
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark"><a href="<c:url value='/prod/list'/>">제품소개</a></h3>
						</div>
					</div>
				</div>




				<div class="col-lg-3 col-md-6 pb-3">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="shipping-fast">
                 				<use xlink:href="#shipping-fast" />
               				</svg>
						</div>
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark"><a href="<c:url value='/shop/list'/>">제품구매</a></h3>
						</div>
					</div>
				</div>
				
				
				
				
				<div class="col-lg-3 col-md-6 pb-3">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="shopping-cart">
                  				<use xlink:href="#shopping-cart" />
                			</svg>
						</div>
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark">장바구니</h3>
						</div>
					</div>
				</div>
				
				
				
				
				<div class="col-lg-3 col-md-6 pb-3">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="return">
                 				 <use xlink:href="#return" />
               				</svg>
						</div>
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark"><a href="<c:url value='/member/login'/>">로그인</a></h3>
						</div>
					</div>
				</div>
				
				
							
				
			</div>
		</div>
	</section>

































	<section id="NewProduct">
		<div class="container-fluid" style="margin-top: 5em;">
			<div class="row align-items-center justify-content-between g-5">
				<div class="col-lg-6">
					<div class="image-holder mb-4 jarallax">
					<h2 class="display-2 text-dark text-uppercase" style="font-size: 2.7em;">
					<i class="fa-solid fa-rocket"></i>   신제품</h2>
						<img src="<c:url value='/images/prod_img/cctv.png'/>" alt="CCTV"
							class="img-fluid jarallax-img">
					</div>
				</div>
				<div class="col-lg-6">
					<div class="detail p-5">
						<div class="display-header">
							<h2 class="display-2 text-uppercase text-dark pb-2">CCTV</h2>
							<p class="pb-3">
높은 해상도의 고품질 영상과 최첨단 기술을 적용한 CCTV 제품을 소개합니다. 저조도 및 야간 감시 기능으로 24시간 안전을 지키며, 스마트폰이나 웹 브라우저를 통한 원격 모니터링과 제어가 가능합니다. 또한, 이벤트 알림을 통해 실시간으로 상황에 대응할 수 있습니다.</p>
							
							
							<a class="btn btn-medium btn-arrow outline-dark position-relative mt-3" id="cctv">
								<span class="text-uppercase">자세히 보기</span> <svg
									class="arrow-right position-absolute" width="18" height="20">
                    <use xlink:href="#arrow-right"></use>
                  </svg>
							</a>
							
							
							
			
						
							
							
							
							
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	



	<section id="home-comment" class="position-relative">
		<div class="container" style="padding-top: 7em; padding-bottom: 9em;">
			<div class="row">
				<div class="review-content position-relative">
					<div
						class="swiper-icon swiper-arrow swiper-arrow-prev position-absolute d-flex align-items-center justify-content-center">
						<svg class="icon-arrow" width="25" height="25">
                <use xlink:href="#arrow-left" />
              </svg>
					</div>
					<div class="swiper testimonial-swiper">
						<div class="quotation text-center">
							<svg class="quote">
                  <use xlink:href="#quote" />
                </svg>
						</div>

						<div class="swiper-wrapper">
							<div
								class="swiper-slide text-center d-flex justify-content-center">
								<div class="review-item col-md-10">
									<i class="icon icon-review"></i>
									<blockquote class="fs-4">안전하고 신뢰성 있는 보안 솔루션으로 데이터 보호 및 암호화, 내구성 및 방수 기능을 갖추어 고객의 안전을 최우선에 두고 있습니다. 전문가 그룹의 기술 지원과 서비스를 통해 CCTV 설치, 유지보수, 사용자 교육 등 다양한 영역에서 고객에게 최상의 서비스를 제공합니다.</blockquote>
									<div class="author-detail">
										<div class="name text-primary text-uppercase pt-2">Hanul
											Team B</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>



	<section id="BestSeller">
		<div class="container-fluid" style="margin-bottom: 8em;">
			<div class="row">
				<div class="col-md-12">
					<div
						class="display-header d-flex flex-wrap justify-content-between pb-3"
						style="margin-top: 10em;">
						<h2 class="display-2 text-dark text-uppercase" style="font-size: 2.7em;">
						<i class="fa-solid fa-crown"></i>   베스트 셀러</h2>
						<a href="<c:url value='/shop/list'/>"
							class="btn btn-medium btn-arrow btn-normal position-relative">
							<span class="text-uppercase">모든 제품 보기</span> <svg
								class="arrow-right position-absolute" width="18" height="20">
                  <use xlink:href="#arrow-right"></use>
                </svg>
						</a>
					</div>
				</div>
			</div>
			
			
			<div class="row g-3 post-grid">
			
			
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item" id=dust>
						<div class="card border-0 bg-transparent">
							<div class="card-image">
								<img src="<c:url value='/images/prod_img/dust.png'/>" alt="dust"
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">미세먼지모듈</a>
							</h3>
							<p>미세먼지측정모듈입니다.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
															
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item" id=fire>
						<div class="card border-0">
							<div class="card-image">
								<img src="<c:url value='/images/prod_img/fire.png'/>" alt="fire"
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4" >
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">화재감지모듈</a>
							</h3>
							<p>화재감지모듈입니다.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
															
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item" id=gas>
						<div class="card border-0">
							<div class="card-image">
								<img src="<c:url value='/images/prod_img/gas.png'/>" alt="gas"
									class="post-image img-fluid"></a>
							</div>
						</div>
						<div class="card-body p-0 mt-4" >
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">가스누출감지모듈</a>
							</h3>
							<p>가스누출감지모듈입니다.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
														
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<hr>




<c:forEach items="${list}" var="vo">

				<!-- 모달 소환  -->
							<%@ include file="/WEB-INF/views/home/modal.jsp" %>

</c:forEach>




		<script>
		
			$('#cctv').click(function(e) {
				console.log("cctv클릭");
				e.preventDefault();
			
				$('#modal2').modal("show");
		    });

			$('#btn2').click(function(e) {
				window.open("/shop/info?prod_id=2", '_blank');
			});
			
			
			
			
			
			$('#dust').click(function(e) {
				e.preventDefault();
			
				$('#modal6').modal("show");
		    });

			$('#btn6').click(function(e) {
				window.open("/shop/info?prod_id=6", '_blank');
			});
			
			
			
			
			
			
			$('#fire').click(function(e) {
				e.preventDefault();
			
				$('#modal3').modal("show");
		    });

			$('#btn3').click(function(e) {
				window.open("/shop/info?prod_id=3", '_blank');
			});
			
			
			
			
			
			
			$('#gas').click(function(e) {
				e.preventDefault();
			
				$('#modal4').modal("show");
		    });

			$('#btn4').click(function(e) {
				window.open("/shop/info?prod_id=4", '_blank');
			});
		</script>



</html>