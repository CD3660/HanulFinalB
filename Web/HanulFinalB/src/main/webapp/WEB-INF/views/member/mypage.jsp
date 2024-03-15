<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-2"></div>
                <div class="col-sm-9">
                    <h2 class="text-center">회원 정보 보기</h2>
                    <table class="table table-striped">
                      <tr>
                        <td>아이디</td>
                        <td class="p-2">${loginInfo.user_id }</td>
                      </tr>
                       
                      <tr>
                        <td>이메일</td>
                      </tr>
                       
                      <tr>
                        <td>전화</td>
                      </tr>
                       <tr>
                        <td>주소</td>
                      </tr>
                       
                    
                       
                      
                       
                     
                       
                     
                       
                    <tr>
                         <td class="text-center" colspan="2">
<button onclick="location.href='MemberUpdateForm.jsp?id=<%=  %>'" class="btn btn-primary">회원수정</button>
<button onclick="location.href='MemberDeleteForm.jsp?id=<%=  %>'" class="btn btn-danger">회원삭제</button>

                          
                         </td>    
                    </tr> 
                 
                       
                    </table>
                </div>
        </div> <!-- col-sm-12 -->
    </div><!-- row -->
</div> <!-- container end-->
</body>
</html>