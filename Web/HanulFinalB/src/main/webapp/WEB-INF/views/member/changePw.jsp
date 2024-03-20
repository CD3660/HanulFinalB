<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container py-5 h-100">
		<div class="row d-flex justify-content-center align-items-center h-95">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card shadow-2-strong" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">
						<main class="myPage-content">


							<section class="myPage-main">
								<div class="myPageHeadFlex">
									<div class="myPageHead">
										<h1 class="myPage-title">비밀번호 변경</h1>

										<span class="myPage-explanation">현재 회원님의 비밀번호를 변경할 수
											있습니다.</span>
									</div>
								</div>


								<div class="changePwborder">
									<form action="changePw" method="POST" name="myPage-form"
										onsubmit="return changePwValidate()">

										<div class="myPage-row">
											<label>현재 비밀번호</label> <input type="password"
												name="currentPw" id="currentPw" maxlength="30"
												class="form-control">
										</div>

										<div class="myPage-row">
											<label>새 비밀번호</label> <input type="password" name="newPw"
												maxlength="30" class="form-control">
										</div>

										<div class="myPage-row">
											<label>새 비밀번호 확인</label> <input type="password"
												name="newPwConfirm" maxlength="30" class="form-control">
										</div>

										<button id="info-update-btn" type="submit">변경하기</button>

									</form>
								</div>

							</section>

						</main>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>