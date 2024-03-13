package com.hanul.mysmarthome.showdata;

import java.util.Date;

public class DatasVO {
	private int sensor_id ,sensor_data1, sensor_data2;
	private String id, user_id, data_name1, data_name2, formattedTime;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private Date time;

	public int getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}

	public int getSensor_data1() {
		return sensor_data1;
	}

	public void setSensor_data1(int sensor_data1) {
		this.sensor_data1 = sensor_data1;
	}

	public int getSensor_data2() {
		return sensor_data2;
	}

	public void setSensor_data2(int sensor_data2) {
		this.sensor_data2 = sensor_data2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getData_name1() {
		return data_name1;
	}

	public void setData_name1(String data_name1) {
		this.data_name1 = data_name1;
	}

	public String getData_name2() {
		return data_name2;
	}

	public void setData_name2(String data_name2) {
		this.data_name2 = data_name2;
	}

	public String getFormattedTime() {
		return formattedTime;
	}

	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
	}
}
