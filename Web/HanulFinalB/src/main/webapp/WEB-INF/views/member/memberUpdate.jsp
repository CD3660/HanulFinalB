<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
								<label for="memberName">이름</label> <input type="text"
									class="form-control" id="memberName" name="name"
									placeholder="이름을 입력해주세요" value="${empty loginInfo.name? '':loginInfo.name}" required> <span
									class="memberNamechk"></span>

							</div>
						</div>
						<div class="mb-3">
							<label for="email">이메일</label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="mysmarthome@example.com" value="${empty loginInfo.email? '':loginInfo.email}">
							<span class="emailchk"></span>
						</div>

						<div class="mb-3">
							<label for="phoneNumber">휴대폰 번호</label> <input type="text"
								class="form-control" id="phoneNumber" name="phone" value="${empty loginInfo.phone? '':loginInfo.phone}"> <span
								class="phchk"></span>
						</div>

						<div class="mb-3">
							<label for="address">우편번호
								<span class="text-muted">
									<button type="button" class="btn" id="check_btn" 
										onclick="searchAddress()">찾기</button>
							</span>
							</label> <input type="text" class="form-control" id="address"
								name="address" placeholder="찾기를 눌러 주소를 입력하세요" value="${empty loginInfo.address? '':loginInfo.address}" readonly>
						</div>

						<div class="mb-3">
							<label for="address2">주소</label>
							<input type="text" class="form-control" id="address2" value="${empty loginInfo.address2? '':loginInfo.address2}"
								name="address2" placeholder="주소를 입력해주세요." readonly>
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
	<%-- <div class="container py-5 h-100">
		<div class="row d-flex justify-content-center align-items-center h-95">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card shadow-2-strong" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">

						<h1 align="center">회원정보 수정</h1>
						<div class="">
							<form action="/member/memberUpdate" method="post">
								<table>
									<tr>
										<td>* 아이디</td>
										<td><input type="text" id="memberId" name="memberId"
											value="${loginInfo.user_id }" readonly></td>
									</tr>

									<tr>
										<td>* 이름</td>
										<td><input type="text" name="memberName"
											value="${loginInfo.name }" readonly></td>
									</tr>

									<tr>
										<td>* 이메일</td>
										<td><input type="text" name="memberEmail"
											value="${loginInfo.email }"></td>
									</tr>
									<tr>
										<td>* 전화번호</td>
										<td><input type="text" name="memberPhone"
											value="${loginInfo.phone }"></td>
									</tr>
									<tr>
										<td>* 우편번호</td>
										<td><input type="text" name="post"
											value="${loginInfo.address }"></td>
										<!-- 배열로 값을 받았다면 ${addreess[0]}으로 표기하여 출력할수 있다-->
									</tr>
									<tr>
										<td>* 주소</td>
										<td><input type="text" name="address"
											value="${loginInfo.address2 }"></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="submit"
											value="수정하기">
											<button type="button" onclick="removeMember();">
												탈퇴하기</button> <!-- //type을 button으로 꼭 적어줘야! submit이 되지 않는다!! 꼭 기억하기!
				 --></td>
									</tr>
								</table>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> --%>

	<script>
		function removeMember() {
			if (window.confirm("탈퇴하시겠습니까?")) {
				location.href = "/member/remove.kh";
			}

		}
	</script>
</body>
</html>