package com.hanul.finalb.app;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppSensorService {

	@Autowired
	SqlSession sql;

	// 유저 아이디로 유저의 센서 종류 정보를 가져오는 메소드
	public List<UserSensorVO> userSensorList(String user_id) {

		return sql.selectList("sensor.user_sensor_list", user_id);
	}

	// sensor_id로 cctv 주소를 반환
	public String getCCTVURL(int sensor_id) {
		return sql.selectOne("sensor.cctvurl", sensor_id);
	}

	// sensor_id로 측정 데이터 반환
	public List<DatasVO> getDatas(int sensor_id) {
		List<DatasVO> list = sql.selectList("sensor.datas", sensor_id);
		return list;
	}

}
