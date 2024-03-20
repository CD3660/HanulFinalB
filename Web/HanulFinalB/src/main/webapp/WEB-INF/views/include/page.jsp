<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>



<nav aria-label="page navigation">
	<ul class = "pagination mt-4 justify-content-center">
	
	<!-- 첫번째 블럭이 아닌 경우 보이게  -->
	<c:if test="${page.curBlock gt 1 }">
		<li class="page-item"><a class="page-link" onclick="go_page(1)"><i class="fa-solid fa-angles-left mt-1"></i></a></li>
		<li class="page-item"><a class="page-link" onclick="go_page(${page.beginPage - page.blockPage})"><i class="fa-solid fa-angle-left mt-1"></i></a></li>
	</c:if>
	
	
	
	
	<c:forEach var="no" begin="${page.beginPage}" end="${page.endPage}" step="1">
	
		<c:if test="${no eq page.curPage}">
			<li class="page-item"><a class="page-link active" style="background:#787D62; color: #ffffff;" >${no}</a></li>
		</c:if>
		
		<c:if test="${no ne page.curPage }">
			<li class="page-item"><a class="page-link" onclick="go_page(${no })">${no }</a></li>
		</c:if>
		
	</c:forEach>
	
	
	
	
	 <!-- 마지막블럭이 아닌 경우 보이게 -->
	<c:if test="${page.curBlock lt page.totalBlock }">

		<li class="page-item"><a class="page-link" onclick="go_page(${page.endPage+1})"><!-- <span style="text-indent: -99999px; display: inline-block;">0</span> --><i class="fa-solid fa-angle-right mt-1"></i></a></li>
	    <li class="page-item"><a class="page-link" onclick="go_page(${page.totalPage})"><i class="fa-solid fa-angles-right mt-1" style="text-align: center;"></i></a></li>
	
	</c:if>
	
	
	
	
	</ul>
</nav>


<script>
function go_page( no ) {
	$("[name=curPage]").val( no );
	$("form#listForm").submit();
}


</script>