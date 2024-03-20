package com.hanul.mysmarthome.common;

import android.util.Log;

public class Common {

    public String getGlideUrl(String url){
        url.replace("https://drive.google.com/thumbnail?sz=w640&id=", "");
        Log.d("TAG", "getGlideUrl: "+url);
        String newUrl = new StringBuilder().append("https://lh3.googleusercontent.com/d/").append(url).append("=w640?authuser=0").toString();
        Log.d("TAG", "getGlideUrl: "+newUrl);
        return newUrl;
    }
}
