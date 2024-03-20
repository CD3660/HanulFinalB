<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>


<div class="d-flex justify-content-center">
	<div class="m-3 gray-border">
		<div class="p-3 bg-title">
			<span class="text-white">상품 정보 수정</span>
		</div>
		<div class="p-3">
			<form class="update" method="post" enctype="multipart/form-data"
				action="update">
				<input type="hidden" name="maintain" value="true"/>
				<input type="hidden" name="prod_id" value="${vo.prod_id }"/>
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
							name="prod_name" placeholder="예시) 가스 센서 gas-v2"
							value="${vo.prod_name}">[<span class="writing text-info">${fn:length(vo.prod_name) }</span>/100]</td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">판매가</span><span
							class="text-info">(필수)</span></td>
						<td class="p-2 ps-3"><input
							class="form-control d-inline me-3" type="text" name="price"
							style="width: 30%" value="${vo.price}"></td>
					</tr>

					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">상품 간략
								설명</span></td>
						<td class="p-2 ps-3"><textarea class="form-control"
								name="detail" style="resize: none;">${vo.detail}</textarea>[<span
							class="writing text-info">${fn:length(vo.detail) }</span>/500]</td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">대표이미지</span></td>
						<td class="p-2 ps-3"><label class="label me-3"><input
								type="file" name="file" /><span class="btn btn-primary">이미지
									첨부</span></label><i role="button"
							class="${empty vo.prod_img ?'d-none ':''}fa-solid fa-trash-can fs-8 text-danger remover"></i>
							<div class="m-2"
								style="max-width: 700px; word-break: break-word;"></div>
							<div class="preview">
								<c:if test="${!empty vo.prod_img }">
									<img class='pre_img' src="${vo.prod_img}"
										style='max-width: 300px;' />
								</c:if>
							</div></td>
					</tr>
				</table>
				<div class="d-flex justify-content-end m-2">
					<button type="button" class="btn btn-primary" id="btn-update">상품
						정보 수정</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src='<c:url value="/js/shop.js"/>'></script>