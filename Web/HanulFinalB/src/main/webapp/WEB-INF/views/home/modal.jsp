<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<body>
	<!-- Modal -->
	<div class="modal fade modal-home" style=" margin-top: 80px;" id="modal${vo.prod_id}" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-prod">
			<div class="modal-content modal-prod">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">${vo.prod_name}</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<!-- 왼쪽 컬럼 -->
							<div class="col-md-6">
								<img src="<c:url value='/images/prod_img/${vo.prod_img}'/>"
									alt="product-item"
									class="product-image img-fluid prod${vo.prod_id}">
							</div>

							<!-- 오른쪽 컬럼 -->
							<div class="col-md-6">
								<jsp:scriptlet>pageContext.setAttribute("newline", "\n");</jsp:scriptlet>
								<c:out value="${fn:replace(vo.detail, newline, '<br>')}"
									escapeXml="false" />


							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary" id="btn${vo.prod_id}">상품
						구매</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>