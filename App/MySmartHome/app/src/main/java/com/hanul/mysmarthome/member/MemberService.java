package com.hanul.mysmarthome.member;

public class MemberService {

    public MemberVO replaceImgURL(MemberVO vo){
        String img_id = vo.getProfile().replace("https://drive.google.com/thumbnail?sz=w640&id=","");
        img_id = img_id.replace("\"", "");
        vo.setProfile("https://lh3.googleusercontent.com/d/"+img_id+"=w640?authuser=1");
        return vo;
    }
}
