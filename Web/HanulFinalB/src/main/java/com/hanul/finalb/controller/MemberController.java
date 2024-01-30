package com.hanul.finalb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.product.ProductService;


@Controller
@RequestMapping("/member")
public class MemberController {
	

	/* 제품 소개 페이지 전환시 메인 페이지 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		

		
		
		return "member/login";
	}
	
	
	
	
	
}
