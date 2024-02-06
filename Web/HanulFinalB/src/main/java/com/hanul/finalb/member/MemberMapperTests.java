package com.hanul.finalb.member;

import org.springframework.beans.factory.annotation.Autowired;


import com.hanul.finalb.member.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	@Autowired
	private MemberMapper membermapper;			//MemberMapper.java �������̽� ������ ����
	
	
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.getUSER_ID();			//ȸ�� id
		member.getUSER_PW();			//ȸ�� ��й�ȣ
		member.getNAME();		//ȸ�� �̸�
		member.setMemberMail("test");		//ȸ�� ����
		member.setMemberAddr1("test");		//ȸ�� �����ȣ
		member.setMemberAddr2("test");		//ȸ�� �ּ�
		member.setMemberAddr3("test");		//ȸ�� ���ּ�
		
		membermapper.memberJoin(member);			//���� �޼��� ����
		
	}

}
