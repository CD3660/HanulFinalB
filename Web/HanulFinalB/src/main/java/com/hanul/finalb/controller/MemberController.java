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

	/* ï¿½ï¿½Ç° ï¿½Ò°ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¯ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "member/login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {

		return "member/join";
	}

	@RequestMapping(path = "/join", method = RequestMethod.POST)
<<<<<<< HEAD
	public String joinpass(MemberVO member) throws Exception {
		
=======
	public String finalb(MemberVO member) throws Exception {

>>>>>>> main
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
        
        String rawPassword = "hanul123";                //ÀÎÄÚµù Àü ¸Þ¼­µå
        String encdoePassword1;                        // ÀÎÄÚµùµÈ ¸Þ¼­µå
        String encdoePassword2;                        // ¶È°°Àº ºñ¹Ð¹øÈ£ µ¥ÀÌÅÍ¸¦ encdoe()¸Þ¼­µå¸¦ »ç¿ëÇßÀ» ¶§ µ¿ÀÏÇÑ ÀÎÄÚµùµÈ °ªÀÌ ³ª¿À´ÂÁö È®ÀÎÇÏ±â À§ÇØ Ãß°¡
        
        encdoePassword1 = pwEncoder.encode(rawPassword);
        encdoePassword2 = pwEncoder.encode(rawPassword);
        
        // ÀÎÄÚµùµÈ ÆÐ½º¿öµå Ãâ·Â
        System.out.println("encdoePassword1 : " +encdoePassword1);
        System.out.println(" encdoePassword2 : " + encdoePassword2);
        
        String truePassowrd = "hanul123";
        String falsePassword = "asdfjlasf";
        
        System.out.println("truePassword verify : " + pwEncoder.matches(truePassowrd, encdoePassword1));
        System.out.println("falsePassword verify : " + pwEncoder.matches(falsePassword, encdoePassword1));    
    
    }
	
	
	
}
