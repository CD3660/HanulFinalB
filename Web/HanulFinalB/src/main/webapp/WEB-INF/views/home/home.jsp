<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>









	<section id="main">
            <div class="container-fluid" style="height: 85%;">
              <div class="row">
                <div class="col-md-12">		
		
			<div class="banner-item"
				style="background-image: url(images/banner-image1.png); background-repeat: no-repeat; background-position: right; height: 600px; position: relative; background-size: 66%">




				<div class="banner-content padding-large">
					<h1 class="display-1 text-uppercase pb-2 text-primary"
						style="color: #000063; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">My
						Smart</h1>
					<h1 class="display-1 text-uppercase pb-2"
						style="color: #00003F; font-weight: bold; margin-left: 2.7em; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">
						Home</h1>
					<p
						style="margin-left: 30px; margin-top: 30px; font-size: 1.6em; text-shadow: 5px 5px 5px #888888;">IoT
						지금 체험해보세요</p>
					<a href="<c:url value='/prod/list'/>"
						class="btn btn-medium btn-arrow position-relative mt-5"> <span
						class="text-uppercase"
						style="font-weight: bold; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">제품보기</span>
						<svg class="arrow-right position-absolute" width="18" height="20">
                          <use xlink:href="#arrow-right"></use>
                        </svg>
					</a>
				</div>
				
				
			</div>
		</div>
		</div>
		</div>
	</section>










	<section id="home-simple-icon" class="padding-large"
		style="padding-top: 0em; padding-bottom: 10em; padding-left: 6em;">
		<div class="container-fluid">
			<div class="row">
			
			
				<div class="col-lg-3 col-md-6 pb-3">
				<a href="<c:url value='/prod/list'/>">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="gift">
                  				<use xlink:href="#gift" />
                			</svg>
						</div>
						
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark">제품소개</h3>
						</div>
					</div>
				</a>
				</div>




				<div class="col-lg-3 col-md-6 pb-3">
				<a href="<c:url value='/shop/list'/>">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="shipping-fast">
                 				<use xlink:href="#shipping-fast" />
               				</svg>
						</div>
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark">제품구매</h3>
						</div>
					</div>
					</a>
				</div>
				
				
				
				
				<div class="col-lg-3 col-md-6 pb-3">
				<a id="cart">
					<div class="icon-box d-flex align-items-center">
						<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
							<svg class="shopping-cart">
                  				<use xlink:href="#shopping-cart" />
                			</svg>
						</div>
						<div class="icon-box-content ps-3">
							<h3 class="card-title text-uppercase text-dark">장바구니</h3>
						</div>
					</div>
					</a>
				</div>
				
				
				
				<c:if test="${empty loginInfo }">
					<div class="col-lg-3 col-md-6 pb-3">
						<a href="<c:url value='/member/login'/>">
							<div class="icon-box d-flex align-items-center">
							<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
								<svg class="return">
	                 				 <use xlink:href="#return" />
	               				</svg>
							</div>
							<div class="icon-box-content ps-3">
								<h3 class="card-title text-uppercase text-dark"><a href="<c:url value='/member/login'/>">로그인</h3>
							</div>
						</div>
						</a>
					</div>
				</c:if>
				<c:if test="${not empty loginInfo }">
					<div class="col-lg-3 col-md-6 pb-3">
						<a href="<c:url value='/member/login'/>">
							<div class="icon-box d-flex align-items-center">
							<div class="icon-box-icon pt-3 pe-3 pb-3 ps-3">
								<svg class="return">
	                 				 <use xlink:href="#return" />
	               				</svg>
							</div>
							<div class="icon-box-content ps-3">
								<h3 class="card-title text-uppercase text-dark"><a href="<c:url value='/member/mypage'/>">마이페이지</h3>
							</div>
						</div>
						</a>
					</div>
				</c:if>
				
				
							
				
			</div>
		</div>
	</section>

































	<section id="NewProduct">
		<div class="container-fluid" style="margin-top: 5em;">
			<div class="row align-items-center justify-content-between g-5">
				<div class="col-lg-5">
					<div class="image-holder mb-4 justify-content-between">
					<h2 class="display-2 text-dark text-uppercase" style="font-size: 2.7em; margin-bottom: 30px;">
					<i class="fa-solid fa-rocket"></i>   신제품</h2>
						<img src="${list.get(0).prod_img }" alt="CCTV"
							class="img-fluid position-relative" style="margin-left: 40px;">
					</div>
				</div>
				<div class="col-lg-7">
					<div class="detail p-5">
						<div class="display-header">
							<h2 class="display-2 text-uppercase text-dark pb-2">${list.get(0).prod_name }</h2>
							<p class="pb-3">${list.get(0).detail }</p>
							
							
							<a class="btn btn-medium btn-arrow outline-dark position-relative mt-3" id="cctv">
								<span class="text-uppercase">자세히 보기</span> <svg
									class="arrow-right position-absolute" width="18" height="20">
                    <use xlink:href="#arrow-right"></use>
                  </svg>
							</a>
							
							
							
			
						
							
							
							
							
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	



	<section id="home-comment" class="position-relative">
		<div class="container" style="padding-top: 7em; padding-bottom: 9em;">
			<div class="row">
				<div class="review-content position-relative">
				
				
				
				
					
					
					
				
						<div class="quotation text-center">
							<svg class="quote">
                  <use xlink:href="#quote" />
                </svg>
						</div>

					
							<div
								class="text-center d-flex justify-content-center">
								<div class="review-item col-md-10">
									<i class="icon icon-review"></i>
									<blockquote class="fs-4">안전하고 신뢰성 있는 보안 솔루션으로 데이터 보호 및 암호화, 내구성 및 방수 기능을 갖추어 고객의 안전을 최우선에 두고 있습니다. 전문가 그룹의 기술 지원과 서비스를 통해 CCTV 설치, 유지보수, 사용자 교육 등 다양한 영역에서 고객에게 최상의 서비스를 제공합니다.</blockquote>
									<div class="author-detail">
										<div class="name text-primary text-uppercase pt-2">Hanul
											Team B</div>
										
						<div class="quotation text-center">
											<svg class="quote">
                  <use xlink:href="#quote"></use>
                </svg></div>
             
									</div>
								</div>
							</div>
				</div>
			</div>
		</div>
	</section>



	<section id="BestSeller">
		<div class="container-fluid" style="margin-bottom: 8em;">
		
			<div class="row">
				<div class="col-md-12">
					<div
						class="display-header d-flex flex-wrap justify-content-between pb-3"
						style="margin-top: 10em;">
						<h2 class="display-2 text-dark text-uppercase" style="font-size: 2.7em;">
						<i class="fa-solid fa-crown"></i>   베스트 셀러</h2>
						<a href="<c:url value='/shop/list'/>"
							class="btn btn-medium btn-arrow btn-normal position-relative">
							<span class="text-uppercase">모든 제품 보기</span> <svg
								class="arrow-right position-absolute" width="18" height="20">
                  <use xlink:href="#arrow-right"></use>
                </svg>
						</a>
					</div>
				</div>
			</div>
			
			
			
			
	

			
			<div class="row g-3 post-grid">
			
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item" id=dust>
						<div class="card border-0 bg-transparent">
							<div class="card-image">
								<img src="${list.get(3).prod_img }" style="height: 420px; width: 420px;" alt="dust"
									class="post-image img-fluid">
							</div>
						</div>
						<div class="card-body p-0 mt-4">
							<h3 class="card-title text-uppercase">
								<a href="">${list.get(3).prod_name }</a>
							</h3>
							<p id=3>${list.get(3).detail }</p>
							<a href=""
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
															
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item" id=fire>
						<div class="card border-0 bg-transparent">
							<div class="card-image">
								<img src="${list.get(1).prod_img }" style="height: 420px; width: 420px;" alt="fire">
							</div>
						</div>
						<div class="card-body p-0 mt-4" >
							<h3 class="card-title text-uppercase">
								<a href="">${list.get(1).prod_name }</a>
							</h3>
							<p id=1>${list.get(1).detail }</p>
							<a href=""
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
															
						</div>
					</div>
				</div>
				
				
				<div class="col-lg-4 col-md-6 col-sm-12 mb-5">
					<div class="card-item" id=gas>
						<div class="card border-0 bg-transparent">
							<div class="card-image">
								<img src="${list.get(2).prod_img }" style="height: 420px; width: 420px;" alt="gas">
							</div>
						</div>
						<div class="card-body p-0 mt-4" >
							<h3 class="card-title text-uppercase">
								<a href="">${list.get(2).prod_name }</a>
							</h3>
							<p id=2>${list.get(2).detail}</p>
							<a href=""
								class="btn btn-normal text-uppercase p-0"><em>자세히 보기</em></a>
														
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</section>
	<hr>





<c:forEach items="${list}" var="vo">

				<!-- 모달 소환  -->
							<%@ include file="/WEB-INF/views/home/modal.jsp" %>

</c:forEach>




		<script>
		
			$('#cctv').click(function(e) {
				console.log("cctv클릭");
				e.preventDefault();
			
				$('#modal14').modal("show");
		    });

			$('#btn14').click(function(e) {
				window.open("shop/info?prod_id=14", '_blank');
			});
			
			
			
			
			
			$('#dust').click(function(e) {
				e.preventDefault();
			
				$('#modal18').modal("show");
		    });

			$('#btn18').click(function(e) {
				window.open("shop/info?prod_id=18", '_blank');
			});
			
			
			
			
			
			
			$('#fire').click(function(e) {
				e.preventDefault();
			
				$('#modal15').modal("show");
		    });

			$('#btn15').click(function(e) {
				window.open("shop/info?prod_id=15", '_blank');
			});
			
			
			
			
			
			
			$('#gas').click(function(e) {
				e.preventDefault();
			
				$('#modal16').modal("show");
		    });

			$('#btn16').click(function(e) {
				window.open("shop/info?prod_id=16", '_blank');
			});
			
			
			
			
			
			
			
			$('a#cart').click(function(e) {
			
				e.preventDefault();
				if ( ${empty loginInfo} ) {
					alert('로그인하세요')
				}else{
					location='shop/cart'
				}
			})
			
			
			
		
			
		function createPreview(text, maxLength) {
			
        if (text.length > maxLength) {
            return text.substring(0, maxLength) + "...";
            
        } else {
            return text;
        }
        
    	};

		
    	
    	
    	$(function(){
    		   $(".card-body p").each(function(){
    		      $(this).text( createPreview( $(this).text(), 50 ) );
    		   });
    		});
		
		
		
		</script>







</html>