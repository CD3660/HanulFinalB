package com.hanul.finalb.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		return sql.selectOne("login", id);
	}
	@Autowired
	 private MemberDAO memberDAO;

	    public String findIdByEmail(String email) {
	        return memberDAO.findIdByEmail(email);
	    }
	
	
	
	
	
	
	
	
	
	
	//테스트용 임시 로그인처리 // 회원정보조회
	public MemberVO member_info(String user_id) {
		return sql.selectOne("member.info", user_id);
	}

}
