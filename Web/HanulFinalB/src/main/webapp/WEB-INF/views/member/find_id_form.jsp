<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>아이디 찾기</title>
</head>

<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			
				<div class="w3-center w3-large w3-margin-top">
					<h3>아이디 찾기</h3>
				</div>
				<div>
					<p>
						<label>Email</label> <input class="w3-input" type="text"
							id="email" name="email" required>
					</p>
					<p class="w3-center">
						<button type="submit" id=findBtn
							class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">아이디
							찾기</button>
						<button type="button" onclick="history.go(-1);"
							class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-margin-bottom w3-round">취소</button>
					</p>
				</div>
		</div>
	</div>
	<!-- Add this script tag in the head section of your HTML -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<!-- ... (existing head content) ... -->

	<script>
		$(document).ready(function() {
			$("#findBtn").click(function() {
				var email = $("#email").val();

            // Ajax request to find the username
            $.ajax({
                type: "POST",
                url: "findId", // Your controller mapping
                data: { email: email },
                success: function(data) {
                	if(data.user_id != null){
                		alert("아이디: " + data.user_id);
            		} else {
            			alert("해당 아이디 없음");
            		}
                    // Handle success, update the UI with the found username
                    
                },
                error: function() {
                    // Handle error
                    alert("아이디를 찾을 수 없습니다.");
                }
            });
        });
    });
</script>
</body>
</html>