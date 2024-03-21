package com.hanul.finalb.app;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatasVO {
	private int sensor_id;
	private float sensor_data1, sensor_data2;
	private String id, user_id, data_name1, data_name2, formattedTime;
	private Date time;
}
