package com.hanul.finalb.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.finalb.app.AppSensorService;
import com.hanul.finalb.app.DatasVO;
import com.hanul.finalb.app.UserSensorVO;
import com.hanul.finalb.app.firebase.FirebaseCloudMessageService;
import com.hanul.finalb.app.firebase.RequestDTO;
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
	FirebaseCloudMessageService fcmService;
	
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
	@RequestMapping("/datas")
	public String datas(int sensor_id) {
		return new Gson().toJson(appService.getDatas(sensor_id), new TypeToken<List<DatasVO>>(){}.getType());
	}
	@RequestMapping("/light_on")
	public String light_on() {
		
		return "redirect:sensor/led?led_mode=ON";
	}
	@RequestMapping("/light_off")
	public String light_off() {
		
		return "redirect:sensor/led?led_mode=OFF";
	}
		
	//푸시 알림 테스트용
	@RequestMapping("/fcm")
	public ResponseEntity pushMessage(RequestDTO dto) throws IOException {
		fcmService.sendMessageTo(dto.getTargetToken(), dto.getTitle(), dto.getBody(), dto.getClick_action());
		
		return ResponseEntity.ok().build();
	}




	@RequestMapping("/qna")
	public String qnaList() {
//		return new Gson().toJson(qnaService.appQnaList(), QnaVO.class);
		
		// QnaVO 객체들의 리스트를 가져옴*********************************
		ArrayList<QnaVO> list = (ArrayList<QnaVO>)qnaService.appQnaList();

	    // ArrayList<QnaVO>에 대한 Type 객체를 생성
	   // Type listType = new TypeToken<ArrayList<QnaVO>>() {}.getType();

	    // 생성된 Type을 사용하여 리스트를 JSON 문자열로 변환
//	    return new Gson().toJson(list, listType);
		System.out.println(">> "+ new Gson().toJson(list));
	    return new Gson().toJson(list);
		
	}
	


}
