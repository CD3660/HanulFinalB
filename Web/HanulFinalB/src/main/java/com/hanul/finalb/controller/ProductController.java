package com.hanul.finalb.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.product.ProductService;
import com.hanul.finalb.product.ProductVO;


@Controller
@RequestMapping("/prod")
public class ProductController {
	@Autowired
	private ProductService service;

	/* ��ǰ �Ұ� ������ ��ȯ�� ���� ������ */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<ProductVO> list = service.list();
		//productservice���� ��ǰ ����Ʈ ��ȸ �� ��ȯ
		model.addAttribute("list", list);
		System.out.println(list.get(1).getDetail());
		
		return "product/list";
	}
}
