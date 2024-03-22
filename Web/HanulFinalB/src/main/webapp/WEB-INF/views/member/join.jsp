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
					<form method="post" id="join_form" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-4 mb-3">
								<label for="memberId">���̵�</label> <input type="text"
									class="form-control" id="memberId" name="user_id"
									maxlength="50" placeholder="���̵� �Է����ּ���"> <span
									class="idchk"></span>
							</div>
							<div class="col-md-2 mb-3">
								<input class="btn btn-primary mt-4" type="button" id="idCheck" value="�ߺ� üũ">
							</div>
							<div class="col-md-6 mb-3">
								<label for="memberName">�̸�</label> <input type="text"
									class="form-control" id="memberName" name="name"
									placeholder="�̸��� �Է����ּ���" value=""> <span
									class="memberNamechk"></span>

							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberPw">��й�ȣ</label> <input type="password"
									class="form-control" id="memberPw" name="user_pw"
									placeholder="��й�ȣ�� �Է����ּ���" value=""> <span
									class="pwchk1"></span>
							</div>

							<div class="col-md-6 mb-3">
								<label for="memberPwCheck">��й�ȣ Ȯ��</label> <span class="checkPwInfo"></span>
								<input type="password" class="form-control" id="memberPwCheck"
									name="user_pw_chk" placeholder="��й�ȣ�� �ٽ� �ѹ� �Է����ּ���" value="">
								<span class="pwchk"></span>
							</div>

						</div>
						<div class="mb-3">
							<label for="email">�̸���<span class="text-muted">&nbsp;(���û���)</span></label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="mysmarthome@example.com">
							<span class="emailchk"></span>
						</div>

						<div class="mb-3">
							<label for="phoneNumber">�޴��� ��ȣ<span class="text-muted">&nbsp;(���û���)</span></label> <input type="text"
								class="form-control" id="phoneNumber" name="phone"> <span
								class="phchk"></span>
						</div>

						<div class="mb-3">
							<label for="address">������ȣ <span class="text-muted">&nbsp;(���û���)</span>
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
						<div class="mb-4" class="d-block">
							<label for="file">������ ����</label> 
							<label class="label me-3">
								<input type="file" name="file" />
								<span class="btn btn-primary">�̹��� ÷��</span>
							</label>
								<i role="button" class="d-none fa-solid fa-trash-can fs-8 text-danger remover"></i>
							<div class="m-2" style="max-width: 700px; word-break: break-word;"></div>
							<div class="preview">
							</div>
						</div>

						<button class="btn btn-primary btn-lg btn-block" type="button"
							id=join-btn style="float: right; margin-left: 10px;" onclick="join()">����
							�Ϸ�</button>

						<a href="/finalb/member/login"
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
			if($('#memberId').val()==""){
				alert("���̵�� �ʼ� �Է��Դϴ�.");
				return;
			}
			if($('#memberName').val()==""){
				alert("�̸��� �ʼ� �Է��Դϴ�.");
				return;
			}
			if($('#memberPw').val()==""){
				alert("��й�ȣ�� �ʼ� �Է��Դϴ�.");
				return;
			}
			if(!validateEmail()){
				return;
			}
			if(!validatePhoneNumber()){
				return;	
			}
			if(pwCk && idCk){
				$("#join_form").attr("action", "/finalb/member/joinAction");
				$("#join_form").submit();
			}else if(!idCk){
				alert("���̵� �ߺ�üũ�� ���ּ���.");
			}else if(!pwCk){
				alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				$('#memberPw').focus();
			}
			
		}

		

		//Daum �ּҰ˻� �Լ�
		function searchAddress() {
			new daum.Postcode({
				oncomplete : function(data) {
					// ������ �ּ� ������ �ʿ��� �κп� ����
					document.getElementById('address').value = data.zonecode; // ������ȣ
					document.getElementById('address2').value = data.address; // �ּ�
				}
			}).open();
		}
		//���̵� �ߺ� Ȯ��
		$("#idCheck").click(function() {
			var id = $('#memberId').val();
			if(id==""){
				alert("���̵� �Է��ϼ���.");
				return;
			}
			$.ajax({
				type : 'POST',
				data : {id: id},
				url : "idCheck",
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
					alert("���� ����. �ٽ� �õ��ϼ���.");
				}
			});
		});

		// �н����� �ߺ��˻�
		$('#memberPwCheck').keyup(function() {
			var pw = $('#memberPw').val();
			var pwCheck = $('#memberPwCheck').val();

			if (pw === pwCheck) {
				//alert("��� ��ġ");
				$(".checkPwInfo").html("��й�ȣ ��ġ");
				$(".checkPwInfo").removeClass("text-danger");
				$(".checkPwInfo").addClass("text-success");
				pwCk = true;
			} else {
				$(".checkPwInfo").html("��й�ȣ ����ġ");
				$(".checkPwInfo").removeClass("text-success");
				$(".checkPwInfo").addClass("text-danger");
				//alert("��� ����ġ");
				pwCk = false;
			}

		});

		$("#memberPw").keyup(function() {
			$('#memberPwCheck').val("");
			$(".checkPwInfo").html("");
// 			$('#memberPwCheck').focus();
			pwCk = false;
		});
		
		$("[name=file]").change(function() {
			console.log(this.files[0]);
			$("[name=maintain]").val("false");
			if (!this.files[0]) {
				remove_img();
			}
			else {
				$(".remover").removeClass("d-none");
				$(".label").next("div").html(this.files[0].name);
				$(".preview").html("<img class='pre_img' style='max-width: 300px;  max - height: 300px; '/>");
				var reader = new FileReader();
				reader.onload = function(e) {
					// �̹����� img �±׿� ǥ��
					$(".pre_img").attr('src', e.target.result);
				}
				reader.readAsDataURL(this.files[0]);
			}
		});
		$(".remover").click(function() {
			remove_img();
			$(".remover").addClass("d-none");
		});
		function remove_img() {
			$(".label").next("div").html("");
			$(".preview").html("");
			$("[name=file]").val("");
			$("[name=maintain]").val("false");
		}
		function emailCheck(email_address){     
			email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
			if(!email_regex.test(email_address)){ 
				return false; 
			}else{
				return true;
			}
		}
		function phoneNumberCheck(number){
		    let result = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		    return result.test(number);
		}
		function validateEmail() {
			var email = $("#email").val();

			if (emailCheck(email)) {
				return true;
			} else {
				alert('��ȿ���� ���� �̸��� �ּ��Դϴ�.');
				$("#email").focus();
				return false;
			}
		}
		function validatePhoneNumber() {
			var email = $("#phone").val();

			if (emailCheck(email)) {
				return true;
			} else {
				alert('��ȿ���� ���� ��ȭ��ȣ�Դϴ�.');
				$("#phone").focus();
				return false;
			}
		}

	</script>
</body>
</html>