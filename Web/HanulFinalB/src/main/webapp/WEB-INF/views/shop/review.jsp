<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
	.star-rating {
  display: flex;
  flex-direction: row-reverse;
  font-size: 2.25rem;
  line-height: 2.5rem;
  justify-content: space-around;
  padding: 0 0.2em;
  text-align: center;
  width: 5em;
}
 
.star-rating input {
  display: none;
}
 
.star-rating label {
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 2.3px;
  -webkit-text-stroke-color: #2b2a29;
  cursor: pointer;
}
 
.star-rating :checked ~ label {
  -webkit-text-fill-color: gold;
}
 
.star-rating label:hover,
.star-rating label:hover ~ label {
  -webkit-text-fill-color: #fff58c;
}
</style>
<h3 class="m-4">리뷰 작성하기</h3>
<div class="mb-3">
	<div class="star-rating space-x-4 mx-auto">
	    <input type="radio" id="5-stars" name="rating" value="5" onclick="setRating(5)"/>
	    <label for="5-stars" class="star pr-4">★</label>
	    <input type="radio" id="4-stars" name="rating" value="4" onclick="setRating(4)"/>
	    <label for="4-stars" class="star">★</label>
	    <input type="radio" id="3-stars" name="rating" value="3" onclick="setRating(3)"/>
	    <label for="3-stars" class="star">★</label>
	    <input type="radio" id="2-stars" name="rating" value="2" onclick="setRating(2)"/>
	    <label for="2-stars" class="star">★</label>
	    <input type="radio" id="1-star" name="rating" value="1" onclick="setRating(1)" />
	    <label for="1-star" class="star">★</label>
	    
	</div>
</div>
<form action="insert_review" method="post">
	<input type="hidden" name="rate"/>
	<input type="hidden" name="prod_id" value="${prod_id }"/>
	<input type="hidden" name="user_id" value="${user_id }"/>
	<div class="m-4">
		<label for="review_content" class="form-label">내용</label>
		<textarea class="form-control" id="review_content" name="content" rows="3" style="resize: none;"></textarea>
	</div>
	<div class="row justify-content-end">
		<div class="col-auto">
			<!-- 여기에 내용을 넣으세요 -->
			<input class="btn btn-primary me-4" id="btn-insert" type="button" value="리뷰 등록"/>
		</div>
	</div>
</form>
<script>
	function setRating(rating) {
	    // 여기서 선택한 별점 값(rating)을 사용하여 원하는 동작을 수행합니다.
	    console.log("별점: " + rating);
	    // 별점을 서버로 전송하거나 다른 작업을 수행할 수 있습니다.
	    $("[name=rate]").val(rating);
	}
	$("#btn-insert").click(function() {
		var rate = $("[name=rate]").val();
		var content = $("#review_content").val();
		if(rate=="") {
			alert("별점을 등록하세요");
			return;
		} else if(content==""){
			alert("내용을 작성하세요");
			return;
		} else if(content.length>200){
			alert("200글자 이내로 작성하세요.");
			$("#review_content").val(content.substring(0, 200));
			return;
		} else {
			$("form").submit();
		}
	})
</script>