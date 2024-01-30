package com.hanul.finalb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.finalb.shop.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired private ShopService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", service.list());
		
		return "shop/list";
	}
}
