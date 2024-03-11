package com.hanul.finalb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	MemberService memService;
	
	@RequestMapping("/login")
	public String login(MemberVO vo) {
		System.out.println("연결");
		return new Gson().toJson(memService.member_login(vo), MemberVO.class);
	}
}
