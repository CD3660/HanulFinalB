package com.hanul.finalb.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private SqlSession sql;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;

	public void memberJoin(MemberVO member) {

		sql.insert("member.register", member);

	}

	// 안드로이드 로그인 처리
	public MemberVO member_login(MemberVO vo) {
		MemberVO info = sql.selectOne("member.info", vo.getUser_id());
		if(pwEncoder.matches(vo.getUser_pw(), info.getUser_pw())) {
			String tokenUser = sql.selectOne("member.compareToken", vo);
			if(tokenUser != null) {
				if(!tokenUser.equals(vo.getUser_id())) {
					// 토큰이 이미 등록 되었고, 현재 로그인한 유저와 db의 연동 유저가 같은 지 않은 경우 유저 아이디 변경(같은 기기로 로그인 유저만 바뀐경우)
					sql.update("member.updateToken", vo); 
				}
			} else {
				// 토큰이 이미 등록된 것이 아닌 경우 현재 로그인 유저 아이디로 토큰 정보 등록
				sql.insert("member.saveToken", vo);
			}
			return info;
		} else {
			return null;
		}
	}

	// 테스트용 임시 로그인처리 // 회원정보조회
	
	public int idCheck(String id) {

		return sql.selectOne("idCheck", id);
	}

	public MemberVO login(String id) {

		return sql.selectOne("member.login", id);
	}

	@Autowired
	private MemberDAO memberDAO;

	public String findIdByEmail(String email) {
		return memberDAO.findIdByEmail(email);
	}

	public MemberVO memberInfo(String user_id) {
		return sql.selectOne("member.info", user_id);

	}
	
	public static MemberVO getCurrentMember() {
        // Spring Security를 이용하여 현재 로그인한 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 여기서는 임의의 방법으로 현재 사용자의 정보를 반환하도록 작성합니다.
        // 실제로는 해당 로직을 프로젝트의 요구사항에 맞게 변경해야 합니다.
        MemberVO member = new MemberVO();
        member.setUser_id(userDetails.getUsername());
        // 다른 회원 정보를 가져와서 설정하는 로직을 추가합니다.
        return member;
}
}
