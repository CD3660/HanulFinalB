<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<style>
.comment textarea { height: 90px }
.h-px40 { height: 40px }
</style>



<div id="comment-regist" class="row my-4 justify-content-center">

	<div class="w-pct80 justify-content-between d-flex pb-2 h-px40">
		<span>댓글작성 [ <span class="writing">0</span>] / 200 ]</span>
		<a class="btn btn-outline-primary btn-sm d-none btn-register">댓글등록</a>
	</div>
	
	<div class="comment w-pct80">
		<div class="form-control py-3 text-center guide">
		${empty loginInfo ? "댓글을 입력하려면 여기를 클릭후 로그인하세요" : "댓글을 입력하세요"}
		</div>
	</div>
	
</div>


<div id="comment-list" class="row justify-content-center mx-0">
</div>



<script>








</script>