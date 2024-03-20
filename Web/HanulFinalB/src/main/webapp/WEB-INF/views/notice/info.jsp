<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="mb-4">공지사항 글정보</h3>


<table class="table tb-row">
<colgroup>
	<col width="180px">
	<col>
	<col width="120px">
	<col width="120px">
	<col width="100px">
	<col width="100px">
</colgroup>
<tr><th>제목</th>
	<td colspan="5">${vo.title }</td>
</tr>
<tr><th>작성자</th><td>${vo.name }</td>
	<th>작성일자</th><td>${vo.writedate }</td>
	<th>조회수</th><td>${vo.readcnt }</td>
</tr>
<tr><th>내용</th>
	<td colspan="5">${fn: replace( vo.content, crlf, "<br>" )}</td>
</tr>
</table>



<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-primary" id="btn-list">목록으로</button>

	<!-- 작성자로 로그인한 경우만 수정/삭제 보이게  -->
	<c:if test="${loginInfo.admin eq 'Y'}">
	<button class="btn btn-primary" id="btn-modify">정보수정</button>
	<button class="btn btn-primary" id="btn-delete">정보삭제</button>
	</c:if>

</div>




<form id="voform" method="post">
<input type="hidden" name="notice_id" value="${vo.notice_id }">   <!-- QnaVO -->
<input type="hidden" name="curPage" value="${page.curPage }"> <!-- PageVO -->
<input type="hidden" name="search" value="${page.search }">
<input type="hidden" name="keyword" value="${page.keyword }">
<input type="hidden" name="pageList" value="${page.pageList }">
<input type="hidden" name="url" value="notice/info">
</form>




<c:set var="params" value="curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}"/>










<script>




$("#btn-list, #btn-modify, #btn-delete").click(function() {
	var id = $(this).attr("id"); // 클린된 버튼에 대한 id="btn-modify, id="btn-delete"
	id = id.substr( id.indexOf("-")+1 );
	$("form#voform").attr("action", id);
	if( id == "delete" ) {
		if( confirm("정말 삭제하시겠습니까?") ) {
			$("form#voform").submit();
		}
		
	}else $("form#voform").submit();
	
})


</script>


</body>
</html>