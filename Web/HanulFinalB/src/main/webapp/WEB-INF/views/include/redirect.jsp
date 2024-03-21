<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<form method="post" action="<c:url value='/${url}'/>">

<input type="hidden" name="qna_id" value="${qna_id }">
<input type="hidden" name="notice_id" value="${notice_id }">
<input type="hidden" name="curPage" value="${page.curPage }">
<input type="hidden" name="search" value="${page.search }">
<input type="hidden" name="keyword" value="${page.keyword }">
<input type="hidden" name="pageList" value="${page.pageList }">

</form>  
    
    
    
    
    
<script>
$("form").submit()
</script>