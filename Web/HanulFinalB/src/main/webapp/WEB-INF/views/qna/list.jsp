<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&amp;A 목록</title>




</head>
<body>




<h3 class="mt-4">Q&A</h3>
<form id="listForm" method="post" action="list">
	<div class="row mb-2 justify-content-between">
			<div class = "col-auto">
				<div class="input-group">
					<select name="search" class="form-select" style="width:120px">
						<option value="s1" ${page.search eq "s1" ? "selected" : ""}>전체</option>
						<option value="s2"  <c:if test="${page.search eq 's2'}">selected</c:if> >제목</option>
						<option value="s3" ${page.search eq "s3" ? "selected" : ""}>내용</option>
						<option value="s4" ${page.search eq "s4" ? "selected" : ""}>작성자</option>
						<option value="s5" ${page.search eq "s5" ? "selected" : ""}>제목+내용</option>
					</select>
					
					<input type="text" name="keyword" class="form-control"	style="width:170px" value="${page.keyword }">
					<button class="btn btn-primary"><i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</div>
			
			
			
			<div class="col-auto d-flex justify-content-end">
				<select name="pageList" class="form-select">
					<c:forEach var="i" begin="1" end="5">
						<option value="${10*i }">${10*i}개씩</option>
					</c:forEach>
				</select>
			
			
	
		
		
				
				<!-- 로그인되어 있는 경우만 -->
				<c:if test="${ ! empty loginInfo }">
					<div class="col-auto">
						<button type="button" class="btn btn-primary"
							onclick="location='register'">글쓰기</button>
					</div>
				</c:if>
			
			</div>
		
		
	</div>
	
	
			<input type="hidden" name="qna_id">
			<input type="hidden" name="curPage" value="1">
	
	
</form>


	<table class="table tb-list">
			<colgroup>
				<col width='100px'>
				<col>
				<col width='120px'>
				<col width='120px'>
				<col width='100px'>
			</colgroup>
			<tr>
				<th style="text-align: center;">번호</th>
				<th>제목</th>
				<th style="text-align: center;">작성자</th>
				<th style="text-align: center;">작성일자</th>
				<th style="text-align: center;">조회수</th>
			</tr>


		<c:if test="${empty page.list}">
			<tr>
				<td colspan="5" style="text-align: center;">방명록 글이 없습니다</td>
			</tr>
		</c:if>


		<c:if test="${not empty page.list}">
			<c:forEach items="${page.list}" var="vo">
				<tr>
					<td  style="text-align: center;">${vo.no}</td>
					<td class="align-middle">
					<span style="margin-left: ${15*vo.indent }px"></span>
					<c:if test="${vo.indent >0}"><i class="fa-regular fa-comment-dots"></i></c:if>
					<a href="javascript:info( ${vo.qna_id } )">${vo.title }</a>
					
						<c:if test="${vo.filecnt gt 0}">
							<i class="fa-solid fa-paperclip"></i>
						</c:if></td>
					<td style="text-align: center;">${vo.name }</td>
					<td style="text-align: center;">${vo.writedate }</td>
					<td style="text-align: center;">${vo.readcnt }</td>
				</tr>
			</c:forEach>
		</c:if>




	</table>


 <jsp:include page="/WEB-INF/views/include/page.jsp" /> 


<script>


$("[name=pageList]").change(function(){
	$("form#listForm").submit()
})



//해당 목록수가 선택되어져 있게
$("[name=pageList]").val( ${page.pageList}).prop("selected", true)


function info(qna_id) {
	$("[name=qna_id]").val(qna_id);
	$("[name=curPage]").val( ${page.curPage} );
	$("form#listForm").attr("action", "info").submit();
}



</script>


</body>
</html>