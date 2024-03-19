package com.hanul.finalb.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.finalb.app.AppSensorService;
import com.hanul.finalb.app.UserSensorVO;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.qna.QnaService;
import com.hanul.finalb.qna.QnaVO;

@RestController
@RequestMapping(value="/app",produces="application/text;charset=utf-8")
public class AppController {
	
	@Autowired
	MemberService memService;
	
	@Autowired
	AppSensorService appService;
	
	@Autowired
	QnaService qnaService;
	
	@RequestMapping("/login")
	public String login(MemberVO vo) {
		return new Gson().toJson(memService.member_login(vo), MemberVO.class);
	}
	
	@RequestMapping("/updateSensor")
	public String updateSensor(MemberVO vo) {
		return new Gson().toJson(appService.userSensorList(vo.getUser_id()), new TypeToken<List<UserSensorVO>>(){}.getType());
	}
	
	@RequestMapping("/getCctvUrl")
	public String getCctvUrl(int sensor_id) {
		return appService.getCCTVURL(sensor_id);
	}




	@RequestMapping("/qna")
	public String qnaList() {
		
		// QnaVO 객체들의 리스트를 가져옴
		ArrayList<QnaVO> list = (ArrayList<QnaVO>)qnaService.appQnaList();

	    // 생성된 Type을 사용하여 리스트를 JSON 문자열로 변환
		System.out.println(">> "+ new Gson().toJson(list));
	    return new Gson().toJson(list);
		
	}
	


}
