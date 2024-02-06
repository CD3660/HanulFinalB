package com.hanul.finalb.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper membermapper;

	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		membermapper.memberJoin(member);
		
	}
}
