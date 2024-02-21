package com.hanul.finalb.qna;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.PageVO;

@Service
public class QnaService {
	@Autowired
	private SqlSession sql;

	// 신규 방명록 글 저장처리
	public int qna_register(QnaVO vo) {
		int dml = sql.insert("qna.register", vo);
		if (dml == 1 && vo.getFileList() != null) {

		}
		return dml;
	}

	// 방명록 목록 조회
	public PageVO qna_list(PageVO page) {
		page.setTotalList(sql.selectOne("qna.totalList", page));
		page.setList(sql.selectList("qna.list", page));

		return page;
	}

	// 선택한 방명록 정보 조회
	public QnaVO qna_info(int id) {
		QnaVO vo = sql.selectOne("qna.info", id);
		// 첨부된 파일정보 조회
		vo.setFileList(sql.selectList("qna.fileList", id));

		return vo;
	}

	// 방명록 정보 변경저장처리

	// 방명록 정보 조회수 변경저장
	public int qna_read(int id) {
		return sql.update("qna.read", id);
	}

	// 방명록 정보 삭제처리

	// 댓글 등록저장처리
	// 댓글 변경저장처리
	// 댓글 삭제처리
	// 댓글 목록조회

	// 파일목록 조회
	// 파일정보 조회
	// 파일삭제

}