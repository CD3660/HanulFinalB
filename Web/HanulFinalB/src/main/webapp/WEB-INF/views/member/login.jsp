<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/member/login.css'/>">
</head>
<body>
<body class="text-center">

	<!--  html 전체 영역을 지정하는 container -->
	<div id="container">
		<!--  login 폼 영역을 : loginBox -->
		<div id="loginBox">

			<!-- 로그인 페이지 타이틀 -->
			<div id="loginBoxTitle">MySmartHome</div>
			<!-- 아이디, 비번, 버튼 박스 -->
			<div id="inputBox">
				<div class="input-form-box">
					<span>아이디 </span><input type="text" name="uid" class="form-control">
				</div>
				<div class="input-form-box">
					<span>비밀번호 </span><input type="password" name="upw"
						class="form-control">
				</div>
				<div class="button-login-box">
					<button type="button" class="btn btn-primary btn-xs"
						style="width: 100%">로그인</button>
				<div class="button-login-box">
					<button type="button" class="btn btn-primary btn-xs"
						style="width: 100%">네이버</button>
				<div class="button-login-box">
					<button type="button" class="btn btn-primary btn-xs"
						style="width: 100%">카카오톡</button>
						
				</div>
			</div>

		</div>
	</div>

</body>
</html>