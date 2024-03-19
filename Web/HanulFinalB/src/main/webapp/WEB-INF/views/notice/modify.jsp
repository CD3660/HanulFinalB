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
<h3 class="mb-4">공지사항 글수정</h3>

<form id="modifyForm" method="post" action="update" enctype="multipart/form-data">
<input type="hidden" name="writer" value="${loginInfo.user_id }">
<table class="table tb-row">
<colgroup>
	<col width="180px">
	<col>
</colgroup>
<tr><th>제목</th>
	<td><input type="text" title="제목" autofocus class="check-empty form-control" value="${vo.title }" name="title"></td>
</tr>
<tr><th>내용</th>
	<td><textarea name="content" title="내용" class="check-empty form-control">${vo.content }</textarea></td>
</tr>



</table>
<input type="hidden" name="qna_id" value="${vo.notice_id }">
<input type="hidden" name="curPage" value="${page.curPage }">
<input type="hidden" name="search" value="${page.search }">
<input type="hidden" name="keyword" value="${page.keyword }">
<input type="hidden" name="pageList" value="${page.pageList }">
</form>


<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-primary px-4" id="btn-save">저장</button>
	<button class="btn btn-outline-primary px-4" id="btn-cancel">취소</button>
</div>






<script>


$("#btn-save").click(function() {
	if( emptyCheck() ) { //입력이 되어 있는 경우만 서브밋
		$("form#modifyForm").submit();
	}
})



$("#btn-cancel").click(function() {
	$("form#modifyForm").attr("action", "info").submit();
})





</script>


</body>
</html>