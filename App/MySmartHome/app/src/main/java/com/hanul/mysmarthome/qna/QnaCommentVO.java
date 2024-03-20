package com.hanul.mysmarthome.qna;

import java.io.Serializable;




public class QnaCommentVO implements Serializable {
	private int comment_id, qna_id;
	private String content, writer, name, profile;
	private String writedate;


	public QnaCommentVO(int comment_id, int qna_id, String content, String writer, String name, String profile, String writedate) {
		this.comment_id = comment_id;
		this.qna_id = qna_id;
		this.content = content;
		this.writer = writer;
		this.name = name;
		this.profile = profile;
		this.writedate = writedate;
	}


	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getQna_id() {
		return qna_id;
	}

	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
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

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}




