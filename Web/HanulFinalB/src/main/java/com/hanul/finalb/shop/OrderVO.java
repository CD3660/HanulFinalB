package com.hanul.finalb.shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	private int id, prod_id, payment_id, price, ea;
	private String user_id, prod_name, prod_img, order_id;
}
