package com.hanul.finalb.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;

@Service
public class QnaService {
	@Autowired private SqlSession sql;
	
	
	
	//신규 방명록 글 저장처리
	public int qna_register(QnaVO vo) {
		int dml= sql.insert("qna.register", vo);
		if ( dml ==1 && vo.getFileList() != null) {
			
		}
		return dml;
	}
	
	
	//방명록 목록 조회
	public PageVO qna_list(PageVO page) {
		page.setTotalList(sql.selectOne("qna.totalList", page));
		page.setList(sql.selectList("qna.list", page));
		
		return page;
	}
	
	//선택한 방명록 정보 조회
	public QnaVO qna_info(int qna_id) {
		QnaVO vo = sql.selectOne("qna.info", qna_id);
		//첨부된 파일정보 조회
		vo.setFileList(sql.selectList("qna.fileList", qna_id));
		
		return vo;
	}
	
	
	
	//방명록 정보 변경저장처리
	public int qna_update(QnaVO vo) {
		return sql.update("notice.upadate", vo);
	}
	
	
	
	
	
	//방명록 정보 조회수 변경저장
	public int qna_read(int qna_id) {
		return sql.update("qna.read", qna_id);
	}
	
	//방명록 정보 삭제처리
	public int qna_delete(int qna_id) {
		return sql.delete("qna.delete", qna_id);
	}
	
	
	
	
	
	
	//댓글 등록저장처리
	//댓글 변경저장처리
	//댓글 삭제처리
	//댓글 목록조회
	
	
	
	
	
	
	//파일목록 조회
	public List<FileVO> qna_file_list(int qna_id) {	//qna의 qna_id
		return sql.selectList("qna.fileList", qna_id);
	}
	
	//파일정보 조회
	public FileVO qna_file_info(int file_id) { //qna_file의 file_id
		return sql.selectOne("qna.fileInfo", file_id);
	}
	
	
	
	//파일삭제
	
	

	
	
	
	
	
	
	
	
	//신규답글저장
	public int qna_replyRegister(QnaVO vo) {
		return sql.insert("qna.replyRegister", vo);
	}
	
	
	
	
	
}
