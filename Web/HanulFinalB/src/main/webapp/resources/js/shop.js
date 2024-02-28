/**
 * 
 */
//대표 이미지 첨부 후 이름 표시, 미리보기 표시
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
//상품명 글자수 제한하기
$("[name=prod_name]").change(function() {
	tag = $("[name=prod_name]");
	var str = tag.val();
	if (str.length > 100) {
		alert("100글자 이내로 작성하세요");
		str = str.substr(0, 100)
		tag.val(str)
	}
	tag.next("span").text(str.length);
});
//상품명 글자수 제한하기
$("[name=prod_name]").keyup(function() {
	tag = $("[name=prod_name]");
	var str = tag.val();
	if (str.length > 100) {
		alert("100글자 이내로 작성하세요");
		str = str.substr(0, 100)
		tag.val(str)
	}
	tag.next("span").text(str.length);
});

//설명 글자수 제한하기
$("[name=detail]").change(function() {
	tag = $("[name=detail]");
	var str = tag.val();
	if (str.length > 500) {
		alert("500글자 이내로 작성하세요");
		str = str.substr(0, 500)
		tag.val(str)
	}
	tag.next("span").text(str.length);
});
//설명 글자수 제한하기
$("[name=detail]").keyup(function() {
	tag = $("[name=detail]");
	var str = tag.val();
	if (str.length > 500) {
		alert("500글자 이내로 작성하세요");
		str = str.substr(0, 500)
		tag.val(str)
	}
	tag.next("span").text(str.length);
});

$("#btn-insert").click(function() {
	if ($("[name=prod_name]").val().length > 100) {alert("상품명은 100글자 이내여야 합니다."); return;}
	if (isNaN($("[name=price]").val())) {alert("판매가가 숫자가 아닙니다."); return;}
	if ($("[name=price]").val().length > 9) {alert("판매가 입력 범위 초과"); return;}
	if (isNaN($("[name=stock]").val())) {alert("재고가 숫자가 아닙니다."); return;}
	if ($("[name=stock]").val().length > 9) {alert("재고 입력 범위 초과"); return;}
	if ($("[name=prod_name]").val().length > 100) {alert("상품명은 100글자 이내여야 합니다."); return;}
	$("form").submit();
});

$("#btn-update").click(function() {
	if ($("[name=prod_name]").val().length > 100) {alert("상품명은 100글자 이내여야 합니다."); return;}
	if (isNaN($("[name=price]").val())) {alert("판매가가 숫자가 아닙니다."); return;}
	if ($("[name=price]").val().length > 9) {alert("판매가 입력 범위 초과"); return;}
	if (isNaN($("[name=stock]").val())) {alert("재고가 숫자가 아닙니다."); return;}
	if ($("[name=stock]").val().length > 9) {alert("재고 입력 범위 초과"); return;}
	if ($("[name=prod_name]").val().length > 100) {alert("상품명은 100글자 이내여야 합니다."); return;}
	$("form").submit();
});

$(".remover").click(function() {
	remove_img();
	$(".remover").addClass("d-none");
});

function remove_img(){
	$(".label").next("div").html("");
	$(".preview").html("");
	$("[name=file]").val("");
	$("[name=maintain]").val("false");
}