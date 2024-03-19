<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/member/sideMenu.jsp" />
	<div class="container py-5 h-100">
		<div class="row d-flex justify-content-center align-items-center h-95">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card shadow-2-strong" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">
						<main class="myPage-content">


							<section class="myPage-main">
								<div class="myPageHeadFlex">
									<div class="myPageHead">
										<h1 class="myPage-title">회원 탈퇴</h1>

										<span class="myPage-explanation">현재 비밀번호가 일치하는 경우 회원
											탈퇴할 수 있습니다.</span>
									</div>
								</div>


								<div class="secessionborder">
									<form action="secession" method="POST" name="myPage-form"
										onsubmit="return secessionValidate()">

										<div class="myPage-row">
											<label id="secessionPw">비밀번호</label> <input type="password"
												name="memberPw" id="memberPw" maxlength="30"
												class="form-control">
										</div>
										<div class="campwithus">
											※소셜회원인 경우 '<span id="campwithus">mysmarthome</span>'를 입력하세요.
										</div>


										<div class="myPage-row info-title">
											<label>회원 탈퇴 약관</label>
										</div>

										<div class="secessionContent">
											<h3>제1조(목적)</h3>
											이 약관은 한울이 운영하는 MySmartHome(이하 "MSH”라 한다)에서 제공하는 인터넷 관련 서비스(이하
											“서비스”라 한다)를 이용함에 있어 사이버 MSH과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로
											합니다. <br> ※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한
											이 약관을 준용합니다.」<br>

											<h3>제7조(회원 탈퇴 및 자격 상실 등)</h3>

											① 회원은 “MSH”에 언제든지 탈퇴를 요청할 수 있으며 “MSH”은 즉시 회원탈퇴를 처리합니다. <br>

											② 회원이 다음 각 호의 사유에 해당하는 경우, “MSH”은 회원자격을 제한 및 정지시킬 수 있습니다. <br>
											<br> 1. 가입 신청 시에 허위 내용을 등록한 경우 <br> 2. “MSH”을 이용하여
											구입한 재화 등의 대금, 기타 “MSH”이용에 관련하여 회원이 부담하는 채무를 기일에 지급하지 않는 경우 <br>
											3. 다른 사람의 “MSH” 이용을 방해하거나 그 정보를 도용하는 등 전자상거래 질서를 위협하는 경우 <br>
											4. “MSH”을 이용하여 법령 또는 이 약관이 금지하거나 공서양속에 반하는 행위를 하는 경우<br>
											<br> ③ “MSH”이 회원 자격을 제한․정지 시킨 후, 동일한 행위가 2회 이상 반복되거나 30일
											이내에 그 사유가 시정되지 아니하는 경우 “MSH”은 회원자격을 상실시킬 수 있습니다.<br> ④
											“MSH”이 회원자격을 상실시키는 경우에는 회원등록을 말소합니다. 이 경우 회원에게 이를 통지하고, 회원등록
											말소 전에 최소한 30일 이상의 기간을 정하여 소명할 기회를 부여합니다.<br>

										</div>



										<div id="checkBoxdiv">
											<input type="checkbox" name="agree" id="agree"> <label
												for="agree">
												<div>위 약관에 동의합니다.</div>
											</label>
										</div>


										<button id="info-update-btn">탈퇴</button>

									</form>
								</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>