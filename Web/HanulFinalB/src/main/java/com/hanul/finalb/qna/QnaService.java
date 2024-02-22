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
	public QnaVO qna_info(int qna_id) {
		QnaVO vo = sql.selectOne("qna.info", qna_id);
		//÷�ε� �������� ��ȸ
		vo.setFileList(sql.selectList("qna.fileList", qna_id));
		
		return vo;
	}
	
	//���� ���� ��������ó��
	
	//���� ���� ��ȸ�� ��������
	public int qna_read(int qna_id) {
		return sql.update("qna.read", qna_id);
	}
	
	//���� ���� ����ó��
	public int qna_delete(int qna_id) {
		return sql.delete("qna.delete", qna_id);
	}
	
	
	
	
	
	
	//��� �������ó��
	//��� ��������ó��
	//��� ����ó��
	//��� �����ȸ
	
	
	
	
	
	
	//���ϸ�� ��ȸ
	public List<FileVO> qna_file_list(int qna_id) {	//qna�� qna_id
		return sql.selectList("qna.fileList", qna_id);
	}
	
	//�������� ��ȸ
	public FileVO qna_file_info(int file_id) { //qna_file�� file_id
		return sql.selectOne("qna.fileInfo", file_id);
	}
	
	
	
	//���ϻ���
	
	

	
	
	
	
}
