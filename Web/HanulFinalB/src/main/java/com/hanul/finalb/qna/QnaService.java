package com.hanul.finalb.qna;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;

@Service
public class QnaService {
	@Autowired private SqlSession sql;
	
	
	
	//�ű� ���� �� ����ó��
	public int qna_register(QnaVO vo) {
		int dml= sql.insert("qna.register", vo);
		if ( dml ==1 && vo.getFileList() != null) {
			
		}
		return dml;
	}
	
	
	//���� ��� ��ȸ
	public PageVO qna_list(PageVO page) {
		page.setTotalList(sql.selectOne("qna.totalList", page));
		page.setList(sql.selectList("qna.list", page));
		
		return page;
	}
	
	//������ ���� ���� ��ȸ
	public QnaVO qna_info(int id) {
		QnaVO vo = sql.selectOne("qna.info", id);
		//÷�ε� �������� ��ȸ
		vo.setFileList(sql.selectList("qna.fileList", id));
		
		return vo;
	}
	
	//���� ���� ��������ó��
	
	//���� ���� ��ȸ�� ��������
	public int qna_read(int id) {
		return sql.update("qna.read", id);
	}
	
	//���� ���� ����ó��
	
	
	
	
	
	
	//��� �������ó��
	//��� ��������ó��
	//��� ����ó��
	//��� �����ȸ
	
	
	
	
	
	
	//���ϸ�� ��ȸ
	
	//�������� ��ȸ
	public FileVO qna_file_info(int no) { //qna_file�� no
		return sql.selectOne("qna.fileInfo", no);
	}
	
	
	
	//���ϻ���
	
	

	
	
	
	
}
