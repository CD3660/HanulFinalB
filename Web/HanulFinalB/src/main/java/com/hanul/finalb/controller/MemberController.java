package com.hanul.finalb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;

	@Autowired
	private MemberService service;

	/* 占쏙옙품 占쌀곤옙 占쏙옙占쏙옙占쏙옙 占쏙옙환占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "member/login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {

		return "member/join";
	}

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
	
	@ResponseBody
	@RequestMapping(value = "/secuTest", method = RequestMethod.GET)
    public void secuTest() {
        
        String rawPassword = "hanul123";                //占쏙옙占쌘듸옙 占쏙옙 占쌨쇽옙占쏙옙
        String encdoePassword1;                        // 占쏙옙占쌘듸옙占쏙옙 占쌨쇽옙占쏙옙
        String encdoePassword2;                        // 占싫곤옙占쏙옙 占쏙옙橘占싫� 占쏙옙占쏙옙占싶몌옙 encdoe()占쌨쇽옙占썲를 占쏙옙占쏙옙占쏙옙占� 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 확占쏙옙占싹깍옙 占쏙옙占쏙옙 占쌩곤옙
        
        encdoePassword1 = pwEncoder.encode(rawPassword);
        encdoePassword2 = pwEncoder.encode(rawPassword);
        
        // 占쏙옙占쌘듸옙占쏙옙 占싻쏙옙占쏙옙占쏙옙 占쏙옙占�
        System.out.println("encdoePassword1 : " +encdoePassword1);
        System.out.println(" encdoePassword2 : " + encdoePassword2);
        
        String truePassowrd = "hanul123";
        String falsePassword = "asdfjlasf";
        
        System.out.println("truePassword verify : " + pwEncoder.matches(truePassowrd, encdoePassword1));
        System.out.println("falsePassword verify : " + pwEncoder.matches(falsePassword, encdoePassword1));    
    
    }
	
	
	
}
