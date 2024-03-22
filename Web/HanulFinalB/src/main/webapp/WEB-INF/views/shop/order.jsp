<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<h3 class="m-2">주문 / 결제</h3>
<hr style="color: lightgray;" />
<div class="container show-grid">
	<div class="row">
		<div class="col-7 p-3">
			<h4>회원 정보</h4>
			<table class="m-3"
				style="width: 100%; border-bottom: 1px solid gray; border-top: 1px solid gray;">
				<colgroup>
					<col style="width: 150px; height: 80px;">
					<col>
				</colgroup>
				<tr style="border-bottom: 1px solid lightgray;" style="height:50px;">
					<td class="fw-bold p-2 bg-lightgray">이름</td>
					<td class="p-2">${loginInfo.name }</td>
				</tr>
				<tr style="border-bottom: 1px solid lightgray;" style="height:50px;">
					<td class="fw-bold p-2 bg-lightgray">이메일</td>
					<td class="p-2">${loginInfo.email }</td>
				</tr>
				<tr>
					<td class="fw-bold p-2 bg-lightgray" style="height: 50px;">전화번호</td>
					<td class="p-2">${loginInfo.phone }</td>
				</tr>
			</table>
			<h4>배송 정보</h4>
			<form method="post" action="pay">
				<table class="m-3"
					style="width: 100%; border-bottom: 1px solid gray; border-top: 1px solid gray;">
					<colgroup>
						<col style="width: 150px; height: 80px;">
						<col>
					</colgroup>
					<tr style="border-bottom: 1px solid lightgray;" style="height:50px;">
						<td class="fw-bold p-2 bg-lightgray">받는분</td>
						<td class="p-2">
							<input type="text" style="width:150px;" class="form-control"
							name="reciever" value="${loginInfo.name }" required>
						</td>
					</tr>
					<tr style="border-bottom: 1px solid lightgray;" style="height:50px;">
						<td class="fw-bold p-2 bg-lightgray">배송 주소</td>
						<td class="p-2">
							<div class="d-flex mb-2">
								<input class="form-control t-readonly me-2" type="text" name="post" value="${loginInfo.address }" style="outline:none;width:150px;" required readonly>
								<button class="btn btn-primary" id="post" type="button">우편번호 검색</button>
							</div>
							<div class="d-flex mb-2">
								<input class="form-control t-readonly" type="text" name="address2_1" value="${loginInfo.address2 }" style="outline:none;width:400px;" required readonly>
							</div>
							<div class="d-flex">
								<input class="form-control" type="text" name="address2_2" value="" required>
							</div>
						</td>
					</tr>
					<tr>
						<td class="fw-bold p-2 bg-lightgray" style="height: 50px;">핸드폰번호</td>
						<td class="p-2">
							<input type="text" style="width:150px;" class="form-control"
							name="phone" value="${loginInfo.phone }" required>
						</td>
					</tr>
					<tr>
						<td class="fw-bold p-2 bg-lightgray" style="height: 50px;">배송요청사항</td>
						<td class="p-2">
							<textarea class="form-control" name="request_msg" rows="2" cols="50" style="resize: none;"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="col-5 p-3">
			<h4>주문상품 정보</h4>
			<div style="width: 100%; border: 2px solid gray; margin-bottom: 10px">
				<table style="width: 100%; min-height: 500px">
					<colgroup>
						<col width="100px">
						<col>
					</colgroup>
					<c:forEach items="${list}" var="vo">
						<tr style="height: 80px">
							<td class="d-flex justify-content-center align-items-center" style="height: 80px">
								<img src="${vo.prod_img}" style="max-width: 80px; max-height: 80px">
							</td>
							<td class="left-align" style="height: 80px;">
								<div><span class="fw-bold">${vo.prod_name}</span></div>
								<div style="margin:5px 0 5px 0;overflow:hidden;height:1px; border-top: #DDD 1px solid;">
								</div>
		                        <div>
		                            <span><fmt:formatNumber value="${vo.price}" pattern="###,###원" /></span>
		                            <span> / ${vo.ea}개</span>
		                            <span class="span-goods-total-price"><fmt:formatNumber
							value="${vo.price*vo.ea}" pattern="###,###원" /></span>
		                        </div>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			<div class="d-flex justify-content-center p-3" style="width: 100%; border: 2px solid gray;">
				총 <span class="me-2 ms-2 text-info fw-bold">${fn:length(list) }</span> 건
			</div>
			<h3 class="mt-3">최종결제 정보</h3>
			<div class="mt-3 mb-3 p-2" style="width: 100%; border: 2px solid gray;">
				<div class="d-flex justify-content-between align-items-end">
					<div class="fw-bold">결제 예정액</div>
					<div class="fw-bold"><span class="fw-bold text-danger fs-3"><fmt:formatNumber value="${totalPrice}" pattern="###,###원" /></span></div>
				</div>
				<hr>
				<div class="d-flex justify-content-between align-items-end m-3">
					<div class="d-flex justify-content-center align-items-center" role="button" onclick="pay('tosspayments.iamporttest_3', 'toss')" style="border-radius: 25px; background-color: white;width: 100px; height: 50px"><img src='<c:url value="/images/shop/TossPayments_Logo_Simple_Primary.png"/>' style="width: 100px; height: 40px"/></div>
					<div class="d-flex justify-content-center align-items-center" role="button" onclick="pay('kakaopay.TC0ONETIME', 'kakao')" style="width: 100px; height: 50px"><img src='<c:url value="/images/shop/payment_icon_yellow_small.png"/>' style="width: 100px; height: 40px"/></div>
					<div class="d-flex justify-content-center align-items-center" role="button" onclick="pay('tosspay.tosstest', 'tosspay')" style="border-radius: 25px; background-color: white;width: 100px; height: 50px"><img src='<c:url value="/images/shop/logo-toss-pay.png"/>' style="width: 80px; height: 20px;"/></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src='<c:url value="/js/shop.js"/>'></script>
<script type="text/javascript">
IMP.init('imp75188550');

function pay(pg, uid) {
	IMP.request_pay({
	      pg: pg,
	      pay_method: "card",
	      merchant_uid: "${uid}",   // 주문번호
	      name: "${name}",
	      amount: "${totalPrice}",                         // 숫자 타입
	      buyer_email: "${loginInfo.email}",
	      buyer_name: "${loginInfo.name}",
	      buyer_tel: "${loginInfo.phone}",
	      buyer_addr: "${loginInfo.address2}",
	      buyer_postcode: "${loginInfo.address}"
	    }, function (rsp) { // callback
	    	console.log(rsp);
	    	if (rsp.success) {
	    		$.ajax({
	    			url:"pay",
	    			data:{
	    				amount:"${totalPrice}",
	    				imp_uid:rsp.imp_uid,
	    				merchant_uid:"${uid}",
	    				user_id:"${loginInfo.user_id}",
	    				order_id:"${order_id}",
	    				reciever:$("[name=reciever]").val(),
	    				post:$("[name=post]").val(),
	    				address2_1:$("[name=address2_1]").val(),
	    				address2_2:$("[name=address2_2]").val(),
	    				phone:$("[name=phone]").val(),
	    				request_msg:$("[name=request_msg]").val(),
	    			}
	    		}).done(function(resp) {
	    			console.log(resp);
					if(resp=='success'){
						alert("결제 완료");
						location="list";
					} else {
						alert("오류 발생");
						location="order?prod_id=${order.prod_id}&ea=${order.ea}"
					}
				});
	    	} else {
	    		if(rsp.error_code=='F1002') alert("결제 정보가 일치하지 않습니다.");
	    	}
	    });
}
</script>