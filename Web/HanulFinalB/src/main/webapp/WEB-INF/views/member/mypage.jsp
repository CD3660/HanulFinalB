<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
</head>
<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div class="card shadow-2-strong p-3" style="border-radius: 1rem;">
					<h4 class="mb-3">회원 관리</h4>
					<form class="update" method="post" enctype="multipart/form-data" id="join_form" action="memberUpdate">
						<div class="row">
							<div class="mb-3">
								<label for="memberName">이름</label> 
								<input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력해주세요"
									value="${empty loginInfo.name? '':loginInfo.name}" required>
								<span class="memberNamechk"></span>
							</div>
						</div>
						<div class="mb-3">
							<label for="email">이메일</label> 
							<input type="text" class="form-control" id="email" name="email" placeholder="mysmarthome@example.com"
								value="${empty loginInfo.email? '':loginInfo.email}"> 
								<span class="emailchk"></span>
						</div>
						<div class="mb-3">
							<label for="phoneNumber">휴대폰 번호</label> 
							<input type="text" class="form-control" id="phone" name="phone" value="${empty loginInfo.phone? '':loginInfo.phone}"> 
							<span class="phchk"></span>
						</div>
						<div class="mb-3">
							<label for="address">우편번호 
								<span class="text-muted">
									<button type="button" class="btn" id="check_btn" onclick="searchAddress()">찾기</button>
								</span>
							</label> 
							<input type="text" class="form-control" id="address" name="address" 
								value="${empty loginInfo.address? '':loginInfo.address}" readonly>
						</div>
						<div class="mb-3">
							<label for="address2">주소</label> 
							<input type="text" class="form-control" id="address2" value="${empty loginInfo.address2? '':loginInfo.address2}"
								name="address2" readonly>
						</div>
						<div class="mb-4" class="d-block">
							<label for="file">프로필 사진</label> 
							<label class="label me-3">
								<input type="file" name="file" />
								<span class="btn btn-primary">이미지 첨부</span>
							</label>
								<i role="button" class="${empty loginInfo.profile ?'d-none ':''}fa-solid fa-trash-can fs-8 text-danger remover"></i>
							<div class="m-2"
								style="max-width: 700px; word-break: break-word;"></div>
							<div class="preview">
								<c:if test="${!empty loginInfo.profile }">
									<img class='pre_img' src="${loginInfo.profile}"
										style='max-width: 300px;' />
								</c:if>
							</div>
						</div>
						<input type="hidden" name="maintain" value="true"/>
						<input type="hidden" name="profile" value="${vo.profile == null ? '': vo.profile}"/>
						<input type="hidden" name="user_id" value="${loginInfo.user_id}"/>
						<button class="btn btn-primary btn-lg btn-block" type="button" id=update-btn style="float: right; margin-left: 10px;"
							onclick="update()">회원 정보 수정</button>
						<button class="btn btn-danger btn-lg btn-block" type="button" id=delete-btn style="float: right; margin-left: 10px;"
							onclick="resign()">회원 탈퇴 신청</button>
						<a href="/finalb" class="btn btn-outline-primary btn-lg btn-block" id=back-btn
							style="float: right; display: block;">뒤로가기</a>

					</form>


				</div>
			</div>
		</div>

	</div>

	<script>
		function searchAddress() {
			new daum.Postcode({
				oncomplete : function(data) {
					// 선택한 주소 정보를 필요한 부분에 적용
					$("#address").val(data.zonecode);
					$("#address2").val(data.address);
				}
			}).open();
		}
		function update(){
			if($("#name").val()==''){
				alert("이름은 비워둘 수 없습니다.");
				$("#name").focus();
				return;
			}
			if(!validateEmail()){
				return;
			}
			if(!validatePhoneNumber()){
				return;	
			}
			$("form").submit();
		}
		function resign(){
			if(confirm("정말 회원탈퇴를 신청하시겠습니까?")){
				location="resign";
			}
		}
		$("[name=file]").change(function() {
			console.log(this.files[0]);
			$("[name=maintain]").val("false");
			if (!this.files[0]) {
				remove_img();
			}
			else {
				$(".remover").removeClass("d-none");
				$(".label").next("div").html(this.files[0].name);
				$(".preview").html("<img class='pre_img' style='max-width: 300px;  max - height: 300px; '/>");
				var reader = new FileReader();
				reader.onload = function(e) {
					// 이미지를 img 태그에 표시
					$(".pre_img").attr('src', e.target.result);
				}
				reader.readAsDataURL(this.files[0]);
			}
		});
		$(".remover").click(function() {
			remove_img();
			$(".remover").addClass("d-none");
		});
		function remove_img() {
			$(".label").next("div").html("");
			$(".preview").html("");
			$("[name=file]").val("");
			$("[name=maintain]").val("false");
			$("[name=profile]").val("");
		}
		function emailCheck(email_address){     
			email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
			if(!email_regex.test(email_address)){ 
				return false; 
			}else{
				return true;
			}
		}
		function phoneNumberCheck(number){
			var regExp = /^\d{3}-\d{3,4}-\d{4}$/
		    return regExp.test(number);
		}
		function validateEmail() {
			var email = $("#email").val();
			if(email==""){
				return true;
			}
			if (emailCheck(email)) {
				return true;
			} else {
				alert('유효하지 않은 이메일 주소입니다.');
				$("#email").focus();
				return false;
			}
		}
		function validatePhoneNumber() {
			var phone = $("#phone").val();
			if(phone==""){
				return true;
			}
			if (emailCheck(phone)) {
				return true;
			} else {
				alert('유효하지 않은 전화번호입니다.');
				$("#phone").focus();
				return false;
			}
		}
		
	</script>
</body>
</html>