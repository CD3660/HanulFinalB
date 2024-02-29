package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.product.ProductVO;
import com.hanul.finalb.shop.OrderVO;
import com.hanul.finalb.shop.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopService service;
	@Autowired
	private Common comm;

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
		model.addAttribute("vo", service.info(id));

		return "shop/info";
	}
	
	@RequestMapping("/to_cart")
	public String to_cart(Model model, int prod_id, int ea) {
		

		return "shop/info";
	}
	
	@RequestMapping("/order")
	public String order(Model model, OrderVO vo) {
		model.addAttribute("vo", vo);
		model.addAttribute("list", service.list());
		MemberVO login = new MemberVO();
		login.setAdmin("Y");
		login.setName("김한울");
		login.setEmail("email@email.com");
		login.setPhone("010-0000-0000");
		login.setAddress("502502");
		login.setAddress2("광주광역시 서구 농성동 271-4");
		model.addAttribute("loginInfo", login);

		return "shop/order";
	}

	@RequestMapping("/insertPage")
	public String insertPage() {

		return "shop/insert";
	}

	@RequestMapping("/insert")
	public String insert(ProductVO vo, MultipartFile file) throws GeneralSecurityException, IOException {
		if (!file.isEmpty()) {
			String id = comm.fileUpload(file);
			vo.setProd_img(comm.fileURL(id));
			vo.setProd_img_id(id);
		}
		service.insert(vo);

		return "redirect:/shop/list";
	}

	@RequestMapping("/updatePage")
	public String updatePage(Model model, int id) {
		model.addAttribute("vo", service.info(id));

		return "shop/update";
	}

	@RequestMapping("/update")
	public String update(ProductVO vo, MultipartFile file, boolean maintain)
			throws GeneralSecurityException, IOException {
		String remove_id = service.info(vo.getProd_id()).getProd_img_id();

		if (!file.isEmpty()) {
			String id = comm.fileUpload(file);
			vo.setProd_img(comm.fileURL(id));
			vo.setProd_img_id(id);
		}
		if (!maintain) {
			if (remove_id != null) {
				comm.fileDelete(remove_id);
			}
		}
		service.update(vo, maintain);

		return "redirect:/shop/info?id=" + vo.getProd_id();
	}
	@RequestMapping("/delete")
	public String delete(int id) throws GeneralSecurityException, IOException {
		ProductVO vo = service.info(id);
			if (vo.getProd_img_id() != null) {
				comm.fileDelete(vo.getProd_img_id());
			}
		service.delete(id);

		return "redirect:/shop/list";
	}

	@ResponseBody
	@RequestMapping("/uploadSummernoteImageFile")
	public String uploadSummernoteImageFile(MultipartFile file) throws GeneralSecurityException, IOException {
		String id = comm.fileUpload(file);

		return comm.fileURL(id);
	}
	
}
