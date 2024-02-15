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
	
	

	
	
	
	//방명록 수정저장처리 요청
	
	//방명록 수정화면 요청
	
	//방명록 글삭제처리 요청
	
	//방명록 첨부파일 다운로드 요청
	
	//방명록 정보화면 요청
	
	//방명록 신규등록처리 요청
	
	//방명록 신규등록화면 요청
	
	//방명록 목록화면 조회
	
	@RequestMapping("/list")
	public String list(PageVO page, Model model, HttpSession session) {
		session.setAttribute("category", "qna");
		//DB에서 방명록 글을 한 페이지 정보를 조회해와 화면에 출력할 수 있도록 Model에 담기
		model.addAttribute("page", service.qna_list(page));
	
		return "qna/list";
	}


	
	
	
	
	
	//댓글 삭제처리 요청

	//댓글 변경저장처리 요청

	//댓글 목록조회
	
	//댓글 등록저장처리 요청
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
