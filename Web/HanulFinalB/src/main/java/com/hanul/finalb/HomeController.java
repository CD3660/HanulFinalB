package com.hanul.finalb;

import java.text.DateFormat;
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
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home/home";
	}
	@RequestMapping(value = "/prod", method = RequestMethod.GET)
	public String product(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "product/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	//Ȩȭ�� ���â
	@RequestMapping("/homemodal")
	public String mainModal(Model model, int id) {
		
		//�ش� ��ǰ ���̵� DB���� ��ȸ -> ȭ�鿡 ��� �� �� �ֵ��� Model ��ü�� ���
		ProductVO vo = service.product_info(id);
		model.addAttribute( "vo", vo );
		
		return "home/home";
		
	}
	
}
