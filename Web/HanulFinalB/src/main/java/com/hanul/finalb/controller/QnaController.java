package com.hanul.finalb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.common.FileVO;
import com.hanul.finalb.common.PageVO;
import com.hanul.finalb.qna.QnaCommentVO;
import com.hanul.finalb.qna.QnaService;
import com.hanul.finalb.qna.QnaVO;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QnaService service;
	@Autowired
	private Common common;

	// Q&A 수정저장처리 요청
	@RequestMapping("/update")
	public String update(QnaVO vo, Model model, PageVO page, String remove, MultipartFile[] addfile, HttpServletRequest request) throws GeneralSecurityException, IOException {
		
		//화면에서 입력한 정보로DB에 변경저장한 후 정보화면으로 연결
		//첨부파일이 있으면 QnaVO 의 fileList에 담기
		vo.setFileList( common.multipleFileUpload(vo.getQna_id(),"qna", addfile, request) );
		if( service.qna_update(vo)==1 ) {
			//삭제된 첨부파일이 있으면 DB에서 삭제+물리적파일도 삭제
			if( ! remove.isEmpty() ) {
				List<FileVO> list = service.qna_file_list(remove);
				if( service.qna_file_delete(remove) > 0 ) {
					for( FileVO f : list ) {
						common.fileDelete(f.getFile_id());
					}
				}
			}
		}
		
		model.addAttribute("qna_id", vo.getQna_id());
		model.addAttribute("url", "qna/info");
		model.addAttribute("page", page);
		
		return "include/redirect";
	}
		

	
	
	
	
	
	
	
	
	
	
	
	// Q&A 수정화면 요청
	@RequestMapping("/modify")
	public String modify(int qna_id, Model model, PageVO page) {
		model.addAttribute("vo", service.qna_info(qna_id));
		model.addAttribute("page", page);

		return "qna/modify";
	}

	
	
	
	
	
	
	
	
	
	
	  // Q&A 첨부파일 다운로드 요청*************************************************************************************************************************
	  @RequestMapping("/download")
	  public void download(int file_id, HttpServletRequest request, HttpServletResponse response) { // 해당 파일정보를 조회해와 클라이언트에 다운로드하기
	  FileVO vo = service.qna_file_info(file_id);
	  
	  //common.fileDownload(vo.getFilename(), vo.getFilepath(), request, response);
	 
	  
	  
	  }
	 

	
	
	
	
	
	
	
	
	
	
	
	// Q&A 정보화면 요청
	@RequestMapping("/info")
	public String info(int qna_id, Model model, PageVO page) {
		// 해당 id의 정보를 DB에서 조회해와 정보화면에 출력할 수 있도록 Model에 담기
		service.qna_read(qna_id); // 조회수 변경
		model.addAttribute("vo", service.qna_info(qna_id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);

		return "qna/info";
	}

	// Q&A 신규등록화면 요청
	@RequestMapping("/register")
	public String register() {
		return "qna/register";
	}

	// Q&A 신규등록처리 요청
	@RequestMapping("/insert")
	public String insert(QnaVO vo, MultipartFile[] addfile, HttpServletRequest request)
			throws GeneralSecurityException, IOException {

		// 첨부된 파일들을 QnaVO 의 fileList에 담기
		vo.setFileList(common.multipleFileUpload(vo.getQna_id(), "qna", addfile, request));

		// 화면에서 입력한 정보로 DB에 신규삽입저장처리 -> 화면연결:목록
		service.qna_register(vo);
		return "redirect:list";

	}

	// Q&A 목록화면 조회
	@RequestMapping("/list")
	public String list(PageVO page, Model model, HttpSession session) {
		session.setAttribute("category", "qna");
		// DB에서 방명록 글을 한 페이지 정보를 조회해와 화면에 출력할 수 있도록 Model에 담기
		model.addAttribute("page", service.qna_list(page));

		return "qna/list";
	}

	// Q&A 글삭제처리 요청
	@RequestMapping("delete")
	public String delete(int qna_id, PageVO page, Model model, HttpServletRequest request)
			throws GeneralSecurityException, IOException {
		// 첨부파일이 있는경우 물리적인 파일을 삭제할 수 있도록 파일정보를 조회해둔다.
		List<FileVO> list = service.qna_file_list(qna_id);

		// 해당 Q&A 글을 DB에서 삭제하기
		// qna를 삭제하면 table설계시 fk(외래키)에 on delete cascade에 의해 qna_file도 함께 자동 삭제
		if (service.qna_delete(qna_id) == 1) {
			for (FileVO vo : list) {
				common.fileDelete(vo.getFile_id());
			}
		}

		// 삭제후 목록화면으로 연결
		model.addAttribute("page", page);
		model.addAttribute("qna_id", qna_id);
		model.addAttribute("url", "qna/list");

		return "include/redirect";
	}

	// 댓글 목록조회
	@RequestMapping("/comment/list/{qna_id}")
	public String comment_list(@PathVariable int qna_id, Model model) {
		model.addAttribute("list", service.qna_comment_list(qna_id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "qna/commentlist/comment_list";
	}

	// 댓글 등록저장처리 요청
	@ResponseBody
	@RequestMapping("/comment/register")
	public boolean comment_register(QnaCommentVO vo) {
		return service.qna_comment_register(vo) == 1 ? true : false;
	}

	// 댓글 변경저장처리 요청
	@ResponseBody
	@RequestMapping("/comment/update")
	public Object comment_update(QnaCommentVO vo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (service.qna_comment_update(vo) == 1) {
			map.put("success", true);
			map.put("message", "성공^^");
			map.put("content", vo.getContent());
		} else {
			map.put("success", false);
			map.put("message", "실패ㅠㅠ");
		}
		return map;
	}

	// 댓글 삭제처리 요청
	@ResponseBody
	@RequestMapping("/comment/delete")
	public Map<String, Object> comment_delete(int comment_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (service.qna_comment_delete(comment_id) == 1) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		return map;
	}

	// 답글쓰기화면 요청
	@RequestMapping("/reply")
	public String reply(int qna_id, PageVO page, Model model) {
		// 원글정보를 조회해와 답글화면에 사용할 수 있도록 Model에 담기
		model.addAttribute("vo", service.qna_info(qna_id)); // 원글의 qna_id와 원글의 정보
		model.addAttribute("page", page); // 원글의 PageVO 정보

		return "qna/reply";
	}

	// 답글저장처리 요청
	@RequestMapping("/replyInsert")
	public String replyInsert(QnaVO vo, PageVO page) throws UnsupportedEncodingException {

		service.qna_replyRegister(vo); // -> 새로운 qna_id가 생성됨

		// 답글의 위치와 상관없이 원글의 curPage로 이동
		return "redirect:list?curPage=" + page.getCurPage() + "&search=" + page.getSearch() + "&keyword="
				+ URLEncoder.encode(page.getKeyword(), "utf-8");

	}

}
