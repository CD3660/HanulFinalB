package com.hanul.finalb.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private SqlSession sql;

	public void memberJoin(MemberVO member) {

		sql.insert("member.register", member);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//테스트용 임시 로그인처리 // 회원정보조회
	public MemberVO member_info(String user_id) {
		return sql.selectOne("member.info", user_id);
	}
	
	
	
}
