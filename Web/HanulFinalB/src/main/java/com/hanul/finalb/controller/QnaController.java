package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;
import com.hanul.finalb.qna.QnaService;
import com.hanul.finalb.qna.QnaVO;

@Controller @RequestMapping("/qna")
public class QnaController {
	
	@Autowired private QnaService service;
	@Autowired private Common common;
	
	

	
	//���� ��������ó�� ��û
	
	//���� ����ȭ�� ��û
	
	//���� �ۻ���ó�� ��û
	
	//���� ÷������ �ٿ�ε� ��û
	@RequestMapping("/download")
	public void download(int no, HttpServletRequest request, HttpServletResponse response) {
		//�ش� ���������� ��ȸ�ؿ� Ŭ���̾�Ʈ�� �ٿ�ε��ϱ�
		FileVO vo = service.qna_file_info(no);
		/*
		 * common.fileDownload(vo.getFilename(), vo.getFilepath(), request, response);   -> �ٿ�ε�ó�� �� �������
		 */
		
	}
	
	
	//���� ����ȭ�� ��û
	@RequestMapping("/info")
	public String info(int id, Model model, PageVO page) {
		//�ش� id�� ������ DB���� ��ȸ�ؿ� ����ȭ�鿡 ����� �� �ֵ��� Model�� ���
		service.qna_read(id); //��ȸ�� ����
		model.addAttribute("vo", service.qna_info(id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		
		return "qna/info";
	}
	
	
	
	//���� �űԵ��ó�� ��û
	@RequestMapping("/insert")
	public String insert(QnaVO vo, MultipartFile[] file, HttpServletRequest request) throws GeneralSecurityException, IOException {
		//÷�ε� ���ϵ��� QnaVO �� fileList�� ���
		vo.setFileList(common.multipleFileUpload("qna", file, request));
		
		//ȭ�鿡�� �Է��� ������ DB�� �űԻ�������ó�� -> ȭ�鿬��:���
		service.qna_register(vo);
		return "redirect:list";
		
	}
	
	
	
	
	
	//���� �űԵ��ȭ�� ��û
	@RequestMapping("/register")
	public String register() {
		return "qna/register";
	}
	
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
