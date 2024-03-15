package com.hanul.mysmarthome.qna;

import com.hanul.mysmarthome.common.FileDTO;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class QnaVO implements Serializable {
	private int qna_id, readcnt, no, filecnt;
	private String title, content, writer, name;
	private String writedate;
	
	
	private List<FileDTO> fileList;

	
	private int root, step, indent, rid;

	
	
	private String filename, filepath;

	public QnaVO(int qna_id, int readcnt, int no, int filecnt, String title, String content, String writer,
				 String name, String writedate, List<FileDTO> fileList, int root, int step, int indent,
				 int rid, String filename, String filepath) {

		this.qna_id = qna_id;
		this.readcnt = readcnt;
		this.no = no;
		this.filecnt = filecnt;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.name = name;
		this.writedate = writedate;
		this.fileList = fileList;
		this.root = root;
		this.step = step;
		this.indent = indent;
		this.rid = rid;
		this.filename = filename;
		this.filepath = filepath;
	}




	public int getQna_id() {
		return qna_id;
	}

	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
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

	public int getFilecnt() {
		return filecnt;
	}

	public void setFilecnt(int filecnt) {
		this.filecnt = filecnt;
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

	public List<FileDTO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileDTO> fileList) {
		this.fileList = fileList;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}

