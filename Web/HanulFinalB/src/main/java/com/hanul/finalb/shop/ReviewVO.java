package com.hanul.finalb.shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVO {
    private int review_id, prod_id, rate;
    private String content, user_id;
}
