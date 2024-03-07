<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		</div>
		<div class="col-5 p-3"></div>
	</div>
</div>
<script src='<c:url value="/js/shop.js"/>'></script>
