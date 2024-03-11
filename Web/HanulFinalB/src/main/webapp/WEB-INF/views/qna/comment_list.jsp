<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!-- 댓글이 없는 경우 -->
<c:if test="${empty list}">
	<div class="py-3 text-center">
		<div class="fs-5">등록된 댓글이 없습니다</div>
		<div>첫번째 댓글을 남겨주세요</div>
	</div>
</c:if>	