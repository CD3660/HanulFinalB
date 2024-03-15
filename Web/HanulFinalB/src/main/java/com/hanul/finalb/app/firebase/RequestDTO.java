package com.hanul.finalb.app.firebase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
	private String title;
    private String body;
    private String targetToken;
    private String click_action;
}
