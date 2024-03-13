package com.hanul.mysmarthome;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class NonTouchableWebView extends WebView {

    public NonTouchableWebView(Context context) {
        super(context);
    }

    public NonTouchableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonTouchableWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // 터치 이벤트를 처리하지 않도록 재정의
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 터치 이벤트를 소비하지 않음 (터치를 무시함)
        return false;
    }
}