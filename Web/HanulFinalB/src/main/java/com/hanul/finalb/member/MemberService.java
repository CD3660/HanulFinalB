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

	public MemberVO memberLogin(MemberVO member) {
		String username = member.getUser_id();
	    String password = member.getUser_pw();

	    // 사용자 정보 확인 로직을 추가
	    if (isValidCredentials(username, password)) {
	        // 로그인 성공 시, 해당 멤버의 정보를 반환 또는 세션에 저장 등의 작업을 수행
	        MemberVO loggedInMember = getMemberByUsername(username);
	        return loggedInMember;
	    } else {
	        // 로그인 실패 시, 보통 null이나 특정 오류 상태 등을 반환
	        return null;
	    }
	}
	private boolean isValidCredentials(String username, String password) {
	    // TODO: 실제 데이터베이스나 다른 저장소에서 사용자 정보를 확인하는 로직을 구현
	    // 예를 들면, 회원 정보를 DB에서 조회하여 사용자 이름과 비밀번호를 확인
	    // 이 예시에서는 간단하게 하드코딩하여 'user1'의 비밀번호가 'password1'일 때만 허용하도록 함
	    return "user1".equals(username) && "password1".equals(password);
	}

	// 사용자 이름을 기반으로 멤버 정보를 가져오는 메소드
	private MemberVO getMemberByUsername(String username) {
	    // TODO: 실제 데이터베이스나 다른 저장소에서 사용자 정보를 조회하여 MemberVO를 반환
	    // 여기에서는 가상의 MemberVO를 생성하여 반환하는 예시 코드를 작성
	    MemberVO member = new MemberVO();
	    member.setUser_id(username);
	    // 멤버의 다른 정보들을 설정
	    return member;
	}
}
