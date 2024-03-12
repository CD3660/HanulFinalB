package com.hanul.mysmarthome.cctv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityCctvBinding;

public class CCTVActivity extends AppCompatActivity {
    ActivityCctvBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCctvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new CommonConn(this, "getCctvUrl?sensor_id="+getIntent().getIntExtra("sensor_id",14))
                .onExcute((isResult, data) -> {
                    if(isResult){
                        binding.webview.loadUrl(data);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}