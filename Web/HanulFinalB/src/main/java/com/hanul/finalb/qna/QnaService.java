package com.hanul.finalb.qna;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;

@Service
public class QnaService {
	@Autowired private SqlSession sql;
	
	
	//안드로이드 Q&A 목록 조회
	public List<QnaVO> appQnaList() {
	   
		List<QnaVO> list = sql.selectList("qna.appQnaList");
		 return list;
	}
	
	
	
	//신규 Q&A 글 저장처리
	public int qna_register(QnaVO vo) {
		int dml= sql.insert("qna.register", vo);
		if ( dml ==1 && vo.getFileList() != null) {
			sql.insert( "qna.fileInsert", vo );
		}
		return dml;
	}
	
	
	//Q&A 목록 조회
	public PageVO qna_list(PageVO page) {
		page.setTotalList(sql.selectOne("qna.totalList", page));
		page.setList(sql.selectList("qna.list", page));
		
		return page;
	}
	
	@Autowired private Common common;
	//선택한 Q&A 정보 조회
	public QnaVO qna_info(int qna_id) {
		QnaVO vo = sql.selectOne("qna.info", qna_id);
		//첨부된 파일정보 조회		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qna_id", qna_id);
		map.put("url", common.fileURL());
		vo.setFileList(sql.selectList("qna.fileList", map));
		
		return vo;
	}
	
	
	
	//Q&A 정보 변경저장처리
	public int qna_update(QnaVO vo) {
		int dml = sql.update("qna.update", vo);
		//첨부파일저장
		if( dml==1 && vo.getFileList()!=null ) {
			sql.insert("qna.fileInsert", vo);
		}
		return dml;
	}
	
	
	
	
	
	//Q&A 정보 조회수 변경저장
	public int qna_read(int qna_id) {
		return sql.update("qna.read", qna_id);
	}
	
	//Q&A 정보 삭제처리
	public int qna_delete(int qna_id) {
		return sql.delete("qna.delete", qna_id);
	}
	
	
	
	
	
	
	

	
	//댓글 목록조회
	public List<QnaCommentVO> qna_comment_list( int qna_id ) {
		return sql.selectList("qna.commentList", qna_id);
	}
	
	//댓글 등록저장처리
	public int qna_comment_register(QnaCommentVO vo) {
		return sql.insert("qna.commentRegister", vo);
	}
	
	
	//댓글 변경저장처리
	public int qna_comment_update(QnaCommentVO vo) {
		return sql.update("qna.commentUpdate", vo);
	}
	
	//댓글 삭제처리
	public int qna_comment_delete(int comment_id) {
		return sql.delete("qna.commentDelete", comment_id);
	}
	
	
	
	
	
	
	
	
	//파일목록 조회
	public List<FileVO> qna_file_list(int qna_id) {	//qna의 qna_id
		return sql.selectList("qna.fileList", qna_id);
	}
	
	public List<FileVO> qna_file_list(String remove){ //qna_file의 file_id들
		return sql.selectList("qna.removeFileList", remove);
	}
	
	
	
	
	//파일정보 조회
	public FileVO qna_file_info(String file_id) {
		return sql.selectOne("qna.fileInfo", file_id);
	}
	
	
	
	
	
	//파일삭제
	public int qna_file_delete(String remove) {
		return sql.delete("qna.fileDelete", remove);
	}
	
	

	
	
	
	
	
	
	
	
	//신규답글저장
	public int qna_replyRegister(QnaVO vo) {
		return sql.insert("qna.replyRegister", vo);
	}
	
	
	
	
	
}
