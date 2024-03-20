<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<body>
	<c:if test="${loginInfo.admin == 'Y'}">
		<div class="row justify-content-end">
			<div class="col-auto">
				<!-- 여기에 내용을 넣으세요 -->
				<button type="button" class="btn btn-primary me-3" id="btn-insert">상품 등록</button>
			</div>
		</div>
	</c:if>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<!-- 전체의 2/3 (8 out of 12) -->
				<div class="input-group mb-3 align-items-center">
					<input type="text" class="form-control search" id="keyword" aria-label="Text input with dropdown button" value="${keyword==null?'': keyword}">
					<i role="button" class="fa-solid fa-magnifying-glass search-icon"></i>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
				<div class="container">
					<div class="row">
						<c:forEach items="${list }" var="vo" varStatus="i">
							<div class="col shop-item mt-2"
								onclick="shop_info(${vo.prod_id})"
								style="word-wrap: break-word; height: 500px; width: 240px; max-width: 240px">
								<img src="${vo.prod_img }"
									class="shop-img center-block m-auto" />
								<p class="mt-2">${vo.prod_name}</p>
								<p class="price">
									<fmt:formatNumber value="${vo.price}" pattern="###,###원" />
								</p>
								<div class="rate">
									<span style="width: ${(vo.rate==null?0:vo.rate)*10}%"></span>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function shop_info(id){
			location = "info?prod_id="+id;
		}
		$("#btn-insert").click(function() {
			location = "insertPage";
		});
		$('.search').keyup(function(event){
		    if(event.which === 13){
		        event.preventDefault();
		        search();
		     }
		});
		$(".search-icon").click(function() {
			search();
		});
		
		function search() {
			location = "list?keyword="+ $("#keyword").val();
		}
	</script>

</body>
</html>