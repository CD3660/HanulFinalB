package com.hanul.finalb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;

@RestController
@RequestMapping(value="/app",produces="application/text;charset=utf-8")
public class AppController {
	
	@Autowired
	MemberService memService;
	
	@RequestMapping("/login")
	public String login(MemberVO vo) {
		return new Gson().toJson(memService.member_login(vo), MemberVO.class);
	}
}
