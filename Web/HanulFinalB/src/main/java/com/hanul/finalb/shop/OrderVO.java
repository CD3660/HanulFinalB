package com.hanul.finalb.shop;



import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	private int id, prod_id, ea, price;
	private Date order_date;
	private String address, tel, reciever, request_msg, paid;
}
