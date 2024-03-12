package com.hanul.mysmarthome.qna;

import com.hanul.mysmarthome.common.FileVO;

import java.sql.Date;
import java.util.List;


public class QnaVO {
	private int qna_id, readcnt, no, filecnt;
	private String title, content, writer, name;
	private Date writedate;
	
	
	private List<FileVO> fileList;

	
	private int root, step, indent, rid;

	
	
	private String filename, filepath;
}
