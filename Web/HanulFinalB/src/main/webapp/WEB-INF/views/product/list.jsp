<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Cinzel:wght@400;500;600;700&family=Poppins:wght@200;300;400;500&display=swap"
	rel="stylesheet">
<!-- script
    ================================================== -->
<script src="<c:url value='/js/modernizr.js'/>"></script>
</head>
<body>
	<section id="latest-blog">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div
						class="display-header d-flex flex-wrap justify-content-between pb-3">
						<h2 class="display-2 text-dark text-uppercase">Read Our
							Articles</h2>
						<a href="blog.html"
							class="btn btn-medium btn-arrow btn-normal position-relative">
							<span class="text-uppercase">See all articles</span> <svg
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
								<img src="images/post-item1.jpg" alt=""
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">Best looking interior things for
									bedrooms</a>
							</h3>
							<p>Enim ut nunc, ultrices mauris felis viverra amet. Ante sed
								dictum nisi suscipit ac ut faucibus pretium interdum.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>Read More</em></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item">
						<div class="card border-0">
							<div class="card-image">
								<img src="images/post-item2.jpg" alt=""
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">Trending modern furniture design
									in 2022</a>
							</h3>
							<p>Enim ut nunc, ultrices mauris felis viverra amet. Ante sed
								dictum nisi suscipit ac ut faucibus pretium interdum.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>Read More</em></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item">
						<div class="card border-0">
							<div class="card-image">
								<img src="images/post-item3.jpg" alt=""
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="single-post.html">Why is simple firniture design
									looks fabulous</a>
							</h3>
							<p>Enim ut nunc, ultrices mauris felis viverra amet. Ante sed
								dictum nisi suscipit ac ut faucibus pretium interdum.</p>
							<a href="single-post.html"
								class="btn btn-normal text-uppercase p-0"><em>Read More</em></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>