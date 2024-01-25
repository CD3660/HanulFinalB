package com.hanul.finalb.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
	private String  prod_name, detail, prod_img;
	private int prod_id, price, stock;
}
