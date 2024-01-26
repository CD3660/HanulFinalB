<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<!-- 전체의 2/3 (8 out of 12) -->
				<div class="input-group mb-3 align-items-center">
					<input type="text" class="form-control search"
						aria-label="Text input with dropdown button"> <i
						role="button" class="fa-solid fa-magnifying-glass search-icon"></i>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<p>This is the main content.</p>
			</div>
			<div class="col-md-4">
				<!-- 작은 화면에서 보이는 버튼 -->
				<button type="button" class="btn btn-primary d-block d-sm-none"
					data-bs-toggle="collapse" data-bs-target="#hiddenList">
					Show List</button>
				<!-- 숨겨진 리스트 -->
				<div id="hiddenList" class="collapse">
					<ul class="list-group">
						<li class="list-group-item">Item 1</li>
						<li class="list-group-item">Item 2</li>
						<li class="list-group-item">Item 3</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>