<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<div class="d-flex justify-content-center">
	<div class="m-3 gray-border">
		<div class="p-3 bg-title">
			<span class="text-white">상품 정보 추가</span>
		</div>
		<div class="p-3">
			<form class="insert" method="post" enctype="multipart/form-data"
				action="insert">
				<table class="w-100">
					<colgroup>
						<col class="insert-col1">
						<col class="insert-col2">
					</colgroup>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">상품명</span><span
							class="text-info">(필수)</span></td>
						<td class="p-2 ps-3"><input
							class="form-control d-inline me-3" type="text" style="width: 70%"
							name="prod_name" placeholder="예시) 가스 센서 gas-v2">[<span
							class="writing text-info">0</span>/100]</td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">판매가</span><span
							class="text-info">(필수)</span></td>
						<td class="p-2 ps-3"><input
							class="form-control d-inline me-3" type="text" name="price"
							style="width: 30%"></td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">상품 간략
								설명</span></td>
						<td class="p-2 ps-3"><textarea class="form-control"
								name="detail" style="resize: none;"></textarea>[<span
							class="writing text-info">0</span>/500]</td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">대표이미지</span></td>
						<td class="p-2 ps-3"><label class="label me-3"><input
								type="file" name="file" /><span class="btn btn-primary">이미지
									첨부</span></label><i role="button"
							class="fa-solid fa-trash-can fs-8 text-danger remover d-none"></i>
							<div class="m-2"
								style="max-width: 700px; word-break: break-word;"></div>
							<div class="preview"></div></td>
					</tr>
				</table>
				<div class="d-flex justify-content-end m-2">
					<button type="button" class="btn btn-primary" id="btn-insert">상품
						등록</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src='<c:url value="/js/shop.js"/>'></script>