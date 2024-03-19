package com.hanul.finalb.notice;

import java.sql.Date;
import java.util.List;

import com.hanul.finalb.common.FileVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
	private int notice_id, readcnt, no, filecnt;
	private String title, content, writer, name;
	private String writedate;

	
	
	
	
	private int root, step, indent, rid;

	
	
}
