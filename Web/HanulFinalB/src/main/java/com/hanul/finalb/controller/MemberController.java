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
	public String finalb(MemberVO member) throws Exception {

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

		String rawPassword = "hanul123"; // 인코딩 전 메서드
		String encdoePassword1; // 인코딩된 메서드
		String encdoePassword2; // 똑같은 비밀번호 데이터를 encdoe()메서드를 사용했을 때 동일한 인코딩된 값이 나오는지 확인하기 위해 추가

		encdoePassword1 = pwEncoder.encode(rawPassword);
		encdoePassword2 = pwEncoder.encode(rawPassword);

		// 인코딩된 패스워드 출력
		System.out.println("encdoePassword1 : " + encdoePassword1);
		System.out.println(" encdoePassword2 : " + encdoePassword2);

		String truePassowrd = "hanul123";
		String falsePassword = "asdfjlasf";

		System.out.println("truePassword verify : " + pwEncoder.matches(truePassowrd, encdoePassword1));
		System.out.println("falsePassword verify : " + pwEncoder.matches(falsePassword, encdoePassword1));

	}

}