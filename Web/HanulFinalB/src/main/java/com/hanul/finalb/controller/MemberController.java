package com.hanul.finalb.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping("/join")
	public String join(Model model) {

		return "member/join";
	}

	@RequestMapping(path = "/join", method = RequestMethod.POST)

	public String joinpass(MemberVO member) throws Exception {
		//아이디 중복체크
		
		member.setUser_pw(pwEncoder.encode(member.getUser_pw()));
		service.memberJoin(member);
		return "redirect:/member/login";
	}
	/* 로그인 */
	@RequestMapping(value="/loginPOST", method=RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr, MemberService memberservice) throws Exception {

		HttpSession session = request.getSession();
        String rawPw = "";
        String encodePw = "";
    
        MemberVO lvo = memberservice.memberLogin(member);    // 제출한아이디와 일치하는 아이디 있는지 
        
        if(lvo != null) {            // 일치하는 아이디 존재시
            
            rawPw = member.getUser_pw();        // 사용자가 제출한 비밀번호
            encodePw = lvo.getUser_pw();        // 데이터베이스에 저장한 인코딩된 비밀번호
            
            if(pwEncoder.matches(rawPw, encodePw)) {        // 비밀번호 일치여부 판단
                
                lvo.setUser_pw("");                    // 인코딩된 비밀번호 정보 지움
                session.setAttribute("member", lvo);     // session에 사용자의 정보 저장
                return "redirect:/";        // 메인페이지 이동
                
                
            } else {
 
                rttr.addFlashAttribute("result", 0);            
                return "redirect:/member/login";    // 로그인 페이지로 이동
                
            }
            
        } else {                    // 일치하는 아이디가 존재하지 않을 시 (로그인 실패)
            
            rttr.addFlashAttribute("result", 0);            
            return "redirect:/member/login";    // 로그인 페이지로 이동
        }
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
