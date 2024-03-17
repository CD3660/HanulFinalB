<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="inner_body">
    <button onclick="sidebar.open()">SideBar Open</button>
    <aside class="side_bar js-side_bar">
        <ul>
            <li>
            <li><a href="">Home</a></li>
            <li><a href="#none">회원정보</a></li>
            <li><a href="#none">비밀번호 변경</a></li>
            <li><a href="#none">회원탈퇴</a></li>
        </ul>
        <button onclick="sidebar.close()">SideBar Close</button>
    </aside>
</div>
</body>
</html>