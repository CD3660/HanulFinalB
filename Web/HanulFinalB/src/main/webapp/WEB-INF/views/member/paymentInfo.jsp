<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<h3 class="m-2">결제 상세 내역</h3>
<hr style="color: lightgray;" />
<div class="container show-grid">
	<div class="row">
		<div class="col-7 p-3">
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
							name="reciever" value="${vo.reciever }" required readonly>
						</td>
					</tr>
					<tr style="border-bottom: 1px solid lightgray;" style="height:50px;">
						<td class="fw-bold p-2 bg-lightgray">배송 주소</td>
						<td class="p-2">
							<div class="d-flex mb-2">
								<input class="form-control t-readonly me-2" type="text" name="post" value="${vo.post }" style="outline:none;width:150px;" required readonly>
							</div>
							<div class="d-flex mb-2">
								<input class="form-control t-readonly" type="text" name="address2_1" value="${vo.address }" style="outline:none;width:400px;" required readonly>
							</div>
							<div class="d-flex">
								<input class="form-control" type="text" name="address2_2" value="결제 시각 : ${vo.payment_time }" required readonly>
							</div>
						</td>
					</tr>
					<tr>
						<td class="fw-bold p-2 bg-lightgray" style="height: 50px;">핸드폰번호</td>
						<td class="p-2">
							<input type="text" style="width:150px;" class="form-control"
							name="phone" value="${vo.phone }" required readonly>
						</td>
					</tr>
					<tr>
						<td class="fw-bold p-2 bg-lightgray" style="height: 50px;">배송요청사항</td>
						<td class="p-2">
							<textarea class="form-control" name="request_msg" rows="2" cols="50" style="resize: none;" readonly>${vo.request_msg}</textarea>
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
					<c:forEach items="${list}" var="item">
						<tr style="height: 80px">
							<td class="d-flex justify-content-center align-items-center" style="height: 80px">
								<img src="${item.prod_img}" style="max-width: 80px; max-height: 80px">
							</td>
							<td class="left-align" style="height: 80px;">
								<div><span class="fw-bold">${item.prod_name}</span></div>
								<div style="margin:5px 0 5px 0;overflow:hidden;height:1px; border-top: #DDD 1px solid;">
								</div>
		                        <div>
		                            <span>${item.price}원</span>
		                            <span> / ${item.ea}개</span>
		                            <span class="span-goods-total-price">${item.price*item.ea}원</span>
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
					<div class="fw-bold">결제 완료 금액</div>
					<div class="fw-bold"><span class="fw-bold text-danger fs-3">${vo.amount}</span>원</div>
				</div>
			</div>
		</div>
	</div>
</div>
