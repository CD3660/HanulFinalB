package com.hanul.finalb.qna;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.finalb.common.PageVO;

@Controller @RequestMapping("/qna")
public class QnaController {
	
	@Autowired private QnaService service;
	
	

	
	
	
	//���� ��������ó�� ��û
	
	//���� ����ȭ�� ��û
	
	//���� �ۻ���ó�� ��û
	
	//���� ÷������ �ٿ�ε� ��û
	
	//���� ����ȭ�� ��û
	
	//���� �űԵ��ó�� ��û
	
	//���� �űԵ��ȭ�� ��û
	
	//���� ���ȭ�� ��ȸ
	
	@RequestMapping("/list")
	public String list(PageVO page, Model model, HttpSession session) {
		session.setAttribute("category", "qna");
		//DB���� ���� ���� �� ������ ������ ��ȸ�ؿ� ȭ�鿡 ����� �� �ֵ��� Model�� ���
		model.addAttribute("page", service.qna_list(page));
	
		return "qna/list";
	}


	
	
	
	
	
	//��� ����ó�� ��û

	//��� ��������ó�� ��û

	//��� �����ȸ
	
	//��� �������ó�� ��û
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
