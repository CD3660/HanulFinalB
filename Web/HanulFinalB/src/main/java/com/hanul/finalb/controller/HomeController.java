package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.HomeService;
import com.hanul.finalb.common.Common;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.product.ProductVO;

@Controller
public class HomeController {

	@Autowired
	private HomeService service;
	@Autowired
	private MemberService member;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		
		String user_id = "hanul";		
		MemberVO vo = member.login(user_id);
		session.setAttribute("loginInfo", vo);
		session.removeAttribute("category");
		
		
		
		
		

		List<ProductVO> list = new ArrayList<ProductVO>();
		list.add(service.product_info(14)); //0 cctv
		list.add(service.product_info(15)); //1 화재
		list.add(service.product_info(16)); //2 가스
		list.add(service.product_info(18)); //3 미세먼지
	
		model.addAttribute("list", list);

		return "home/home";
	}


	@RequestMapping("/file/redirect")
	public String auth() {
		
		return "redirect:/";
	}
	
	@Autowired
	private Common comm;
	
	@RequestMapping("/download")
	public void download(HttpServletRequest req, HttpServletResponse resp) {
		try {
			comm.fileDownload("19qyY9Ja52jfCCGrQ_l_2DNwIOWqDXRVb", "제목없음.png", req, resp);
			
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/error")
	public String error(HttpServletRequest req, Model model) {
		int errorCode = (int) req.getAttribute("javax.servlet.error.status_code");
		if(errorCode==500) {
			Throwable exception = (Throwable) req.getAttribute("javax.servlet.error.exception");
			model.addAttribute("error", exception.toString());
		}
		return "default/error/" + (errorCode==404?404:"common");
	}
	

}
