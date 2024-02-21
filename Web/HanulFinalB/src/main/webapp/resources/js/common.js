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
	
	
	
	
	
	
	
	this.showFile = function() {
		var tag = ""
		if( this.files.length > 0 ) { //파일목록에 파일이 있는 경우
			for(i=0; i<this.files.length; i++) {
				tag += `
					<div class="file-item d flex gap-2">
						<button type="button" class="btn-close small" data-seq="${i}"></button>
						<span>${ this.files[i].name }</span>
					</div>
				`;
				
			}
		}else{
		
		}
		$(".file-drag").html( tag );
		console.log(">>  ", this )
	}
	
	
	
	
	
	
	
}
