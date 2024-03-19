package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;
import com.hanul.finalb.notice.NoticeService;
import com.hanul.finalb.notice.NoticeVO;
import com.hanul.finalb.qna.QnaVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService service;
	@Autowired
	private Common common;

	// Notice 수정저장처리 요청
	@RequestMapping("/update")
	public String update(NoticeVO vo, Model model, PageVO page, String remove, MultipartFile[] addfile, HttpServletRequest request) throws GeneralSecurityException, IOException {
		//화면에서 입력한 정보로DB에 변경저장한 후 정보화면으로 연결
		
		
		//******************************************************
		if( service.notice_update(vo)==1 ) {
			
		}
		
		model.addAttribute("qna_id", vo.getNotice_id());
		model.addAttribute("url", "qna/info");
		model.addAttribute("page", page);
		
		return "include/redirect";
	}
		


	
	
	
	// Q&A 수정화면 요청
	@RequestMapping("/modify")
	public String modify(int notice_id, Model model, PageVO page) {
		model.addAttribute("vo", service.notice_info(notice_id));
		model.addAttribute("page", page);

		return "notice/modify";
	}
	 
	
	
	// Q&A 정보화면 요청
	@RequestMapping("/info")
	public String info(int notice_id, Model model, PageVO page) {
		// 해당 id의 정보를 DB에서 조회해와 정보화면에 출력할 수 있도록 Model에 담기
		service.notice_read(notice_id); // 조회수 변경
		model.addAttribute("vo", service.notice_info(notice_id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);

		return "notice/info";
	}

	// Q&A 신규등록화면 요청
	@RequestMapping("/register")
	public String register() {
		return "notice/register";
	}

	// Q&A 신규등록처리 요청
	@RequestMapping("/insert")
	public String insert(NoticeVO vo, MultipartFile[] addfile, HttpServletRequest request)
			throws GeneralSecurityException, IOException {


		// 화면에서 입력한 정보로 DB에 신규삽입저장처리 -> 화면연결:목록
		service.notice_register(vo);
		return "redirect:list";

	}

	// Q&A 목록화면 조회
	@RequestMapping("/list")
	public String list(PageVO page, Model model, HttpSession session) {
		session.setAttribute("category", "notice");
		// DB에서 방명록 글을 한 페이지 정보를 조회해와 화면에 출력할 수 있도록 Model에 담기
		model.addAttribute("page", service.notice_list(page));

		return "notice/list";
	}

	// Q&A 글삭제처리 요청
	@RequestMapping("delete")
	public String delete(int notice_id, PageVO page, Model model, HttpServletRequest request)
			throws GeneralSecurityException, IOException {
		
		// 해당 Q&A 글을 DB에서 삭제하기
		// qna를 삭제하면 table설계시 fk(외래키)에 on delete cascade에 의해 qna_file도 함께 자동 삭제
		//****************************************************************************************
		if (service.notice_delete(notice_id) == 1) {
			
		}

		// 삭제후 목록화면으로 연결
		model.addAttribute("page", page);
		model.addAttribute("qna_id", notice_id);
		model.addAttribute("url", "qna/list");

		return "include/redirect";
	}



}
