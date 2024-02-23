package com.hanul.finalb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.shop.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired private ShopService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", service.list());
		MemberVO vo = new MemberVO();
		vo.setAdmin("Y");
		model.addAttribute("loginInfo", vo);
		
		return "shop/list";
	}
	@RequestMapping("/info")
	public String info(Model model, int id) {
		model.addAttribute("info", service.info(id));
		
		return "shop/info";
	}
	@RequestMapping("/insert")
	public String insert(Model model) {
		
		return "shop/insert";
	}
}
