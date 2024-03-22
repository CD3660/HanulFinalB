package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.common.Common;
import com.hanul.finalb.common.PageVO;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.member.PaymentVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	BCryptPasswordEncoder pwEncoder;

	@Autowired
	private MemberService service;

	@ResponseBody
	@RequestMapping("/idCheck")
	public Map<String, Object> idCheck(String id) {

		Map<String, Object> result = new HashMap<String, Object>();
		// 디비에서 아이디 갯수 확인하는 변수
		int cnt = service.idCheck(id);

		result.put("cnt", cnt);

		return result;
	}

	// 코멘트 만들면서 임시로 만듬. 연결된 코드들 수정 마무리 바람
	// 로그인 화면 요청
	@RequestMapping(value = "/login")
	public String login(Locale locale, Model model, HttpSession session, String url, PageVO page, String qna_id) {
		// 방명록 정보화면에서 서브밋된 경우
		if (url != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("url", url);
			map.put("page", page);
			map.put("user_id", qna_id);
			session.setAttribute("redirect", map); // redirect에 필요한 정보를 세션에 담기
		}

		session.setAttribute("category", "login");

		return "default/member/login";
	}

	@RequestMapping("/joinView")
	public String join(HttpSession session ,Model model) {
		session.setAttribute("category", "signin");
		return "member/join";
	}

	@RequestMapping("/joinAction")
	public String joinpass(MemberVO vo, MultipartFile file) throws GeneralSecurityException, IOException {
		if (!file.isEmpty()) {
			String id = comm.fileUpload(file);
			vo.setProfile(comm.fileURL(id));
		}
		
		String encodingPw = pwEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(encodingPw);

		service.memberJoin(vo);

		return "redirect:login";

	}

	@ResponseBody
	@RequestMapping("/loginAction")
	public Map<String, Object> loginpass(MemberVO member, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// 프론트에서 사용자가 입력한 비밀번호
		String encodingPw = pwEncoder.encode(member.getUser_pw());

		// 디비에서 가져온거
		MemberVO checkInfo = service.login(member.getUser_id());
		if(checkInfo == null) {
			result.put("code", "-1");
			return result;
		}
		if (pwEncoder.matches(member.getUser_pw(), checkInfo.getUser_pw())) {
			// 성공시
			result.put("code", "0");
			// 회원 정보 풀버전 세션에 넣기
			session.setAttribute("loginInfo", service.memberInfo(member.getUser_id()));
		} else {
			// 실패시
			result.put("code", "-1");
		}

		// service.memberJoin(member);

		return result;

	}
	
	@RequestMapping("/resign")
	public String resign(HttpSession session) throws GeneralSecurityException, IOException {
		MemberVO vo = (MemberVO) session.getAttribute("loginInfo");
		if(vo==null) {
			return "shop/redirect";
		}
		if(service.memberResign(vo.getUser_id())==1) {
			if(vo.getProfile()!=null) {
				comm.fileDelete(comm.fileId(vo.getProfile()));
			}
			session.removeAttribute("loginInfo");
		}
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}

	@RequestMapping("/mypage")
	public String MyPage(HttpSession session, Model model) {
		MemberVO vo = (MemberVO) session.getAttribute("loginInfo");
		session.setAttribute("loginInfo", service.memberInfo(vo.getUser_id()));
		return "member/mypage";
	}
	@Autowired
	Common comm;
	@RequestMapping("/memberUpdate")
	public String memberUpdate(MemberVO vo, MultipartFile file, boolean maintain) throws GeneralSecurityException, IOException {
		String remove_img = service.memberInfo(vo.getUser_id()).getProfile();
		if (!file.isEmpty()) {
			String id = comm.fileUpload(file);
			vo.setProfile(comm.fileURL(id));
		}
		if (!maintain) {
			if (remove_img != null) {
				comm.fileDelete(comm.fileId(remove_img));
			}
		}
		service.updateMember(vo);
		return "redirect:mypage";
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
	
	@RequestMapping("/paymentList")
	public String paymentList(HttpSession session, Model model) {
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		if(loginInfo == null) {
			model.addAttribute("url", "member/login");
			return "shop/redirect";
		}
		List<PaymentVO> list = service.getPaymentList(loginInfo.getUser_id());
		model.addAttribute("list", list);
		
		return "member/paymentList";
	}
	@RequestMapping("/paymentInfo")
	public String paymentInfo(HttpSession session, Model model, String imp_uid) {
		
		PaymentVO vo = service.getPaymentInfo(imp_uid);
		model.addAttribute("vo", vo);
		model.addAttribute("list", vo.getList());
		
		return "member/paymentInfo";
	}
}