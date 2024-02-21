package com.hanul.finalb.member;

import org.springframework.beans.factory.annotation.Autowired;


import com.hanul.finalb.member.MemberVO;


public class MemberMapperTests {

	@Autowired
	private MemberService service;			//MemberMapper.java �������̽� ������ ����
	
	
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setUser_id("test");			//ȸ�� id
		member.setUser_pw("test");			//ȸ�� ��й�ȣ
		member.setName("test");		//ȸ�� �̸�
		member.setEmail("test");		//ȸ�� ����
		member.setAddress("test");		//ȸ�� �����ȣ
		member.setAddress2("test");		//ȸ�� �ּ�
		member.setPhone("test");		//ȸ�� ��ȣ
		
		service.memberJoin(member);			//���� �޼��� ����
		
	}

	
	
	
	
}
