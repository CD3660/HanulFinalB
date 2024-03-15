package com.hanul.finalb.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/idCheck")
	@ResponseBody
	public Map<String, Object> idCheck(@RequestBody String id) {

		Map<String, Object> result = new HashMap<String, Object>();
		// 디비에서 아이디 갯수 확인하는 변수
		int cnt = service.idCheck(id);

		result.put("cnt", cnt);

		return result;
	}

	/* ��ǰ �Ұ� ������ ��ȯ�� ���� ������ */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "member/login";
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

		return "redirect:/member/login";

	}
	// 아이디 찾기 폼
		@RequestMapping(value = "/find_id_form")
		public String find_id_form() throws Exception{
			return "/member/find_id_form";
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
	@PostMapping("/findId")
    public ResponseEntity<String> findIdByEmail(@RequestBody String email) {
        String name = service.findIdByEmail(email);

        if (name != null) {
            return new ResponseEntity<>(name, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("아이디를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            
        }
        
	
    }
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}
	@RequestMapping("/myPage")
	public String myPage(HttpSession session) {
		
		return "/member/myPage";
	}

}
