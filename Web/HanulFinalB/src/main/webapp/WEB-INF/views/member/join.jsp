<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<title>회원가입</title>

<link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
</head>
<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div class="card shadow-2-strong p-3" style="border-radius: 1rem;">
					<h4 class="mb-3">회원가입</h4>	
					<form method="post" id="join_form" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-4 mb-3">
								<label for="memberId">아이디</label> <input type="text"
									class="form-control" id="memberId" name="user_id"
									maxlength="50" placeholder="아이디를 입력해주세요"> <span
									class="idchk"></span>
							</div>
							<div class="col-md-2 mb-3">
								<input class="btn btn-primary mt-4" type="button" id="idCheck" value="중복 체크">
							</div>
							<div class="col-md-6 mb-3">
								<label for="memberName">이름</label> <input type="text"
									class="form-control" id="memberName" name="name"
									placeholder="이름을 입력해주세요" value=""> <span
									class="memberNamechk"></span>

							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberPw">비밀번호</label> <input type="password"
									class="form-control" id="memberPw" name="user_pw"
									placeholder="비밀번호를 입력해주세요" value=""> <span
									class="pwchk1"></span>
							</div>

							<div class="col-md-6 mb-3">
								<label for="memberPwCheck">비밀번호 확인</label> <span class="checkPwInfo"></span>
								<input type="password" class="form-control" id="memberPwCheck"
									name="user_pw_chk" placeholder="비밀번호를 다시 한번 입력해주세요" value="">
								<span class="pwchk"></span>
							</div>

						</div>
						<div class="mb-3">
							<label for="email">이메일<span class="text-muted">&nbsp;(선택사항)</span></label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="mysmarthome@example.com">
							<span class="emailchk"></span>
						</div>

						<div class="mb-3">
							<label for="phoneNumber">휴대폰 번호<span class="text-muted">&nbsp;(선택사항)</span></label> <input type="text"
								class="form-control" id="phoneNumber" name="phone"> <span
								class="phchk"></span>
						</div>

						<div class="mb-3">
							<label for="address">우편번호 <span class="text-muted">&nbsp;(선택사항)</span>
								<span class="text-muted">
									<button type="button" class="btn" id="check_btn" 
										onclick="searchAddress()">찾기</button>
							</span>
							</label> <input type="text" class="form-control" id="address"
								name="address" placeholder="찾기를 눌러 주소를 입력하세요">
						</div>

						<div class="mb-3">
							<label for="address2">주소<span class="text-muted">&nbsp;(선택사항)</span></label>
							<input type="text" class="form-control" id="address2"
								name="address2" placeholder="주소를 입력해주세요.">
						</div>
						<div class="mb-4" class="d-block">
							<label for="file">프로필 사진</label> 
							<label class="label me-3">
								<input type="file" name="file" />
								<span class="btn btn-primary">이미지 첨부</span>
							</label>
								<i role="button" class="d-none fa-solid fa-trash-can fs-8 text-danger remover"></i>
							<div class="m-2" style="max-width: 700px; word-break: break-word;"></div>
							<div class="preview">
							</div>
						</div>

						<button class="btn btn-primary btn-lg btn-block" type="button"
							id=join-btn style="float: right; margin-left: 10px;" onclick="join()">가입
							완료</button>

						<a href="/finalb/member/login"
							class="btn btn-primary btn-lg btn-block" id=back-btn
							style="float: right; display: block;">뒤로가기</a>

					</form>


				</div>
			</div>
		</div>

	</div>
	<script>
		// 상태 값
		var pwCk = false;
		var idCk = false;
		
		
		// 서브밋
		function join(){
			if($('#memberId').val()==""){
				alert("아이디는 필수 입력입니다.");
				return;
			}
			if($('#memberName').val()==""){
				alert("이름은 필수 입력입니다.");
				return;
			}
			if($('#memberPw').val()==""){
				alert("비밀번호는 필수 입력입니다.");
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
				alert("아이디 중복체크를 해주세요.");
			}else if(!pwCk){
				alert("비밀번호가 일치하지 않습니다");
				$('#memberPw').focus();
			}
			
		}

		

		//Daum 주소검색 함수
		function searchAddress() {
			new daum.Postcode({
				oncomplete : function(data) {
					// 선택한 주소 정보를 필요한 부분에 적용
					document.getElementById('address').value = data.zonecode; // 우편번호
					document.getElementById('address2').value = data.address; // 주소
				}
			}).open();
		}
		//아이디 중복 확인
		$("#idCheck").click(function() {
			var id = $('#memberId').val();
			if(id==""){
				alert("아이디를 입력하세요.");
				return;
			}
			$.ajax({
				type : 'POST',
				data : {id: id},
				url : "idCheck",
				success : function(data) {
					if (data.cnt > 0) {
						alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
						idCk = false;
					} else {
						alert("사용가능한 아이디입니다.");
						idCk = true;
					}
				},
				error : function(error) {
					alert("서버 오류. 다시 시도하세요.");
				}
			});
		});

		// 패스워드 중복검사
		$('#memberPwCheck').keyup(function() {
			var pw = $('#memberPw').val();
			var pwCheck = $('#memberPwCheck').val();

			if (pw === pwCheck) {
				//alert("비번 일치");
				$(".checkPwInfo").html("비밀번호 일치");
				$(".checkPwInfo").removeClass("text-danger");
				$(".checkPwInfo").addClass("text-success");
				pwCk = true;
			} else {
				$(".checkPwInfo").html("비밀번호 불일치");
				$(".checkPwInfo").removeClass("text-success");
				$(".checkPwInfo").addClass("text-danger");
				//alert("비번 불일치");
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
					// 이미지를 img 태그에 표시
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
				alert('유효하지 않은 이메일 주소입니다.');
				$("#email").focus();
				return false;
			}
		}
		function validatePhoneNumber() {
			var email = $("#phone").val();

			if (emailCheck(email)) {
				return true;
			} else {
				alert('유효하지 않은 전화번호입니다.');
				$("#phone").focus();
				return false;
			}
		}

	</script>
</body>
</html>