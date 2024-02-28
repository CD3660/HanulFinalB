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

	/* �뜝�룞�삕�뭹 �뜝��怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�솚�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "member/login";
	}

	@RequestMapping("/join")
	public String join(Model model) {

		return "member/join";
	}
	@RequestMapping("/userSearch")
	public String userSearch(Model model) {

		return "member/userSearch";
	}
	@RequestMapping(path = "/join", method = RequestMethod.POST)

	public String joinpass(MemberVO member) throws Exception {
		//�븘�씠�뵒 以묐났泥댄겕
		
		member.setUser_pw(pwEncoder.encode(member.getUser_pw()));
		service.memberJoin(member);
		return "redirect:/member/login";
	}
	/* 濡쒓렇�씤 */
	@RequestMapping(value="/loginPOST", method=RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) {

		HttpSession session = request.getSession();
        String rawPw = "";
        String encodePw = "";
    
        MemberVO lvo = service.memberLogin(member);    // �젣異쒗븳�븘�씠�뵒�� �씪移섑븯�뒗 �븘�씠�뵒 �엳�뒗吏� 
        
        if(lvo != null) {            // �씪移섑븯�뒗 �븘�씠�뵒 議댁옱�떆
            
            rawPw = member.getUser_pw();        // �궗�슜�옄媛� �젣異쒗븳 鍮꾨�踰덊샇
            encodePw = lvo.getUser_pw();        // �뜲�씠�꽣踰좎씠�뒪�뿉 ���옣�븳 �씤肄붾뵫�맂 鍮꾨�踰덊샇
            
            if(pwEncoder.matches(rawPw, encodePw)) {        // 鍮꾨�踰덊샇 �씪移섏뿬遺� �뙋�떒
                
                lvo.setUser_pw("");                    // �씤肄붾뵫�맂 鍮꾨�踰덊샇 �젙蹂� 吏���
                session.setAttribute("loginInfo", lvo);     // session�뿉 �궗�슜�옄�쓽 �젙蹂� ���옣
                return "redirect:/";        // 硫붿씤�럹�씠吏� �씠�룞
                
                
            } else {
 
                rttr.addFlashAttribute("result", 0);            
                return "redirect:/member/login";    // 濡쒓렇�씤 �럹�씠吏�濡� �씠�룞
                
            }
            
        } else {                    // �씪移섑븯�뒗 �븘�씠�뵒媛� 議댁옱�븯吏� �븡�쓣 �떆 (濡쒓렇�씤 �떎�뙣)
            
            rttr.addFlashAttribute("result", 0);            
            return "redirect:/member/login";    // 濡쒓렇�씤 �럹�씠吏�濡� �씠�룞
        }
        }
	

   

	

	@RequestMapping(value = "/secuTest", method = RequestMethod.GET)
	public void secuTest() {

		String rawPassword = "hanul123"; // �씤肄붾뵫 �쟾 硫붿꽌�뱶
		String encdoePassword1; // �씤肄붾뵫�맂 硫붿꽌�뱶
		String encdoePassword2; // �삊媛숈� 鍮꾨�踰덊샇 �뜲�씠�꽣瑜� encdoe()硫붿꽌�뱶瑜� �궗�슜�뻽�쓣 �븣 �룞�씪�븳 �씤肄붾뵫�맂 媛믪씠 �굹�삤�뒗吏� �솗�씤�븯湲� �쐞�빐 異붽�

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
