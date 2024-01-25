<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<body>
	<section id="latest-blog">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div
						class="display-header d-flex flex-wrap justify-content-between pb-3">
						<h2 class="display-2 text-dark fw-bold ms-3">제품 소개</h2>
						<a href="<c:url value='/shop/list'/>"
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
									alt="product-item"
									class="product-image img-fluid prod${vo.prod_id}">
							</div>
							<div class="cart-concern">
								<h3 class="card-title text-uppercase pt-3 text-primary">
									<a class="text-primary prod${vo.prod_id}">${vo.prod_name}</a>
								</h3>
								<div class="cart-info">
									<a class="pseudo-text-effect prod${vo.prod_id}"
										data-after="자세히 보기"><span style="opacity: 0">hidden</span></a>
								</div>
							</div>
						</div>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="modal${vo.prod_id}" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-prod">
							<div class="modal-content modal-prod">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">${vo.prod_name}</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<div class="container">
										<div class="row">
											<!-- 왼쪽 컬럼 -->
											<div class="col-md-6">
												<img src="<c:url value='/images/prod_img/${vo.prod_img}'/>"
													alt="product-item"
													class="product-image img-fluid prod${vo.prod_id}">
											</div>

											<!-- 오른쪽 컬럼 -->
											<div class="col-md-6">
												<jsp:scriptlet>pageContext.setAttribute("newline", "\n");</jsp:scriptlet>
												<c:out value="${fn:replace(vo.detail, newline, '<br>')}"
													escapeXml="false" />


											</div>
										</div>
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">닫기</button>
									<button type="button" class="btn btn-primary" id="btn${vo.prod_id}">상품 구매</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<c:forEach items="${list}" var="vo">
		<script>
			$('.prod${vo.prod_id}').click(function(e) {
				e.preventDefault();
				$('#modal${vo.prod_id}').modal("show");
			});
			$('#btn${vo.prod_id}').click(function(e) {
				 window.open("/shop/info?prod_id=${vo.prod_id}", '_blank');
			});
		</script>
	</c:forEach>
</body>
</html>