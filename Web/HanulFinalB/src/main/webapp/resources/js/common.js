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
	if( files.length > 0 ){
		for(i=0; i<files.length; i++){
			if( fileList.info.upload[i] ) transfer.items.add( files[i] ); //upload 대상인 파일만 추가
		}
	}
	console.log(' transfer.files> ', transfer.files )
	$("#file-multiple").prop("files", transfer.files )
}






//파일관련처리
function FileList() {
	this.files= [];
	this.info ={ upload:[], id:[], remove:[] };
	
	
	
	
	this.setFile = function( file, id ) {
		//id 값이 있으면 이미 업로드되어 있는 파일이므로 업로드하지 않는다.
		this.info.upload.push( typeof id == "undefined" );
		
		//id 값이 있으면 이미 업로드되어 있는 파일의 id를 담기
		if(typeof id != "undefined" ) this.info.id.push(id);
		
		this.files.push ( file );
	}
	
	
	
	
	
	
	
	
	this.getFile = function() {
		return this.files;
	}
	
	
		
		
		
		
		
		
	this.removeFile = function( i ) {
	this.files.splice(i,1);
	this.info.upload.splice(i,1);	
		
		if( typeof this.info.id[i] != "undefined" ) {
			this.info.remove.push( this.info.id[i]); //remove에 넣기
			this.info.id.splice(i,1); //id에서 삭제하기
			
		}
	}
	
	
	
	
	
	
	
	this.showFile = function(){
		var tag = "";
		if( this.files.length > 0 ){ //파일목록에 파일이 있는 경우
			for(i=0; i<this.files.length; i++){
				tag += `
					<div class="file-item d-flex gap-2 my-1">
						<button type="button" class="btn-close small" data-seq="${i}"></button>
						<span>${ this.files[i].name }</span>
					</div>
				`;
			}
		}else{
			tag = `<div class="py-3 text-center">첨부할 파일을 마우스로 끌어 오세요</div>`
		}
		$(".file-drag").html( tag );
		console.log(">> ", this)		
	}



}








	//폴더 제한하기
	function filterFolder( transfer ) {
	var files = [], folder = false;
	for( i = 0; i<transfer.items.length; i++) {
		var entry = transfer.items[i].webkitGetAsEntry();
		
	}	
		
		
		
		
	}
	
	
	
	
	
	
	






$(function() {







	//드래그 앤 드롭
	$(".file-drag")
	.on("dragover dragleave drop", function(e){
		e.preventDefault(); // 드롭을 허용하기 위해 기본 동작 취소
		
		//드래그 오버시 입력태그에 커서 있을때처럼 적용하기
		if( e.type == "dragover" ) 	$(this).addClass("drag-over");
		else 						$(this).removeClass("drag-over");
		
	})
	
	.on("drop", function(e){
		console.log( "e>", e )
		console.log( "e>", e.originalEvent.dataTransfer.files )
		var files = filterFolder( e.originalEvent.dataTransfer );
		 
		$(files).each(function(){
			fileList.setFile( this )
		})
		console.log( 'fileList> ', fileList )
		fileList.showFile(); //끌어온 파일목록 보이게
	})
	
	;	

	
	
	
	

	$("body")
	.on("dragover dragleave drop", function(e){ 
		e.preventDefault(); 
	});





	$("#file-multiple").on("change", function(){
			var files = this.files;
			$(files).each(function(){
				fileList.setFile( this )
			})
			fileList.showFile();
		})











	$(".file-delete").click(function() {
			//선택했던 파일정보 삭제. 미리보기도 안보이게, 삭제버튼도 안보이게
	//		var _info = $(this).closest(".file-info");
	//		_info.find(".file-preview").empty();
	//		_info.find("input[type=file]").val("");
	//		$(this).addClass("d-none")
			initFileInfo(  $(this) )
		})
		
	
	
	
	
	
	
	
	
	
	
	
	})






	//선택했던 파일정보 삭제. 미리보기도 안보이게, 삭제버튼도 안보이게, 
	function initFileInfo( tag ){
		var _info = tag.closest(".file-info");
		_info.find("input[type=file]").val(""); 		//선택한 파일정보 초기화
		_info.find(".file-preview").empty(); 			//미리보기 이미지 안보이게
		_info.find(".file-name").empty(); 				//선택한 파일명 안보이게
		_info.find(".file-delete").addClass("d-none") 	//삭제버튼 안보이게
	
	}
		
		
		
		
		
		
		
		
		
		
	$(document)
	.on("click", ".file-item .btn-close", function(){
		//console.log( 'idx> ', $(this).data("seq") )
		fileList.removeFile( $(this).data("seq")  )
		fileList.showFile()
	})
	
	
	
	
	
	
	
	
