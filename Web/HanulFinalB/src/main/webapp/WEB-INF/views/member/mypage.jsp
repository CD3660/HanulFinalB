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

						<h1 align="center">회원정보</h1>
						<div class="">
						<form name="form1" method="post">
							
								<table>
									<tr>
										<td>* 아이디</td>
										<td><input type="text" id="user_id" name="user_id"
											value="${loginInfo.user_id }" readonly></td>
									</tr>

									<tr>
										<td>* 이름</td>
										<td><input type="text" name="name"
											value="${loginInfo.name }" readonly></td>
									</tr>

									<tr>
										<td>* 이메일</td>
										<td><input type="text" name="email"
											value="${loginInfo.email }"></td>
									</tr>
									<tr>
										<td>* 전화번호</td>
										<td><input type="text" name="phone"
											value="${loginInfo.phone }"></td>
									</tr>
									<tr>
										<td>* 우편번호</td>
										<td><input type="text" name="address"
											value="${loginInfo.address }"></td>
										<!-- 배열로 값을 받았다면 ${addreess[0]}으로 표기하여 출력할수 있다-->
									</tr>
									<tr>
										<td>* 주소</td>
										<td><input type="text" name="address2"
											value="${loginInfo.address2 }"></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
										<input type="button" value="수정하기" id ="btnUpdate">
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
	</div>

	<script>
	$(document).ready(function(){
        $("#btnUpdate").click(function(){
        	var path = "memberUpdate";
            document.form1.action = path;
            document.form1.submit();
        });
    });
		function removeMember() {
			if (window.confirm("탈퇴하시겠습니까?")) {
				location.href = "/member/remove.kh";
			}

		}
	</script>
</body>
</html>