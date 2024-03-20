package com.hanul.finalb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hanul.finalb.app.firebase.FirebaseCloudMessageService;
import com.hanul.finalb.sensor.SensorService;
import com.hanul.finalb.sensor.SensorVO;

@Controller
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private SensorService service;
	@Autowired
	FirebaseCloudMessageService fcmService;
	
	@ResponseBody
	@RequestMapping("/data_in")
	public String sensor(SensorVO vo) {
		System.out.println("데이터 입력");
		service.insertSensorData(vo);
		service.alert(vo);
		return "connect success";
	}
	@ResponseBody
	@RequestMapping("/test_alert")
	public String test_alert(SensorVO vo) {
		service.alert(vo);
		return "connect success";
	}
	
	@RequestMapping("/chart")
	public String chart(SensorVO vo) {
		return "default/sensor/chart";
	}

	@RequestMapping("/sensor")
	public String sensor() {

		return "iot/sensor";
	}
	@Autowired
	private SqlSession sql;
	@ResponseBody
	@RequestMapping("/gas_alert/{user_id}")
	public String gas_alert(@PathVariable String user_id,int code) {
		try {
			fcmService.sendMessageTo(sql.selectOne("member.getToken",user_id), "가스센서 경고", "가스 누출이 감지되었습니다. 확인이 필요합니다.", "none");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user_id);
			map.put("sensor_id", 4);
			map.put("title", "가스센서 경고");
			map.put("body", "가스 누출이 감지되었습니다. 확인이 필요합니다.");
			map.put("data_value", code);
			sql.insert("sensor.insert_history", map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "gas alert";
	}
	@ResponseBody
	@RequestMapping("/led")
	public String led(String led_mode) {
		System.out.println("IoT라이트 조작");
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
