package com.hanul.finalb;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.product.ProductVO;


@Controller
public class HomeController {
	@Autowired private HomeService service;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		list.add(service.product_info(2));
		list.add(service.product_info(3));
		list.add(service.product_info(4));
		list.add(service.product_info(6));
	
		model.addAttribute("list", list);
		
		return "home/home";
	}
	
	
	
	
	
}
