package com.hanul.finalb.sensor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorVO {
	private float dust, gas, fire, temp, humid;
	private int dust_sensor_id, gas_sensor_id, fire_sensor_id, temp_sensor_id;
	private String user_id;
}
