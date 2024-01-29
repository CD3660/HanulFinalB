<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title></title>
</head>
<body>









	<section id="main" class="overflow-hidden">
		<div class="container-fluid" style="height: 85%;">
			<div class="banner-item"
				style="background-image: url(images/banner-image1.png); background-repeat: no-repeat; background-position: right; height: 600px; position: relative; background-size: 66%">




				<div class="banner-content padding-large">
					<h1 class="display-1 text-uppercase pb-2 text-primary"
						style="color: #000042; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">My
						Smart</h1>
					<h1 class="display-1 text-uppercase pb-2"
						style="color: #000000; font-weight: bold; margin-left: 2.7em; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">
						Home</h1>
					<p
						style="margin-left: 30px; margin-top: 30px; font-weight: bold; font-size: 1.4em;">IoT
						지금 체험해보세요</p>
					<a href="shop.html"
						class="btn btn-medium btn-arrow position-relative mt-5"> <span
						class="text-uppercase"
						style="font-weight: bold; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">구매하기</span>
						<svg class="arrow-right position-absolute" width="18" height="20">
                          <use xlink:href="#arrow-right"></use>
                        </svg>
					</a>
				</div>

			</div>
			</div>
	</section>







	<section id="company-services" class="padding-large"
		style="padding-top: 0em; padding-bottom: 5em; padding-left: 6em;">
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
							<h3 class="card-title text-uppercase text-dark">제품소개</h3>


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
							<h3 class="card-title text-uppercase text-dark">제품구매</h3>





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
							<h3 class="card-title text-uppercase text-dark">로그인</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="about-us">
		<div class="container-fluid" style="margin-top: 5em;">
			<div class="row align-items-center justify-content-between g-5">
				<div class="col-lg-6">
					<div class="image-holder mb-4 jarallax">

						<img src="<c:url value='/images/prod_img/cctv.png'/>" alt="CCTV"
							class="img-fluid jarallax-img">
					</div>
				</div>
				<div class="col-lg-6">
					<div class="detail p-5">
						<div class="display-header">
							<h2 class="display-2 text-uppercase text-dark pb-2">CCTV</h2>
							<p class="pb-3">설명</p>
							<a href="about-us.html"
								class="btn btn-medium btn-arrow outline-dark position-relative mt-3">
								<span class="text-uppercase">신제품 보기</span> <svg
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





	<section id="testimonials" class="position-relative">
		<div class="container" style="padding-top: 7em; padding-bottom: 3em;">
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
									<blockquote class="fs-4">blah, blah, blah~ blah,
										blah, blah~blah, blah, blah~blah, blah, blah~blah, blah,
										blah~blah, blah, blah~blah, blah, blah~blah, blah, blah~blah,
										blah, blah~blah, blah, blah~</blockquote>
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
		<div class="swiper-pagination text-center position-absolute"></div>
	</section>







	<section id="latest-blog">

		<div class="container-fluid" style="margin-bottom: 8em;">
			<div class="row">
				<div class="col-md-12">
					<div
						class="display-header d-flex flex-wrap justify-content-between pb-3"
						style="margin-top: 10em;">
						<h2 class="display-2 text-dark text-uppercase">베스트 셀러</h2>
						<a href="blog.html"
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
					<div class="card-item">
						<div class="card border-0 bg-transparent">
							<div class="card-image">
								<img src="<c:url value='/images/prod_img/dust.png'/>" alt="dust"
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">미세먼지 측정기</a>
							</h3>
							<p>설명.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item">
						<div class="card border-0">
							<div class="card-image">
								<img src="<c:url value='/images/prod_img/fire.png'/>" alt="fire"
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">불꽃 측정기</a>
							</h3>
							<p>설명.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item">
						<div class="card border-0">
							<div class="card-image">
								<img src="<c:url value='/images/prod_img/gas.png'/>" alt="gas"
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">가스 측정기</a>
							</h3>
							<p>설명.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<hr>















</body>
</html>