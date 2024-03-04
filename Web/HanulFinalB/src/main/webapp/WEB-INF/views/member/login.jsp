<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
<link rel="stylesheet" href="<c:url value='/css/member/login.css'/>">
</head>
<body>

	<div class="container py-5 h-100">
		<div class="row d-flex justify-content-center align-items-center h-95">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card shadow-2-strong" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">

						<h3 class="mb-5">�α���</h3>

						<div class="form-outline mb-4">
							<label class="form-label" for="typeEmailX-2">���̵�</label> <input
								type="email" id="typeEmailX-2"
								class="form-control form-control-lg" />

						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="typePasswordX-2">��й�ȣ</label> <input
								type="password" id="typePasswordX-2"
								class="form-control form-control-lg" />

						</div>

						<!-- Checkbox -->
						<div class="form-check d-flex justify-content-start mb-4">
							<input class="form-check-input" type="checkbox" value=""
								id="form1Example3" /> <label class="form-check-label"
								for="form1Example3"> Remember password </label>
						</div>
						<form  method="GET">
							<button class="btn btn-primary btn-lg btn-block" type="submit">�α���</button>
						</form>
						<hr class="my-4">

						<div class="row pt-3 justify-content-center">
							<div class="col pb-4 mx-auto">
								<ul class="lgacc-tab">
									<li class="tab-item"><a class="tab-link"
										id="btn-search-id" href="javascript:void(0);">���̵� ã��</a></li>
									<li class="tab-item"><a id="reset-password"
										class="tab-link" href="javascript:void(0)">��й�ȣ �缳��</a>
								</ul>
								<ul class="lgacc-tab justify-content-center">
									<li class="tab-item"><a id="btn-register" class="tab-link"
										href="http://localhost:8080/finalb/member/join"> ȸ������</a></li>
									
								</ul>
							</div>
						</div>

						<!-- Register buttons -->
						<div class="text-center">
							<p>�ٸ� �������� �α���</p>
							<button type="button" class="btn btn-link btn-floating mx-1">
								<i class="fab fa-naver-f"></i>
							</button>

						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<script type="text/javascript">
		$(function() {
			if(${!empty fail}) alert("���̵� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		})
	
		$("#naver, #kakao").click(function() {
			location = $(this).attr("id") + "Login";
		})	
	</script>
</body>
</html>