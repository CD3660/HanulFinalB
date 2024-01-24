package com.hanul.finalb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.product.ProductService;


@Controller
@RequestMapping("/prod")
public class ProductController {
	@Autowired
	private ProductService service;

	/* ��ǰ �Ұ� ������ ��ȯ�� ���� ������ */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//productservice���� ��ǰ ����Ʈ ��ȸ �� ��ȯ
		model.addAttribute("list", service.list());
		
		return "product/list";
	}
}
