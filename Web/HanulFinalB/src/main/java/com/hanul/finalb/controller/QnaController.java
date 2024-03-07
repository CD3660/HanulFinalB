package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

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
	
	

	
	//방명록 수정저장처리 요청
	
	
	//방명록 수정화면 요청
	@RequestMapping("/modify")
	public String modify() {
		
		
		
		return "qna/modify";
	}
	
	

	
	
	
	
	//방명록 첨부파일 다운로드 요청
	@RequestMapping("/download")
	public void download(int no, HttpServletRequest request, HttpServletResponse response) {
		//해당 파일정보를 조회해와 클라이언트에 다운로드하기
		FileVO vo = service.qna_file_info(no);
		/*
		 * common.fileDownload(vo.getFilename(), vo.getFilepath(), request, response);   -> 다운로드처리 후 살려야함
		 */
		
	}
	
	
	//방명록 정보화면 요청
	@RequestMapping("/info")
	public String info(int qna_id, Model model, PageVO page) {
		//해당 id의 정보를 DB에서 조회해와 정보화면에 출력할 수 있도록 Model에 담기
		service.qna_read(qna_id); //조회수 변경
		model.addAttribute("vo", service.qna_info(qna_id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		
		return "qna/info";
	}
	
	
	
	//방명록 신규등록처리 요청
	@RequestMapping("/insert")
	public String insert(QnaVO vo, MultipartFile[] files, HttpServletRequest request) throws GeneralSecurityException, IOException {
		
		//첨부된 파일들을 QnaVO 의 fileList에 담기
		vo.setFileList(common.multipleFileUpload("qna", files, request));
		
		//화면에서 입력한 정보로 DB에 신규삽입저장처리 -> 화면연결:목록
		service.qna_register(vo);
		return "redirect:list";
		
	}
	
	
	
	
	
	//방명록 신규등록화면 요청
	@RequestMapping("/register")
	public String register() {
		return "qna/register";
	}
	
	//방명록 목록화면 조회
	@RequestMapping("/list")
	public String list(PageVO page, Model model, HttpSession session) {
		session.setAttribute("category", "qna");
		//DB에서 방명록 글을 한 페이지 정보를 조회해와 화면에 출력할 수 있도록 Model에 담기
		model.addAttribute("page", service.qna_list(page));

		return "qna/list";
	}


	
	
	//방명록 글삭제처리 요청
	@RequestMapping("delete")
	public String delete(int qna_id, PageVO page, Model model, HttpServletRequest request) throws GeneralSecurityException, IOException {
		//첨부파일이 있는경우 물리적인 파일을 삭제할 수 있도록 파일정보를 조회해둔다.
		List<FileVO> list = service.qna_file_list(qna_id);
		
		//해당 방명록 글을 DB에서 삭제하기
		//qna를 삭제하면 table설계시 fk(외래키)에 on delete cascade에 의해 qna_file도 함께 자동 삭제
		if( service.qna_delete(qna_id) == 1 )  {
			for( FileVO vo : list) {
				common.fileDelete( vo.getFile_id() );
			}
		}
		
		
		//삭제후 목록화면으로 연결
		model.addAttribute("page", page);
		model.addAttribute("qna_id", qna_id);
		model.addAttribute("url", "qna/list");
		
		
		return "include/redirect";
	}
	
	
	
	//댓글 삭제처리 요청

	//댓글 변경저장처리 요청

	//댓글 목록조회
	
	//댓글 등록저장처리 요청
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
