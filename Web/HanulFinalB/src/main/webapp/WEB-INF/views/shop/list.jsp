<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<!-- 전체의 2/3 (8 out of 12) -->
				<div class="input-group mb-3 align-items-center">
					<input type="text" class="form-control search"
						aria-label="Text input with dropdown button"> <i
						role="button" class="fa-solid fa-magnifying-glass search-icon"></i>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<!-- 작은 화면에서 보이는 버튼 -->
				<button type="button" class="btn btn-primary d-block d-sm-none"
					data-bs-toggle="collapse" data-bs-target="#hiddenList">
					Show List</button>
				<!-- 숨겨진 리스트 -->
				<div id="hiddenList" class="collapse">
					<ul class="list-group">
						<li class="list-group-item">Item 1</li>
						<li class="list-group-item">Item 2</li>
						<li class="list-group-item">Item 3</li>
					</ul>
				</div>

				<div class="search-filter-content d-sm-none d-md-block p-3">
					<h4>필터</h4>
					<div class="search-filter-options search-service-filter"
						data-address-rocket-wow-eligible="false">
						<h5>쿠팡서비스</h5>
						<div id="searchServiceFilter" class="search-filter-option-list">
							<ul class="search-option-items search-customized-checkbox">
								<li class="search-option-item"><input type="checkbox"
									value="free"> <label class="item-name"> <span
										class="service-filter-free">무료배송</span>
								</label></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-3 shop-item">
						<p>asdfasdfasdf</p>
					</div>
					<div class="col-3">
						<p>asdfasdfasdf</p>
					</div>
					<div class="col-3">
						<p>asdfasdfasdf</p>
					</div>
					<div class="col-3">
						<p>asdfasdfasdf</p>
					</div>
				</div>
				<script type="text/javascript">
					$(".shop-item").mouseenter(function() {
						$(this).addClass("shadow");
					})
					$(".shop-item").mouseleave(function() {
						$(this).removeClass("shadow");
					})
				</script>
			</div>
		</div>
	</div>

</body>
</html>