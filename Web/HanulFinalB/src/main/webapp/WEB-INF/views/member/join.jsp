<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
					<form method="post" id="join_form">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberId">아이디</label> <input type="text"
									class="form-control" id="memberId" name="user_id"
									maxlength="50" placeholder="아이디를 입력해주세요" required> <span
									class="idchk"></span>
							</div>
							<div class="col-md-6 mb-3">
								<label for="memberName">이름</label> <input type="text"
									class="form-control" id="memberName" name="name"
									placeholder="이름을 입력해주세요" value="" required> <span
									class="memberNamechk"></span>

							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="memberPw">비밀번호</label> <input type="password"
									class="form-control" id="memberPw" name="user_pw"
									placeholder="비밀번호를 입력해주세요" value="" required> <span
									class="pwchk1"></span>
							</div>

							<div class="col-md-6 mb-3">
								<label for="memberPwCheck">비밀번호 확인</label> <input
									type="password" class="form-control" id="memberPwCheck"
									name="user_pw_chk" placeholder="비밀번호를 다시 한번 입력해주세요" value=""
									required><span class="pwchk"></span>
							</div>

						</div>
						<div class="mb-3">
							<label for="email">이메일</label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="mysmarthome@example.com" required="required">
							<span class="emailchk"></span>
						</div>

						<div class="mb-3">
							<label for="phoneNumber">휴대폰 번호</label> <input type="text"
								class="form-control" id="phoneNumber" name="phone" placeholder="휴대폰 번호를 입력해주세요"> <span
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



						<div class="mb-4"></div>

						<button class="btn btn-primary btn-lg btn-block" type="button"
							id=join-btn style="float: right; margin-left: 10px;" onclick="join()">가입
							완료</button>

						<a href="http://localhost:8080/finalb/member/login"
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
			if(pwCk && idCk){
				$("#join_form").attr("action", "/finalb/member/joinAction");
				$("#join_form").submit();
			}else if(!idCk){
				alert("중복된 아이디입니다.");
			}else if(!pwCk){
				alert("비밀번호가 일치하지 않습니다");
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

		// 아이디 중복확인
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
						alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
						idCk = false;
					} else {
						alert("사용가능한 아이디입니다.");
						idCk = true;
					}
				},
				error : function(error) {

					alert("아이디를 입력해주세요");
				}
			});

		});

		// 패스워드 중복검사
		$('#memberPwCheck').blur(function() {
			var pw = $('#memberPw').val();
			var pwCheck = $('#memberPwCheck').val();

			if (pw === pwCheck) {
				alert("비번 일치");
				pwCk = true;

			} else {
				alert("비번 불일치");
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