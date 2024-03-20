package com.hanul.finalb.sensor;

import java.io.IOException;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.app.firebase.FirebaseCloudMessageService;
import com.hanul.finalb.common.Common;

@Service
public class SensorService {

	@Autowired
	private SqlSession sql;

	@Autowired
	FirebaseCloudMessageService fcmService;

	@Autowired
	private Common comm;

	public int insertSensorData(SensorVO vo) {

		return sql.insert("sensor.insert", vo);
	}

	public String ledControl(String led_mode) {
		String url = "http://192.168.0.30:8000/led?led_mode=" + led_mode;

		return comm.requestAPI(url);
	}

	public void alert(SensorVO vo) {
		String title = "";
		String body = "";
		String action = "none";
		if (vo.getFire() != 3) {
			if (vo.getFire() == 2) {
				// 화재 경고 낮음
				title = "화재감지센서 경고";
				body = "화재 경고 낮음. 확인이 필요합니다.";
			}
			if (vo.getFire() == 1) {
				// 화재 경고 보통
				title = "화재감지센서 경고";
				body = "화재 경고 보통. 빠른 확인이 필요합니다.";
			}
			if (vo.getFire() == 0) {
				// 화재 경고 높음 - 119 알림까지 연동
				title = "화재감지센서 경고";
				body = "화재 경고 높음. 119 신고가 필요합니다.";
				action = "119";
			}
			try {
				fcmService.sendMessageTo(sql.selectOne("member.getToken", vo.getUser_id()), title, body, action);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", vo.getUser_id());
				map.put("sensor_id", vo.getFire_sensor_id());
				map.put("title", title);
				map.put("body", body);
				map.put("data_value", vo.getFire());
				sql.insert("sensor.insert_history", map);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
