<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="container show-grid">
	<div class="row">
		<div class="col-6 p-3">
			<img src="${vo.prod_img }" style="width: 100%" />
		</div>
		<div class="col-6 p-3">
			<h3>${vo.prod_name }</h3>
			<input type="hidden" id="prod_id" value="${vo.prod_id }"/>
			<div class="rate">
				<span style="width: 50%;"></span>
			</div>
			<span style="color: red;">100개의 상품평</span>
			<hr style="color: gray;" />
			<p style="font-size: 0.8em">${vo.detail }</p>
			<div
				style="border-top: 1px solid lightgray; border-bottom: 1px solid lightgray; background-color: #ebebeb;">
				<p class="m-2">
					판매 가격:<span class="ms-3" style="color: red; font-weight: bold;">${vo.price}원</span>
				</p>
			</div>
			<div
				style="border-top: 1px solid lightgray; border-bottom: 1px solid lightgray;">
				<p class="m-2">
					남은 수량:<span class="ms-3">${vo.stock}</span>
				</p>
				<div class="m-2 d-flex justify-content-start align-items-center">
					구매 수량:<input class="ms-3" type="text" name="ea" value="1"
						style="padding-right: 3; text-align: right; height: 18px; width: 35px;" />
					<div class="ms-1">
						<div style="padding: 1 0 2 0">
							<img class="val_up"
								src="<c:url value='/images/shop/btn_plus.gif'/>"
								style="cursor: pointer">
						</div>
						<div>
							<img class="val_down"
								src="<c:url value='/images/shop/btn_minus.gif'/>"
								style="cursor: pointer">
						</div>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-around p-3">
				<button class="btn btn-outline-primary to_cart" style="width: 45%">장바구니
					담기</button>
				<button class="btn btn-primary order" style="width: 45%">바로 구매 ></button>
			</div>
		</div>
	</div>
</div>
<script src='<c:url value="/js/shop.js"/>'></script>