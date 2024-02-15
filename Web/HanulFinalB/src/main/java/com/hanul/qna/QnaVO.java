package com.hanul.qna;

import java.sql.Date;
import java.util.List;

import com.hanul.finalb.common.FileVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QnaVO {
	private int id, readcnt, no, filecnt;
	private String title, content, writer, name;
	private Date wirtedate;
	
	
	private List<FileVO> fileList; //****************
	

}
