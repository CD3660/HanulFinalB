<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<title>ȸ������</title>

<link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
</head>
<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div class="card shadow-2-strong p-3" style="border-radius: 1rem;">
					<h4 class="mb-3">ȸ������</h4>	
					<form method="post" id="join_form">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberId">���̵�</label> <input type="text"
									class="form-control" id="memberId" name="user_id"
									maxlength="50" placeholder="���̵� �Է����ּ���" required> <span
									class="idchk"></span>
							</div>
							<div class="col-md-6 mb-3">
								<label for="memberName">�̸�</label> <input type="text"
									class="form-control" id="memberName" name="name"
									placeholder="�̸��� �Է����ּ���" value="" required> <span
									class="memberNamechk"></span>

							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberPw">��й�ȣ</label> <input type="password"
									class="form-control" id="memberPw" name="user_pw"
									placeholder="��й�ȣ�� �Է����ּ���" value="" required> <span
									class="pwchk1"></span>
							</div>

							<div class="col-md-6 mb-3">
								<label for="memberPwCheck">��й�ȣ Ȯ��</label> <input
									type="password" class="form-control" id="memberPwCheck"
									name="user_pw_chk" placeholder="��й�ȣ�� �ٽ� �ѹ� �Է����ּ���" value=""
									required><span class="pwchk"></span>
							</div>

						</div>
						<div class="mb-3">
							<label for="email">�̸���</label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="mysmarthome@example.com" required="required">
							<span class="emailchk"></span>
						</div>

						<div class="mb-3">
							<label for="phoneNumber">�޴��� ��ȣ</label> <input type="text"
								class="form-control" id="phoneNumber" name="phone" placeholder="�޴��� ��ȣ�� �Է����ּ���"> <span
								class="phchk"></span>
						</div>

						<div class="mb-3">
							<label for="address">�����ȣ <span class="text-muted">&nbsp;(���û���)</span>
								<span class="text-muted">
									<button type="button" class="btn" id="check_btn" 
										onclick="searchAddress()">ã��</button>
							</span>
							</label> <input type="text" class="form-control" id="address"
								name="address" placeholder="ã�⸦ ���� �ּҸ� �Է��ϼ���">
						</div>

						<div class="mb-3">
							<label for="address2">�ּ�<span class="text-muted">&nbsp;(���û���)</span></label>
							<input type="text" class="form-control" id="address2"
								name="address2" placeholder="�ּҸ� �Է����ּ���.">
						</div>



						<div class="mb-4"></div>

						<button class="btn btn-primary btn-lg btn-block" type="button"
							id=join-btn style="float: right; margin-left: 10px;" onclick="join()">����
							�Ϸ�</button>

						<a href="http://localhost:8080/finalb/member/login"
							class="btn btn-primary btn-lg btn-block" id=back-btn
							style="float: right; display: block;">�ڷΰ���</a>

					</form>


				</div>
			</div>
		</div>

	</div>
	<script>
		// ���� ��
		var pwCk = false;
		var idCk = false;
		
		
		// �����
		function join(){
			if(pwCk && idCk){
				$("#join_form").attr("action", "/finalb/member/joinAction");
				$("#join_form").submit();
			}else if(!idCk){
				alert("�ߺ��� ���̵��Դϴ�.");
			}else if(!pwCk){
				alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			}
			
		}

		

		//Daum �ּҰ˻� �Լ�
		function searchAddress() {
			new daum.Postcode({
				oncomplete : function(data) {
					// ������ �ּ� ������ �ʿ��� �κп� ����
					document.getElementById('address').value = data.zonecode; // �����ȣ
					document.getElementById('address2').value = data.address; // �ּ�
				}
			}).open();
		}

		// ���̵� �ߺ�Ȯ��
		var checkId = $('#memberId');

		checkId.blur(function() {
			var id = $('#memberId').val();

			$.ajax({
				async : true,
				type : 'POST',
				data : id,
				url : "idCheck",
				dataType : "json",
				contentType : "application/json; charset=UTF-8",
				success : function(data) {
					if (data.cnt > 0) {
						alert("���̵� �����մϴ�. �ٸ� ���̵� �Է����ּ���.");
						idCk = false;
					} else {
						alert("��밡���� ���̵��Դϴ�.");
						idCk = true;
					}
				},
				error : function(error) {

					alert("���̵� �Է����ּ���");
				}
			});

		});

		// �н����� �ߺ��˻�
		$('#memberPwCheck').blur(function() {
			var pw = $('#memberPw').val();
			var pwCheck = $('#memberPwCheck').val();

			if (pw === pwCheck) {
				alert("��� ��ġ");
				pwCk = true;

			} else {
				alert("��� ����ġ");
			}

		});

		$("#memberPw").keyup(function() {
			$('#memberPwCheck').val("");
// 			$('#memberPwCheck').focus();
			pwCk = false;
		});
	</script>
</body>
</html>