package com.hanul.finalb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.finalb.app.AppSensorService;
import com.hanul.finalb.app.DatasVO;
import com.hanul.finalb.app.UserSensorVO;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;

@RestController
@RequestMapping(value="/app",produces="application/text;charset=utf-8")
public class AppController {
	
	@Autowired
	MemberService memService;
	
	@Autowired
	AppSensorService appService;
	
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
}
