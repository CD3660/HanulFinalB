<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3 class="mb-4">공지사항 글등록</h3>

	<form id="insertForm" method="post" action="insert"	enctype="multipart/form-data">
		<input type="hidden" name="writer" value="${loginInfo.user_id}">
		<table class="table tb-row">
			<colgroup>
				<col width="180px">
				<col>
			</colgroup>

			<tr>
				<th>제목</th>
				<td><input type="text" title="제목" autofocus
					class="check-empty form-control" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" title="내용"
						class="check-empty form-control" style="height: 200px;"></textarea></td>
			</tr>
		</table>


	</form>


	<div class="btn-toolbar justify-content-center gap-2">
		<button class="btn btn-primary px-4" id="btn-save">저장</button>
		<button class="btn btn-outline-primary px-4"
			id="btn-cancel">취소</button>
	</div>




	<script>
		

		$("#btn-save").click(function() {
			if (emptyCheck()) { //입력되어 있는 경우만 서브밋 
				$("form#insertForm").submit();
			}
		})

		
		
		$("#btn-cancel").click(function() {
			location = "list"
		})
	</script>





</body>
</html>