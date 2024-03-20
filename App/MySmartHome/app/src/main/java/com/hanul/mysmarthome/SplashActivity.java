package com.hanul.mysmarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivitySplashBinding;
import com.hanul.mysmarthome.login.LoginActivity;
import com.hanul.mysmarthome.member.MemberVO;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    public class Data {
        private String createdAt;
        private String message;
        private String click_action;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle notice_data = getIntent().getExtras();
        if(notice_data != null){
            String action = notice_data.getString("click_action");
            if("119".equals(action)) {
                Intent intent_dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"));
                startActivity(intent_dial);
                finish();
            }
        }



        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.loading_spinner)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_background)
                .into(binding.spinner);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
            String user_id = sharedPreferences.getString("user_id", "");
            String user_pw = sharedPreferences.getString("user_pw", "");
            if (user_id.equals("")) {
                Log.d("login", "로그인 정보 없음");
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {

                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d("TAG", "onCreate: "+token);
                    CommonConn conn = new CommonConn(this, "login")
                            .addParamMap("user_id", user_id)
                            .addParamMap("token",token);
                    if (user_pw.equals("")) {
                        conn.addParamMap("social", "Y");
                    } else {
                        conn.addParamMap("user_pw", user_pw);
                    }
                    ;
                    conn.onExcute((isResult, data) -> {
                        if(!isResult){
                            Toast.makeText(this, "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                        MemberVO vo = new Gson().fromJson(data, MemberVO.class);
                        if (vo == null) {
                            Toast.makeText(this, "아이디 또는 패스워드 틀림", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent2);
                            finish();
                        } else {
                            Toast.makeText(this, "로그인 "+vo.getName()+"님", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(SplashActivity.this, MainActivity.class);
                            intent2.putExtra("loginInfo", vo);
                            startActivity(intent2);
                            finish();
                        }
                    });

                });

            }
        }, 3000);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}