<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
								type="text" id="user_id" class="form-control form-control-lg" />

						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="typePasswordX-2">��й�ȣ</label> <input
								type="password" id="user_pw"
								class="form-control form-control-lg" />

						</div>

						<button class="btn btn-primary btn-lg btn-block" type="button"
							onclick="login()">�α���</button>
						<hr class="my-4">

						<div class="row pt-3 justify-content-center">
							<div class="col pb-4 mx-auto">
								<ul class="lgacc-tab justify-content-center">
									<li class="tab-item"><a id="btn-register" class="tab-link"
										href="<c:url value='/member/joinView'/>">���� ȸ���� �ƴ϶��? <span class="text-danger fw-bold">ȸ������</span></a></li>

								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#user_pw').keypress(function(e) {
				if (e.keyCode === 13) { // ���� Ű�� ������ ��
					login(); // �α��� �Լ� ȣ��
				}
			});
		});

		function login() {
			var userInfo = {};
			userInfo.user_id = $('#user_id').val();
			userInfo.user_pw = $('#user_pw').val();

			$.ajax({
				data : userInfo,
				url : "loginAction",
				contentType : "application/json; charset=UTF-8",
				success : function(data) {
					if (data.code === '0') {
						console.log("����");
						location = "/finalb/"
					} else if (data.code === '-1') {
						alert("���̵� �Ǵ� �н����尡 Ʋ���ϴ�");
					}
				},
				error : function(error) {
					alert("���̵� �Ǵ� �н����尡 Ʋ���ϴ�");
				}
			});
		}
	</script>
</body>
</html>