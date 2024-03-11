package com.hanul.finalb.shop;

import java.sql.Date;

import com.hanul.finalb.product.ProductVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentVO {
	private int payment_id, amount;
	private Date payment_time;
	private String post, address, phone, reciever, request_msg, imp_uid, merchant_uid, user_id, order_id;
	
}
