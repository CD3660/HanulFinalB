<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>

<link rel="stylesheet" href="<c:url value='/css/member/login.css'/>">
</head>
<body>

	<div class="container py-5 h-100">
		<div class="row d-flex justify-content-center align-items-center h-95">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card shadow-2-strong" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">

						<h3 class="mb-5">로그인</h3>
						<form method="post" action="loginPOST">
						<div class="form-outline mb-4">
							<label class="form-label" for="typeEmailX-2">아이디</label> <input
								type="text" id="typeEmailX-2" name="user_id"
								class="form-control form-control-lg" />

						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="typePasswordX-2">비밀번호</label> <input
								type="password" id="typePasswordX-2" name="user_pw"
								class="form-control form-control-lg" />

						</div>

						<!-- Checkbox -->
						<div class="form-check d-flex justify-content-start mb-4">
							<input class="form-check-input" type="checkbox" value=""
								id="form1Example3" /> <label class="form-check-label"
								for="form1Example3"> Remember password </label>
						</div>

							<!-- 폼 내용 유지 -->
						<button class="login_button">로그인</button>
						</form>
						<hr class="my-4">

						<div class="row pt-3 justify-content-center">
							<div class="col pb-4 mx-auto">
								<ul class="lgacc-tab">
									<li class="tab-item"><a class="tab-link"
										id="btn-search-id" href="http://localhost:8080/finalb/member/userSearch">아이디 찾기</a></li>
									<li class="tab-item"><a id="reset-password"
										class="tab-link" href="javascript:void(0)">비밀번호 재설정</a>
								</ul>
								<ul class="lgacc-tab justify-content-center">
									<li class="tab-item"><a id="btn-register" class="tab-link"
										href="http://localhost:8080/finalb/member/join"> 회원가입</a></li>

								</ul>
							</div>
						</div>

						<!-- Register buttons -->
						<div class="text-center">
							<p>다른 계정으로 로그인</p>
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
			if(${!empty fail}) alert("아이디나 비밀번호가 일치하지 않습니다.");
		})
	
		$("#naver, #kakao").click(function() {
			location = $(this).attr("id") + "Login";
		})	
		
		/* 로그인 버튼 클릭 메서드 */
    $(".login_button").click(function(){
        
        /*alert("로그인 버튼 작동");
        /* 로그인 메서드 서버 요청 */
        $("#login_form").submit();
    });
 
		
	</script>

</body>
</html>