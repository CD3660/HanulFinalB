<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<div class="container show-grid">
	<c:if test="${loginInfo.admin == 'Y'}">
		<div class="row justify-content-end">
			<div class="col-auto">
				<!-- 여기에 내용을 넣으세요 -->
				<button type="button" class="btn btn-primary me-3"
					id="btn-updatePage">상품 정보 수정</button>
				<button type="button" class="btn btn-primary me-3" id="btn-delete">상품
					삭제</button>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-6 p-3">
			<img src="${vo.prod_img }" style="width: 100%" />
		</div>
		<div class="col-6 p-3">
			<h3>${vo.prod_name }</h3>
			<input type="hidden" id="prod_id" value="${vo.prod_id }" />
			<%-- 상품평과 별점 만드어야함--%>
			<div class="rate">
				<span style="width: ${(vo.rate==null?0:vo.rate)*20}%;"></span>
			</div>
			<span style="color: red;">${vo.review_cnt}개의 상품평</span>
			<%-- 상품평과 별점 만드어야함--%>
			<hr style="color: gray;" />
			<p style="font-size: 0.8em">${vo.detail }</p>
			<div
				style="border-top: 1px solid lightgray; border-bottom: 1px solid lightgray; background-color: #ebebeb;">
				<p class="m-2">
					판매 가격:<span class="ms-3" style="color: red; font-weight: bold;"><fmt:formatNumber
							value="${vo.price}" pattern="###,###원" /></span>
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
				<input type="hidden" id="user_id" value="${loginInfo.user_id }" />
				<button class="btn btn-outline-primary to_cart" style="width: 45%">장바구니
					담기</button>
				<button class="btn btn-primary order" style="width: 45%">바로
					구매 ></button>
			</div>
		</div>
	</div>
	<div class="row">
		<h3 class="mt-4">리뷰</h3>
		<c:if test="${loginInfo.user_id != null }">
			<div class="row justify-content-end">
				<div class="col-auto">
					<!-- 여기에 내용을 넣으세요 -->
					<button type="button" class="btn btn-primary me-3"
						id="btn-review">리뷰 작성</button>
				</div>
			</div>
		</c:if>
		<div class="d-block">
			<div class="d-flex justify-content-center">
				<div style="width: 900px">
					<c:forEach items="${list}" var="vo">
						<div class="row">
							<div class="col-2 p-4">
								<div
									style="width: 60px; height: 60px; border-radius: 70%; overflow: hidden;">
									<img
										src="${vo.profile==null?'/finalb/images/free-icon-profile-6063734.png':vo.profile}"
										style="width: 100%; height: 100%; object-fit: cover;">
								</div>
							</div>
							<div class="col-8 p-4">
								<div>
									<span class="me-3">${vo.user_id}</span>/<span
										class="span-goods-total-price ms-3">${vo.review_date }</span>
									<div class="rate">
										<span style="width: ${(vo.rate==null?0:vo.rate)*20}%;"></span>
									</div>
								</div>
								<div
									style="margin: 5px 0 5px 0; overflow: hidden; height: 1px; border-top: #DDD 1px solid;">
								</div>
								<div>
									<span>
										<jsp:scriptlet>pageContext.setAttribute("newline", "\n");</jsp:scriptlet> <c:out
											value="${fn:replace(vo.content, newline, '<br>')}"
											escapeXml="false" />
									</span>
								</div>
							</div>
							<div class="col-2 p-4">
								<i role="button"
									class="fa-solid fa-trash-can fs-8 text-danger remover ${vo.user_id eq loginInfo.user_id? '':'d-none'}"
									onclick="delete_review(${vo.review_id})"></i>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<script src='<c:url value="/js/shop.js"/>'></script>
<script>
	function delete_review(review_id) {
		location="delete_review?review_id="+review_id+"&prod_id="+${vo.prod_id };
		
	}
	$("#btn-review").click(function() {
		console.log("로그");
		$.ajax({
			url:"/finalb/shop/can_review_check",
			data:{
				user_id: '${loginInfo.user_id}',
				prod_id: ${vo.prod_id}
			}
		}).done(function(resp) {
			if(resp == "success"){
				location="reviewPage?prod_id="+${vo.prod_id};
			} else if(resp == "fail") {
				alert("리뷰 대상자가 아닙니다.\n구매 후 7일 이내에만 리뷰를 작성할 수 있습니다.");
			}
		});
	})
</script>