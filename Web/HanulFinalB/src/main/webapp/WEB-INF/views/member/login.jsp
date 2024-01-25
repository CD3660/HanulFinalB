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

						<div class="form-outline mb-4">
							<label class="form-label" for="typeEmailX-2">아이디</label> <input
								type="email" id="typeEmailX-2"
								class="form-control form-control-lg" />

						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="typePasswordX-2">비밀번호</label> <input
								type="password" id="typePasswordX-2"
								class="form-control form-control-lg" />

						</div>

						<!-- Checkbox -->
						<div class="form-check d-flex justify-content-start mb-4">
							<input class="form-check-input" type="checkbox" value=""
								id="form1Example3" /> <label class="form-check-label"
								for="form1Example3"> Remember password </label>
						</div>

						<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>

						<hr class="my-4">
			<!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-4">
                  회원가입
                </button>

                <!-- Register buttons -->
                <div class="text-center">
                  <p>다른 계정으로 로그인</p>
                  <button type="button" class="btn btn-link btn-floating mx-1">
                    <i class="fab fa-naver-f"></i>
                  </button>

                  <button type="button" class="btn btn-link btn-floating mx-1">
                    <i class="fab fa-kakao"></i>
                  </button>

                  
                </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>