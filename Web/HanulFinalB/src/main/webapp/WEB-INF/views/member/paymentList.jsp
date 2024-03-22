<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>

<h3 class="ms-4">결제 내역</h3>
<div class="d-block">
	<div class="d-flex justify-content-center">
		<div style="border: solid 1px gray; width: 500px; min-height: 500px">
			<c:forEach items="${list}" var="vo">
				<div class="row">
					<div class="col-12 p-4">
						<div  role="button" onclick="paymentInfo('${vo.imp_uid}')" >
							<span class="fw-bold">${vo.prod_name} ${vo.count==1?'':" 외 " += (vo.count-1) += '건'}</span>
						</div>
						<div
							style="margin: 5px 0 5px 0; overflow: hidden; height: 1px; border-top: #DDD 1px solid;">
						</div>
						<div>
							<span class="fs-5">총 결제 금액 : <span class="text-danger fw-bold">${vo.amount}</span>원</span>						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<script>
	function paymentInfo(imp_uid) {
		location = 'paymentInfo?imp_uid='+imp_uid;
	}
</script>
</body>
</html>