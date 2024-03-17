<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">회원정보수정</h1>
	<div class="">
		<form action="/member/mypage" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" id="memberId" name="memberId" value="${member.user_id }" readonly>
					</td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="memberPw" value=""></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="memberName" value="${member.name }" readonly></td>
				</tr>

				<tr>
					<td>* 이메일</td>
					<td><input type="text" name="memberEmail" value="${member.email }"></td>
				</tr>
				<tr>
					<td>* 전화번호</td>
					<td><input type="text" name="memberPhone" value="${member.phone }"></td>
				</tr>
				<tr>
					<td>* 우편번호</td>
					<td><input type="text" name="post" value="${member.address }" ></td>
					<!-- 배열로 값을 받았다면 ${addreess[0]}으로 표기하여 출력할수 있다-->
				</tr>
				<tr>
					<td>* 주소</td>
					<td><input type="text" name="address" value="${member.address2 }"></td>
				</tr>
				<tr>
				<td colspan="2" align="center">
			<input type="submit" value="수정하기">
			<button type="button" onclick="removeMember();"> 탈퇴하기 </button>
			<!-- //type을 button으로 꼭 적어줘야! submit이 되지 않는다!! 꼭 기억하기!
				 -->
				</td>
				</tr>
			</table>
			
		</form>
	</div>

<script>
function removeMember() {
	if(window.confirm("탈퇴하시겠습니까?")){
	location.href="/member/remove.kh";
	}
	
}
</script>
</body>
</html>