package com.hanul.mysmarthome.notice;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;



public class NoticeVO implements Serializable {
	private int notice_id, readcnt, no;
	private String title, content, writer, name;
	private String writedate;


	public NoticeVO(int notice_id, int readcnt, int no, String title, String content, String writer, String name, String writedate) {
		this.notice_id = notice_id;
		this.readcnt = readcnt;
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.name = name;
		this.writedate = writedate;
	}

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
