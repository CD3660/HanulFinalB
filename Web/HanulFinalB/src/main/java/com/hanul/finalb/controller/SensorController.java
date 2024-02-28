package com.hanul.finalb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.finalb.sensor.SensorService;
import com.hanul.finalb.sensor.SensorVO;

@Controller
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private SensorService service;

	@ResponseBody
	@RequestMapping("/data_in")
	public String sensor(SensorVO vo) {
		System.out.println("dust: " + vo.getDust());
		System.out.println("fire: " + vo.getFire());
		System.out.println("gas: " + vo.getGas());
		System.out.println("humid: " + vo.getHumid());
		System.out.println("temp: " + vo.getTemp());
		System.out.println("user_id: " + vo.getUser_id());
		service.insertSensorData(vo);
		service.ledControl("blink");

		return "connect success";
	}

	@RequestMapping("/sensor")
	public String sensor() {

		return "iot/sensor";
	}

	@ResponseBody
	@RequestMapping("/led")
	public String led(String led_mode) {

		return service.ledControl(led_mode);
	}

}
