<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<iframe src="http://192.168.0.30:8000/cctv" width="800" height="600"
		frameborder="0" scrolling="no"></iframe>
	<button id="led_on">조명 켜기</button>
	<button id="led_off">조명 끄기</button>
	<script>
		$("#led_on").click(function() {
			$.ajax({
				url : '/finalb/sensor/led',
				method : 'GET',
				data : {
					led_mode : "on"
				}
			}).done(function(resp) {
				console.log(resp)
			})
		});
		$("#led_off").click(function() {
			$.ajax({
				url : '/finalb/sensor/led',
				method : 'GET',
				data : {
					led_mode : "off"
				}
			}).done(function(resp) {
				console.log(resp)
			})
		});
	</script>
</body>
</html>