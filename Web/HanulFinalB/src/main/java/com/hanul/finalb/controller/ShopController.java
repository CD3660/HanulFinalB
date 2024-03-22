package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.product.ProductService;
import com.hanul.finalb.product.ProductVO;
import com.hanul.finalb.shop.OrderVO;
import com.hanul.finalb.shop.PaymentVO;
import com.hanul.finalb.shop.ReviewVO;
import com.hanul.finalb.shop.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopService service;
	@Autowired
	private ProductService prodService;
	@Autowired
	private Common comm;

	@ResponseBody
	@RequestMapping("/token")
	public String token() {
		return service.getToken();
	}

	@RequestMapping("/list")
	public String list(Model model, String keyword) {
		if (keyword == null) {
			model.addAttribute("list", service.list());
		} else {
			model.addAttribute("list", service.list(keyword));
			model.addAttribute("keyword", keyword);
		}

		return "shop/list";
	}

	@RequestMapping("/info")
	public String info(Model model, int prod_id) {
		model.addAttribute("vo", service.info(prod_id));
		model.addAttribute("list", service.getReview(prod_id));
		return "shop/info";
	}

	@RequestMapping("/to_cart")
	public String to_cart(HttpSession session, OrderVO vo) {
		service.createCart(vo);

		return "redirect:/shop/list";
	}

	@RequestMapping("/cart")
	public String cart(HttpSession session, Model model) {
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		if(loginInfo == null) {
			model.addAttribute("url", "member/login");
			return "shop/redirect";
		}
		List<OrderVO> list = service.cartList(loginInfo.getUser_id());
		model.addAttribute("list", list);
		
		return "shop/cart";
	}
	@RequestMapping("/delete_cart")
	public String delete_cart(HttpSession session, int order_id) {
		service.deleteCart(order_id);

		return "redirect:/shop/cart";
	}
	@RequestMapping("/order_cart")
	public String order_cart(HttpSession session, Model model) {
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		List<OrderVO> list = service.cartList(loginInfo.getUser_id());
		
		model.addAttribute("list", list);
		int totalprice = service.getTotalPrice(list);
		model.addAttribute("totalPrice", totalprice);
		model.addAttribute("name", list.get(0).getProd_name()+"등 "+list.size()+"개");
		model.addAttribute("order_id", service.inOrderId(list));
		String uid = UUID.randomUUID().toString().substring(0, 10);
		model.addAttribute("uid", uid);
		service.prepare(uid, totalprice);

		return "shop/order";
	}

	@RequestMapping("/order")
	public String order(Model model, OrderVO vo) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		service.payNow(vo);
		model.addAttribute("list", list);
		model.addAttribute("totalPrice", vo.getPrice() * vo.getEa());
		model.addAttribute("name", vo.getProd_name());
		model.addAttribute("order_id", "(" + vo.getOrder_id() + ")");
		String uid = UUID.randomUUID().toString().substring(0, 10);
		model.addAttribute("uid", uid);
		service.prepare(uid, vo.getPrice() * vo.getEa());

		return "shop/order";
	}

	public MemberVO tempLogin() {
		MemberVO login = new MemberVO();
		login.setAdmin("Y");
		login.setName("김한울");
		login.setEmail("email@email.com");
		login.setPhone("010-0000-0000");
		login.setAddress("502502");
		login.setAddress2("광주광역시 서구 농성동 271-4");
		login.setUser_id("hanul");
		return login;
	}

	@ResponseBody
	@RequestMapping("/pay")
	public String pay(Model model, PaymentVO vo, String address2_1, String address2_2, String order_id) {
		String address = address2_1 + " " + address2_2;
		vo.setAddress(address);
		if (service.insertPayments(vo) == 1) {
			return "success";
		} else {
			return "fail";
		}
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

		return "redirect:info?prod_id=" + vo.getProd_id();
	}

	@RequestMapping("/delete")
	public String delete(int prod_id) throws GeneralSecurityException, IOException {
		ProductVO vo = service.info(prod_id);
		if (vo.getProd_img_id() != null) {
			comm.fileDelete(vo.getProd_img_id());
		}
		service.delete(prod_id);

		return "redirect:/shop/list";
	}

	@ResponseBody
	@RequestMapping("/uploadSummernoteImageFile")
	public String uploadSummernoteImageFile(MultipartFile file) throws GeneralSecurityException, IOException {
		String id = comm.fileUpload(file);

		return comm.fileURL(id);
	}
	
	@RequestMapping("/delete_review")
	public String delete_review(int review_id, int prod_id) {
		service.deleteReview(review_id);

		return "redirect:/shop/info?prod_id="+prod_id;
	}
	@ResponseBody
	@RequestMapping("/can_review_check")
	public String can_review_check(ReviewVO vo) {
		if(service.can_review_check(vo).size()==0) {
			//주문 내역이 없는 경우 리뷰 작성 불가
			return "fail";
		} else {
			//주문 내역이 있는 경우 리뷰 작성 가능
			return "success";
		}
	}
	@RequestMapping("/reviewPage")
	public String reviewPage(HttpSession session, Model model ,int prod_id) {
		model.addAttribute("prod_id", prod_id);
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		model.addAttribute("user_id", loginInfo.getUser_id());

		return "shop/review";
	}
	@RequestMapping("/insert_review")
	public String insert_review(ReviewVO vo) {
		service.createReview(vo);

		return "redirect:/shop/info?prod_id="+vo.getProd_id();
	}
}
