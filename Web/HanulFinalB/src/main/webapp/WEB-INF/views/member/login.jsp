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

	<!--  html ��ü ������ �����ϴ� container -->
	<div id="container">
		<!--  login �� ������ : loginBox -->
		<div id="loginBox">

			<!-- �α��� ������ Ÿ��Ʋ -->
			<div id="loginBoxTitle">MySmartHome</div>
			<!-- ���̵�, ���, ��ư �ڽ� -->
			<div id="inputBox">
				<div class="input-form-box">
					<span>���̵� </span><input type="text" name="uid" class="form-control">
				</div>
				<div class="input-form-box">
					<span>��й�ȣ </span><input type="password" name="upw"
						class="form-control">
				</div>
				<div class="button-login-box">
					<button type="button" class="btn btn-primary btn-xs"
						style="width: 100%">�α���</button>
				<div class="button-login-box">
					<button type="button" class="btn btn-primary btn-xs"
						style="width: 100%">���̹�</button>
				<div class="button-login-box">
					<button type="button" class="btn btn-primary btn-xs"
						style="width: 100%">īī����</button>
						
				</div>
			</div>

		</div>
	</div>

</body>
</html>