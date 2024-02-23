<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="d-flex justify-content-center">
	<div class="m-3 gray-border">
		<div class="p-3 bg-title">
			<span class="text-white">기본 정보</span>
		</div>
		<div class="p-3">
			<form class="insert" method="post">
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
							class="form-control d-inline me-3" type="number" name="price"
							style="width: 30%"></td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">재고</span><span
							class="text-info">(필수)</span></td>
						<td class="p-2 ps-3"><input
							class="form-control d-inline me-3" type="number" name="stock"
							style="width: 30%"></td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">상품 간략
								설명</span></td>
						<td class="p-2 ps-3"><textarea class="form-control"
								name="detail" style="resize: none;"></textarea></td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">상품 설명</span></td>
						<td class="p-2 ps-3"><span style="background-color: white;"><textarea id="summernote"
								name="editordata"></textarea></span><button type="button" class="btn-detail">테스트 버튼</button></td>
					</tr>
					<tr>
						<td class="p-2 ps-3"><span class="fw-bold me-2">대표이미지</span></td>
						<td class="p-2 ps-3"><label><input type="file" />파일시스템</label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>

