package com.hanul.finalb.shop;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVO {
    private int review_id, prod_id, rate;
    private String content, user_id, profile;
    private Date review_date;
}
