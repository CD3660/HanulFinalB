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
		
		//테스트용 임시 로그인 처리---------------------------
		String user_id = "admin";
		MemberVO vo = member.memberInfo(user_id);
//		session.setAttribute("loginInfo", vo);
		
		//-----------------------------------------------
		
		session.removeAttribute("category");
		
		
		
		
		

		List<ProductVO> list = new ArrayList<ProductVO>();
		list.add(service.product_info(2)); //0 cctv
		list.add(service.product_info(3)); //1 화재
		list.add(service.product_info(4)); //2 가스
		list.add(service.product_info(6)); //3 미세먼지
	
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
			comm.fileDownload("1oNB5Q0DF2SZDrifjJvywaztddBwNGPcv", "profile.jpg", req, resp);
			
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
