package com.hanul.finalb.sensor;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.common.Common;

@Service
public class SensorService {
	
	@Autowired
	private SqlSession sql;
	
	@Autowired
	private Common comm;
	
	public int insertSensorData(SensorVO vo) {
		return sql.insert("sensor.insert", vo);
	}
	public String ledControl(String led_mode) {
		String url = "http://192.168.0.30:8000/led?led_mode="+led_mode;
		
		return comm.requestAPI(url);
	}
}
