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
	if ($("[name=prod_name]").val().length > 100) { alert("상품명은 100글자 이내여야 합니다."); return; }
	if (isNaN($("[name=price]").val())) { alert("판매가가 숫자가 아닙니다."); return; }
	if ($("[name=price]").val().length > 9) { alert("판매가 입력 범위 초과"); return; }
	if (isNaN($("[name=stock]").val())) { alert("재고가 숫자가 아닙니다."); return; }
	if ($("[name=stock]").val().length > 9) { alert("재고 입력 범위 초과"); return; }
	if ($("[name=prod_name]").val().length > 100) { alert("상품명은 100글자 이내여야 합니다."); return; }
	$("form").submit();
});

$("#btn-update").click(function() {
	if ($("[name=prod_name]").val().length > 100) { alert("상품명은 100글자 이내여야 합니다."); return; }
	if (isNaN($("[name=price]").val())) { alert("판매가가 숫자가 아닙니다."); return; }
	if ($("[name=price]").val().length > 9) { alert("판매가 입력 범위 초과"); return; }
	if (isNaN($("[name=stock]").val())) { alert("재고가 숫자가 아닙니다."); return; }
	if ($("[name=stock]").val().length > 9) { alert("재고 입력 범위 초과"); return; }
	if ($("[name=prod_name]").val().length > 100) { alert("상품명은 100글자 이내여야 합니다."); return; }
	$("form").submit();
});

$(".remover").click(function() {
	remove_img();
	$(".remover").addClass("d-none");
});

function remove_img() {
	$(".label").next("div").html("");
	$(".preview").html("");
	$("[name=file]").val("");
	$("[name=maintain]").val("false");
}

//상품 개수 직접 입력에 대한 제어
$("[name=ea]").change(function() {
	tag = $("[name=ea]");
	if (isNaN(tag.val())) {
		alert("구매수량은 숫자만 가능합니다");
		tag.val("1");
		return;
	}
	if (Number(tag.val()) > 999) {
		alert("1000개 이하의 주문만 가능합니다.");
		tag.val("1");
		return;
	}
	if (Number(tag.val()) < 1) {
		alert("1개 이상만 주문 가능합니다.");
		tag.val("1");
		return;
	}
	tag.val(Number(tag.val()));
});

//버튼으로 개수 조절하기 제어
$(".val_up").click(function() {
	tag = $("[name=ea]");
	tag.val(Number(tag.val()) + 1);
});
$(".val_down").click(function() {
	tag = $("[name=ea]");
	if(Number(tag.val())<2) return;
	tag.val(Number(tag.val()) - 1);
});

//장바구니에 담기
$(".to_cart").click(function() {
	ea = $("[name=ea]").val();
	prod_id = $("#prod_id").val();
	location="to_cart?prod_id="+prod_id+"&ea="+ea;
	
});
//장바구니에 담기
$(".order").click(function() {
	ea = $("[name=ea]").val();
	prod_id = $("#prod_id").val();
	location="order?prod_id="+prod_id+"&ea="+ea;
	
});

