package com.hanul.finalb.notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;

@Service
public class NoticeService {
	@Autowired private SqlSession sql;
	
	
	//안드로이드 Q&A 목록 조회
	public List<NoticeVO> appNoticeList() {
	   
		List<NoticeVO> list = sql.selectList("notice.appNoticeList");
		 return list;
	}
	
	
	
	//신규 Q&A 글 저장처리
	public int notice_register(NoticeVO vo) {
		int dml= sql.insert("notice.register", vo);
		return dml;
	}
	
	
	//Q&A 목록 조회
	public PageVO notice_list(PageVO page) {
		page.setTotalList(sql.selectOne("notice.totalList", page));
		page.setList(sql.selectList("notice.list", page));
		
		return page;
	}
	
	@Autowired private Common common;
	//선택한 Q&A 정보 조회
	public NoticeVO notice_info(int notice_id) {
		NoticeVO vo = sql.selectOne("notice.info", notice_id);		
		return vo;
	}
	
	
	
	//Q&A 정보 변경저장처리
	public int notice_update(NoticeVO vo) {
		int dml = sql.update("notice.update", vo);
		return dml;
	}
	
	
	
	
	//Q&A 정보 조회수 변경저장
	public int notice_read(int notice_id) {
		return sql.update("notice.read", notice_id);
	}
	
	//Q&A 정보 삭제처리
	public int notice_delete(int notice_id) {
		return sql.delete("notice.delete", notice_id);
	}
	
	
	
}
