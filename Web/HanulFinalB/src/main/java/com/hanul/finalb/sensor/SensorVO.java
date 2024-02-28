package com.hanul.finalb.sensor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorVO {
	private float gas, dust,  temp, humid;
	private int fire;
	private String user_id;
}
