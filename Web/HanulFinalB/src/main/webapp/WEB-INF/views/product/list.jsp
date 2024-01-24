<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<!-- 임시로 달아둔 구역, 타일 완료 시 삭제 예정 -->
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css'/>">
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
<script src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js'/>"></script>
</head>
<!-- 임시로 달아둔 구역, 타일 완료 시 삭제 예정 -->
<body>
	<section id="latest-blog">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div
						class="display-header d-flex flex-wrap justify-content-between pb-3">
						<h2 class="display-2 text-dark fw-bold">제품 소개</h2>
						<a href="<c:url value='shop/list'/>"
							class="btn btn-medium btn-arrow btn-normal position-relative">
							<span class="">제품 구매</span> <svg
								class="arrow-right position-absolute" width="18" height="20">
                  <use xlink:href="#arrow-right"></use>
                </svg>
						</a>
					</div>
				</div>
			</div>
			<div class="row g-3 post-grid">
				<c:forEach items="${list}" var="vo">

					<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
						<div
							class="product-card image-zoom-effect link-effect d-flex flex-wrap">
							<div class="image-holder">
								<img src="<c:url value='/images/prod_img/${vo.prod_img}'/>"
									alt="product-item" class="product-image img-fluid prod${vo.prod_id}">
							</div>
							<div class="cart-concern">
								<h3 class="card-title text-uppercase pt-3 text-primary">
									<a href="single-product.html" class="text-primary prod${vo.prod_id}">${vo.prod_name}</a>
								</h3>
								<div class="cart-info">
									<a class="pseudo-text-effect prod${vo.prod_id}" data-after="자세히 보기"><span>${vo.price}</span></a>
								</div>
							</div>
						</div>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">...</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<c:forEach items="list" var="vo">
		<script>
			$('#test').click(function(e) {
				e.preventDefault();
				$('#exampleModal').modal("show");
			});
		</script>
	</c:forEach>
</body>
</html>