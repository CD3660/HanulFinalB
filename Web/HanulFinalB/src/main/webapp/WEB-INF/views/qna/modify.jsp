<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="mb-4">Q&A 글수정</h3>

<form method="post" action="update" enctype="multipart/form-data">
<input type="hidden" name="writer" value="${loginInfo.user_id }">
<table class="table tb-row">
<colgroup>
	<col width="180px">
</colgroup>
<tr><th>제목</th>
	<td><input type="text">
</tr>

	<td><textarea rows="" cols=""></textarea>



</table>
</form>



</body>
</html>