<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/member/login.css">
</head>
<body>
 <body class="text-center">
    
    <!--  html ��ü ������ �����ϴ� container -->
    <div id="container">
      <!--  login �� ������ : loginBox -->
      <div id="loginBox">
      
        <!-- �α��� ������ Ÿ��Ʋ -->
        <div id="loginBoxTitle">CodeZone Login</div>
        <!-- ���̵�, ���, ��ư �ڽ� -->
        <div id="inputBox">
          <div class="input-form-box"><span>���̵� </span><input type="text" name="uid" class="form-control"></div>
          <div class="input-form-box"><span>��й�ȣ </span><input type="password" name="upw" class="form-control"></div>
          <div class="button-login-box" >
            <button type="button" class="btn btn-primary btn-xs" style="width:100%">�α���</button>
          </div>
        </div>
        
      </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>