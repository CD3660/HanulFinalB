package com.hanul.finalb.shop;

import java.sql.Date;

import com.hanul.finalb.product.ProductVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	private int id, prod_id, ea, price;
	private Date order_date;
	private String address, phone, reciever, request_msg, paid;
	private ProductVO prod;
}
