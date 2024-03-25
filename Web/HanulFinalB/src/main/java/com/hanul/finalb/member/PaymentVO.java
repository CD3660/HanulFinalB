package com.hanul.finalb.member;


import java.util.Date;
import java.util.List;

import com.hanul.finalb.shop.OrderVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentVO {
	private int amount, count;
	private String prod_name ,reciever, address, phone, imp_uid, request_msg, user_id, post;
	private Date payment_time;
	private List<OrderVO> list;
}
