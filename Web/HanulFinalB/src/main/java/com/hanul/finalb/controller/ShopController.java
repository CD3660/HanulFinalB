package com.hanul.finalb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@RequestMapping("/list")
	public String list() {
		
		return "shop/list";
	}
}
