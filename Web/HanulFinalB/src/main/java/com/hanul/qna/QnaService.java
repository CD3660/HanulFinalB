package com.hanul.qna;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.PageVO;

@Service
public class QnaService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	
	
	//방명록 수정저장처리 요청
	
	//방명록 수정화면 요청
	
	//방명록 글삭제처리 요청
	
	//방명록 첨부파일 다운로드 요청
	
	//방명록 정보화면 요청
	
	//방명록 신규등록처리 요청
	
	//방명록 신규등록화면 요청
	
	//방명록 목록 조회
	public PageVO qna_list(PageVO page) {
		page.setTotalList(sql.selectOne("qna.totalList", page));
		page.setList(sql.selectList("board.list", page));
		
		return page;
	}
	
	
	
	
	
	
	//댓글 삭제처리 요청

	//댓글 변경저장처리 요청

	//댓글 목록조회
	
	//댓글 등록저장처리 요청
	
	
	
}
