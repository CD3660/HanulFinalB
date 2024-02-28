<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
					<form method="post" action="join" name="userInfo" id="join_form"
						onsubmit="return checkValue()">
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
								<label for="memberPw">비밀번호</label> <input type="text"
									class="form-control" id="memberPw" name="user_pw"
									placeholder="비밀번호를 입력해주세요" value="" required> <span
									class="pwchk1"></span>
							</div>

							<div class="col-md-6 mb-3">
								<label for="memberPwCheck">비밀번호 확인</label> <input type="text"
									class="form-control" id="memberPwCheck" name="user_pw"
									placeholder="비밀번호를 다시 한번 입력해주세요" value="" required><span
									class="pwchk"></span>
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



						<div class="mb-4"></div>

						<button class="btn btn-primary btn-lg btn-block" type="submit"
						
							id = join-btn style="float: right; margin-left: 10px;">가입 완료</button>

						<a href="http://localhost:8080/finalb/member/login"
							class="btn btn-primary btn-lg btn-block"
							id = back-btn
							style="float: right; display: block;">뒤로가기</a>

					</form>


				</div>
			</div>
		</div>

	</div>
	<script src="<c:url value='/js/member.js'/>"></script>
	<script>
		$(document).ready(function() {
			//회원가입 버튼(회원가입 기능 작동)
			$("#join-btn").click(function() {
				$("#join_form").attr("action", "/member/join");
				$("#join_form").submit();
			});
		});

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
		
		
	</script>
</body>
</html>