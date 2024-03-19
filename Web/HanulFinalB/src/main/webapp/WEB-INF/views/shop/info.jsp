<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<div class="container show-grid">
	<c:if test="${loginInfo.admin == 'Y'}">
		<div class="row justify-content-end">
			<div class="col-auto">
				<!-- 여기에 내용을 넣으세요 -->
				<button type="button" class="btn btn-primary me-3" id="btn-updatePage">상품 정보 수정</button>
				<button type="button" class="btn btn-primary me-3" id="btn-delete">상품 삭제</button>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-6 p-3">
			<img src="${vo.prod_img }" style="width: 100%" />
		</div>
		<div class="col-6 p-3">
			<h3>${vo.prod_name }</h3>
			<input type="hidden" id="prod_id" value="${vo.prod_id }"/>
			<%-- 상품평과 별점 만드어야함--%>
			<div class="rate">
				<span style="width: ${(vo.rate==null?0:vo.rate)*10}%;"></span>
			</div>
			<span style="color: red;">${vo.review_cnt}개의 상품평</span>
			<%-- 상품평과 별점 만드어야함--%>
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
				<input type="hidden" id="user_id" value="${loginInfo.user_id }"/>
				<button class="btn btn-outline-primary to_cart" style="width: 45%">장바구니
					담기</button>
				<button class="btn btn-primary order" style="width: 45%">바로 구매 ></button>
			</div>
		</div>
	</div>
</div>
<jsp:useBean id="today" class="java.util.Date" />
<fmt:formatDate value="${today}" pattern="yyyyMMdd" var="nowDate"/>
<script src='<c:url value="/js/shop.js"><c:param name="dt" value="${nowDate}"/></c:url>'></script>