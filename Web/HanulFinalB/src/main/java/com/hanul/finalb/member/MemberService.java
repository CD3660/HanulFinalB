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

	public MemberVO member_info(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public MemberVO memberLogin(MemberVO member){
		
        return sql.selectOne("member.loginPOST", member);
    }
}
