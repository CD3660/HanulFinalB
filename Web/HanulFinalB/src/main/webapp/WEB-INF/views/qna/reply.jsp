<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="mt-4">Q&A 답글쓰기</h3>

<form id="replyform" method="post" action="replyInsert">
<input type="hidden" name="writer"  value="${loginInfo.user_id }">
<table class="table tb-row">
<colgroup>
	<col width="180px">
	<col>
</colgroup>
<tr><th>제목</th>
	<td><input type="text"  name="title" title="제목" class="form-control check-empty"></td>
<tr><th>내용</th>
	<td><textarea name="content" title="내용"  class="check-empty form-control" style="height: 200px;" ></textarea></td>
</table>

<input type="hidden" name="curPage" value="${page.curPage }">
<input type="hidden" name="search" value="${page.search }">
<input type="hidden" name="keyword" value="${page.keyword }">
<!-- 원글정보  -->
<input type="hidden" name="root" value="${vo.root }">
<input type="hidden" name="step" value="${vo.step }">
<input type="hidden" name="indent" value="${vo.indent }">
<input type="hidden" name="rid" value="${vo.qna_id }">
</form>


<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-primary px-4" id="btn-save">저장</button>
	<button class="btn btn-outline-primary px-4" id="btn-cancel">저장</button>
</div>




<script>

$("#btn-save").click(function(){
	if( emptyCheck() ){ //입력이 되어있는 경우만 서브밋
		$("form#replyform").submit()
	}
})


$("#btn-cancel").click(function() {
	location="info?qna_id=${vo.qna_id}&curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}"
})


</script>

</body>
</html>