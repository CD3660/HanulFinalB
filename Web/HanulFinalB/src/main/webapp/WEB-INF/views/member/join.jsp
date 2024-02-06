<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<title>ȸ������</title>

<link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
</head>
<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div class="card shadow-2-strong" style="border-radius: 1rem;">
					<h4 class="mb-3">ȸ������</h4>
					<form method="post" action="joinPro" name="userInfo"
						onsubmit="return checkValue()">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberId">���̵�</label> <input type="text"
									class="form-control" id="memberId" name="memberId"
									maxlength="50" placeholder="���̵� �Է����ּ���" required> <span
									class="idchk"></span>
							</div>
							<div class="col-md-6 mb-3">
								<label for="memberName">�̸�</label> <input type="text"
									class="form-control" id="memberName" placeholder="�̸��� �Է����ּ���"
									value="" required> <span class="memberNamechk"></span>

							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberPw">��й�ȣ</label> <input type="text"
									class="form-control" id="memberPw" placeholder="��й�ȣ�� �Է����ּ���"
									value="" required> <span class="pwchk1"></span>
							</div>

							<div class="col-md-6 mb-3">
								<label for="memberPwCheck">��й�ȣ Ȯ��</label> <input type="text"
									class="form-control" id="memberPwCheck"
									placeholder="��й�ȣ�� �ٽ� �ѹ� �Է����ּ���" value="" required><span
									class="pwchk"></span>
							</div>

						</div>
						<div class="mb-3">
							<label for="email">�̸���</label> <input type="text"
								class="form-control" id="email"
								placeholder="mysmarthome@example.com" required="required">
							<span class="emailchk"></span>
						</div>

						<div class="mb-3">
							<label for="phoneNumber">�޴��� ��ȣ</label> <input type="text"
								class="form-control" id="phoneNumber"> <span
								class="phchk"></span>
						</div>

						<div class="mb-3">
							<label for="address">�����ȣ <span class="text-muted">&nbsp;(���û���)</span>
								<span class="text-muted">
									<button type="button" class="btn" id="check_btn"
										onclick="searchAddress()">ã��</button>
							</span>
							</label> <input type="text" class="form-control" id="address"
								placeholder="ã�⸦ ���� �ּҸ� �Է��ϼ���">
						</div>

						<div class="mb-3">
							<label for="address2">�ּ�<span class="text-muted">&nbsp;(���û���)</span></label>
							<input type="text" class="form-control" id="address2"
								placeholder="�ּҸ� �Է����ּ���.">
						</div>

						<script>
							// Daum �ּҰ˻� �Լ�
							function searchAddress() {
								new daum.Postcode(
										{
											oncomplete : function(data) {
												// ������ �ּ� ������ �ʿ��� �κп� ����
												document
														.getElementById('address').value = data.zonecode; // �����ȣ
												document
														.getElementById('address2').value = data.address; // �ּ�
											}
										}).open();
							}
						</script>
						<div class="mb-4"></div>

						<button class="btn btn-primary btn-lg btn-block" type="submit"
							style="float: right;">���� �Ϸ�</button>


						<button class="btn btn-primary btn-lg btn-block" type="submit"
							style="float: right;">�ڷΰ���</button>
					</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>