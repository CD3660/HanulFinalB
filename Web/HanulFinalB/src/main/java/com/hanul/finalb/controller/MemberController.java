package com.hanul.finalb.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.finalb.common.PageVO;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	BCryptPasswordEncoder pwEncoder;

	@Autowired
	private MemberService service;

	@PostMapping("/idCheck")
	@ResponseBody
	public Map<String, Object> idCheck(@RequestBody String id) {

		Map<String, Object> result = new HashMap<String, Object>();
		// 디비에서 아이디 갯수 확인하는 변수
		int cnt = service.idCheck(id);

		result.put("cnt", cnt);

		return result;
	}
	
	
	

	// 코멘트 만들면서 임시로 만듬. 연결된 코드들 수정 마무리 바람
	//로그인 화면 요청
	@RequestMapping(value = "/login")
	public String login(Locale locale, Model model
						, HttpSession session, String url, PageVO page, String qna_id) {
		//방명록 정보화면에서 서브밋된 경우
		if( url != null ) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("url", url);
			map.put("page", page);
			map.put("user_id", qna_id);
			session.setAttribute("redirect", map); //redirect에 필요한 정보를 세션에 담기
		}
		
		
		
		
		session.setAttribute("category", "login");
		
		
		return "default/member/login";
	}
	
	


	@RequestMapping(value = "/joinView", method = RequestMethod.GET)
	public String join(Model model) {

		return "member/join";
	}
	
	
	@RequestMapping("/joinAction")
	public String joinpass(MemberVO member) {

		String encodingPw = pwEncoder.encode(member.getUser_pw());
		member.setUser_pw(encodingPw);
		
		service.memberJoin(member);

		return "redirect:member/login";

	}
	// 아이디 찾기 폼
		@RequestMapping(value = "/find_id_form")

		public String find_id_form() {

		

			return "member/find_id_form";
		}

	@ResponseBody
	@RequestMapping("/loginAction")
	public Map<String, Object> loginpass(MemberVO member, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		// 프론트에서 사용자가 입력한 비밀번호
		String encodingPw = pwEncoder.encode(member.getUser_pw());
		
		// 디비에서 가져온거
		MemberVO checkInfo = service.login(member.getUser_id());
		
		if(pwEncoder.matches(member.getUser_pw(), checkInfo.getUser_pw())) {
			//성공시
			result.put("code", "0");
			//회원 정보 풀버전 세션에 넣기
			session.setAttribute("loginInfo", service.memberInfo(member.getUser_id()));
		}else {
			//실패시
			result.put("code", "-1");
		}
		
		
		//service.memberJoin(member);

		return result;

	}

	@RequestMapping(value = "/secuTest", method = RequestMethod.GET)
	public void secuTest() {

		String rawPassword = "hanul123"; // ���ڵ� �� �޼���
		String encdoePassword1; // ���ڵ��� �޼���
		String encdoePassword2; // �Ȱ��� ��й�ȣ �����͸� encdoe()�޼��带 ������� �� ������ ���ڵ��� ���� ��������
								// Ȯ���ϱ� ���� �߰�

		encdoePassword1 = pwEncoder.encode(rawPassword);
		encdoePassword2 = pwEncoder.encode(rawPassword);

		// ���ڵ��� �н����� ���
		System.out.println("encdoePassword1 : " + encdoePassword1);
		System.out.println(" encdoePassword2 : " + encdoePassword2);

		String truePassowrd = "hanul123";
		String falsePassword = "asdfjlasf";

		System.out.println("truePassword verify : " + pwEncoder.matches(truePassowrd, encdoePassword1));
		System.out.println("falsePassword verify : " + pwEncoder.matches(falsePassword, encdoePassword1));

	}
	@ResponseBody
	@PostMapping("/findId")
    public HashMap<String, String> findIdByEmail(String email) {
		System.out.println("여기");
        String user_id = service.findIdByEmail(email);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        
        return map;
    }
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}
	@RequestMapping("/mypage")
	public String MyPage(Model model) {
		
		return "member/mypage";
	}
	@RequestMapping("/memberUpdate")
	public String memberUpdate(@ModelAttribute MemberVO vo) {
		service.updateMember(vo);
		return "member/memberUpdate";
	}
	@RequestMapping("/sidemenu")
	public String sidemenu(HttpSession session) {
		
		return "/member/sidemenu";
	}
	@RequestMapping("/changePw")
	public String changePw(Model model) {
		
		return "member/changePw";

}
	@RequestMapping("/secession")
	public String secession(Model model) {
		
		return "member/secession";
}
}