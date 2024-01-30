<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>

<link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
</head>
<body>
	<div >
	<form action="doJoin" method="POST" class="joinForm"
		onsubmit="DoJoinForm__submit(this); return false;">

		<h2>ȸ������</h2>
		<div class="textForm">
			<input name="loginId" type="text" class="id" placeholder="���̵�">
			</input>
		</div>
		<div class="textForm">
			<input name="loginPw" type="password" class="pw" placeholder="��й�ȣ">
		</div>
		<div class="textForm">
			<input name="loginPwConfirm" type="password" class="pw"
				placeholder="��й�ȣ Ȯ��">
		</div>
		<div class="textForm">
			<input name="name" type="text" class="name" placeholder="�̸�">
		</div>
		<div class="textForm">
			<input name="email" type="text" class="email" placeholder="�̸���">
		</div>
		<input type="text" id="sample4_postcode" placeholder="�����ȣ"> <input
			type="button" onclick="sample4_execDaumPostcode()" value="�����ȣ ã��"><br>
		<input type="text" id="sample4_roadAddress" placeholder="���θ��ּ�">
		<input type="text" id="sample4_jibunAddress" placeholder="�����ּ�">
		<span id="guide" style="color: #999; display: none"></span> <input
			type="text" id="sample4_detailAddress" placeholder="���ּ�"> <input
			type="text" id="sample4_extraAddress" placeholder="�����׸�">

		<script
			src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

                // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� ǥ���Ѵ�.
                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
                var roadAddr = data.roadAddress; // ���θ� �ּ� ����
                var extraRoadAddr = ''; // ���� �׸� ����

                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
                if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // �����׸� ���ڿ��� ���� ��� �ش� �ʵ忡 �ִ´�.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
		<div class="textForm">
			<input name="cellphoneNo" type="text" class="cellphoneNo"
				placeholder="��ȭ��ȣ">
		</div>
		<input type="submit" class="btn" value="J O I N" />
	
	</form>
</div>
</body>
</html>