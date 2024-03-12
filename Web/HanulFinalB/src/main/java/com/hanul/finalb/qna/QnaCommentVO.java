package com.hanul.finalb.qna;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QnaCommentVO {
	private int comment_id, qna_id;
	private String content, writer, name, profile;
	private String writedate;

}
