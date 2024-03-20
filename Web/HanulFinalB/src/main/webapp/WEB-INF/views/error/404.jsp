<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="row justify-content-center">
	<div class="col-lg-5">
		<div class="card shadow-lg border-0 rounded-lg mt-5">
			<div class="card-header">
				<h3 class="text-center font-weight-light my-4">
					<a href="<c:url value='/'/>"><img
						src="<c:url value='/images/hanul.logo.png'/>" /> </a>
				</h3>
			</div>
			<div class="card-body">
				<h5>죄송합니다.<br>요청하신 페이지를 찾을 수 없습니다.</h5>
				<p>방문하시려는 페이지의 주소가 잘못 입력되었거나,</p>
				<p>페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</p>
				<p>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</p>
				<a href="<c:url value='/'/>">홈으로</a>
			</div> 
		</div>
	</div>
</div>
