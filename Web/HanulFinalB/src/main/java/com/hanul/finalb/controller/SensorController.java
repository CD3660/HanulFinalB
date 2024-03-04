package com.hanul.finalb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		System.out.println("데이터 입력");
		service.insertSensorData(vo);
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
	
	@PostMapping("/saveVideo")
    public String saveVideo(MultipartFile videoFile) {
        String filePath = "path/to/save/" + new Date().getTime() + ".mp4"; // 저장할 파일 경로 및 이름
        try {
            // MultipartFile로부터 파일을 저장
            byte[] bytes = videoFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(bytes);
            stream.close();
            return "Video saved successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to save video";
        }
    }

}
