package com.hanul.finalb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;

	@Autowired
	private MemberService service;

	/* ��ǰ �Ұ� ������ ��ȯ�� ���� ������ */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "member/login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {

		return "member/join";
	}
<<<<<<< HEAD
	@RequestMapping("/login")
	public String Search(Model model) {

		return "member/join";
	}
	
	@RequestMapping("/userSearch")
	public String userSearch(Model model) {
=======
>>>>>>> main

	@RequestMapping(path = "/join", method = RequestMethod.POST)
	public String joinpass(MemberVO member) throws Exception {
		
		service.memberJoin(member);

		return "redirect:/member/login";
		

	}
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginpass(MemberVO member) throws Exception {
		
		service.memberJoin(member);

		return "redirect:/member/login";
		

	
	

	}
	@RequestMapping(value = "/secuTest", method = RequestMethod.GET)
    public void secuTest() {
        
        String rawPassword = "hanul123";                //���ڵ� �� �޼���
        String encdoePassword1;                        // ���ڵ��� �޼���
        String encdoePassword2;                        // �Ȱ��� ��й�ȣ �����͸� encdoe()�޼��带 ������� �� ������ ���ڵ��� ���� �������� Ȯ���ϱ� ���� �߰�
        
        encdoePassword1 = pwEncoder.encode(rawPassword);
        encdoePassword2 = pwEncoder.encode(rawPassword);
        
        // ���ڵ��� �н����� ���
        System.out.println("encdoePassword1 : " +encdoePassword1);
        System.out.println(" encdoePassword2 : " + encdoePassword2);
        
        String truePassowrd = "hanul123";
        String falsePassword = "asdfjlasf";
        
        System.out.println("truePassword verify : " + pwEncoder.matches(truePassowrd, encdoePassword1));
        System.out.println("falsePassword verify : " + pwEncoder.matches(falsePassword, encdoePassword1));    
    
    }
	
	
	
}
