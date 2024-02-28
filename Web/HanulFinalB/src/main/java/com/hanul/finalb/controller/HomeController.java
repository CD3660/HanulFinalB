package com.hanul.finalb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.HomeService;
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
		
		//�׽�Ʈ�� �ӽ� �α��� ó��---------------------------
		String user_id = "hanul";
		MemberVO vo = member.member_info(user_id);
		session.setAttribute("loginInfo", vo);
		
		//-----------------------------------------------
		
		session.removeAttribute("category");
		
		
		
		
		

		List<ProductVO> list = new ArrayList<ProductVO>();
		list.add(service.product_info(2)); //0 cctv
		list.add(service.product_info(3)); //1 ȭ��
		list.add(service.product_info(4)); //2 ����
		list.add(service.product_info(6)); //3 �̼�����
	
		model.addAttribute("list", list);

		return "home/home";
	}


	
	
	
	
	
	
	

}
