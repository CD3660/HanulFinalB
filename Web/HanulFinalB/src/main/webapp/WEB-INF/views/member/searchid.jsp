<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Contents -->
    <div class="container mb-auto lgacc-contents">
      <div class="lgacc-content-card">

        <div class="row justify-content-md-center ">
          <div class="col col-md-9 col-lg-7 col-xl-7">
            <div class="card lgacc-login-card contents-card-type mx-auto">
              <div class="card-header posi-sticky-head d-flex">
                <div class="d-flex align-items-center">
                  <button class="btn btn-xs btn-icon" onclick="history.back()">
                    <i class="bi bi-arrow-back text-hide"><span class="sr-only">이전 페이지로 이동</span></i>
                  </button>
                  <h3 class="m-0"><span>아이디 찾기</span></h3>
                </div>
              </div>
              <div class="card-body">
                <h5 class="card-title">아이디를 찾기 위해 회원님의 회원정보가 필요합니다.<br> 아래의 정보를 입력해 주세요.</h5>
                <!-- <p class="card-text">With supporting text below as a natural lead-in to additional content.</p> -->
                  <div class="form-group valid-mark-no text-left pt-5">
                    <label class="input-label" for="exampleIDPhone">이메일 또는 휴대폰 번호<span class="sr-only">.</span></label>
                    <input type="text" class="form-control ios-placeh01" value="" name="exampleIDPhone" id="exampleIDPhone" oninput="this.value = noEmotiKorean(this.value)" placeholder="이메일 또는 휴대폰 번호 아이디를 입력해 주세요" autocomplete="off">
                    <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
                  </div>
                
                  <div class="row pt-px34">
                    <div class="col pb-1 text-center">
                      <button type="button" id="submitButton" class="btn btn-w320 btn-primary" onclick="googleTag_searchId()">확인</button>
                    </div>
                  </div>
                <div class="row pt-3 justify-content-center">
                  <div class="col mx-auto">
                    <ul class="lgacc-tab justify-content-center">
                      
                     
                    </ul> 
                  </div>
                </div>
                </div>
            </div>
          </div>
        </div>

      </div>
    </div>
</body>
</html>