package com.hanul.qna;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.PageVO;

@Service
public class QnaService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	
	
	//���� ��������ó�� ��û
	
	//���� ����ȭ�� ��û
	
	//���� �ۻ���ó�� ��û
	
	//���� ÷������ �ٿ�ε� ��û
	
	//���� ����ȭ�� ��û
	
	//���� �űԵ��ó�� ��û
	
	//���� �űԵ��ȭ�� ��û
	
	//���� ��� ��ȸ
	public PageVO qna_list(PageVO page) {
		page.setTotalList(sql.selectOne("qna.totalList", page));
		page.setList(sql.selectList("board.list", page));
		
		return page;
	}
	
	
	
	
	
	
	//��� ����ó�� ��û

	//��� ��������ó�� ��û

	//��� �����ȸ
	
	//��� �������ó�� ��û
	
	
	
}
