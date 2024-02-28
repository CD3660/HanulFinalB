package com.hanul.finalb.member;

import org.springframework.beans.factory.annotation.Autowired;


import com.hanul.finalb.member.MemberVO;


public class MemberMapperTests {

	@Autowired
	private MemberService service;			//MemberMapper.java 인터페이스 의존성 주입
	
	
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setUser_id("test");			//회원 id
		member.setUser_pw("test");			//회원 비밀번호
		member.setName("test");		//회원 이름
		member.setEmail("test");		//회원 메일
		member.setAddress("test");		//회원 우편번호
		member.setAddress2("test");		//회원 주소
		member.setPhone("test");		//회원 번호
		
		service.memberJoin(member);			//쿼리 메서드 실행
		
	}

	
	
	
	
}
