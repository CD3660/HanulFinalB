package com.hanul.finalb.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.product.ProductService;
import com.hanul.finalb.product.ProductVO;

@Controller
@RequestMapping("/prod")
public class ProductController {
	@Autowired
	private ProductService service;

	@Autowired
	private Common comm;

	/*  */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		List<ProductVO> list = service.list();
		// productservice
		model.addAttribute("list", list);

		return "product/list";
	}

	@RequestMapping("/download")
	public void download(HttpSession session, HttpServletRequest req, HttpServletResponse resp, int id) {
		
		//comm.fileDownload(vo, req, resp);
	}
}
