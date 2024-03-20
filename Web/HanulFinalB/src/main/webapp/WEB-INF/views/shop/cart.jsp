<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<h3 class="m-4">장바구니</h3>
<div class="d-block">
	<div class="d-flex justify-content-center">
		<div style="border: solid 1px gray; width: 700px; min-height: 500px">
			<c:forEach items="${list}" var="vo">
				<div class="row">
					<div class="col-3 p-4">
						<img src="${vo.prod_img}"
							style="max-width: 80px; max-height: 80px">
					</div>
					<div class="col-7 p-4">
						<div>
							<span class="fw-bold">${vo.prod_name}</span>
						</div>
						<div
							style="margin: 5px 0 5px 0; overflow: hidden; height: 1px; border-top: #DDD 1px solid;">
						</div>
						<div>
							<span>${vo.price}원</span> <span> / ${vo.ea}개</span> <span
								class="span-goods-total-price">${vo.price*vo.ea}원</span>
						</div>
					</div>
					<div class="col-2 p-4">
						<i role="button" class="fa-solid fa-trash-can fs-8 text-danger remover" onclick="delete_cart(${vo.order_id})"></i>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<c:if test="${not empty list }">
<div class="d-flex justify-content-end">
	<button type="button" class="btn btn-primary me-3" id="btn-payCart">장바구니 결제</button>
</div>
</c:if>
<script>
	function delete_cart(order_id) {
		location = "delete_cart?order_id="+order_id;
	}
	$("#btn-payCart").click(function() {
		location = "order_cart";
	})
</script>