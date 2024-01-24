package com.hanul.finalb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.product.ProductService;

@RequestMapping("/prod")
@Controller
public class ProductController {
	@Autowired
	private ProductService service;

	/* 제품 소개 페이지 전환시 메인 페이지 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//productservice에서 제품 리스트 조회 후 전환
		model.addAttribute("list", service.list());
		
		return "product/list";
	}
}
