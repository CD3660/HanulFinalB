package com.hanul.finalb.sensor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorVO {
	private int dust, gas, fire, temp, humid;
	private String user_id;
}
