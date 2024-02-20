// 입력여부확인
function emptyCheck() {
	var ok = true;
	$(".check-empty").each(function() {
		if($(this).val() == "") {
			alert( $(this).attr("title") + "입력하세요!");
			$(this).focus();
			ok= false;
			return ok;
			
		}
		
	})
	return ok;
}






function multipleFileUpload() {
	//FileList 객체의 files의 파일정보를 input file태그에 넣기
	var transfer = new DataTransfer();
	var files = fileList.getFile();
	if(files.length > 0 ) {
		for(i=0; i<files.length; i++) {
			
			
		}
	}
}






//파일관련처리
function FileList() {
	this.files= [];
	
	
	
	
	
}

