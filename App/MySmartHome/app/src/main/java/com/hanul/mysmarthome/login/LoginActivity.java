package com.hanul.mysmarthome.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.SplashActivity;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityLoginBinding;
import com.hanul.mysmarthome.member.MemberVO;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    String TAG = "login_method";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> {
            String user_id = binding.userId.getText().toString();
            String user_pw = binding.userPw.getText().toString();
            if(binding.autoLogin.isChecked()){
                SharedPreferences sp = getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user_id",user_id);
                editor.putString("user_pw",user_pw);
                editor.apply();
            }

            CommonConn conn = new CommonConn(this, "login")
                    .addParamMap("user_id",user_id)
                    .addParamMap("user_pw",user_pw);

            conn.onExcute((isResult, data) -> {
                if(!isResult){
                    Toast.makeText(this, "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                MemberVO vo = new Gson().fromJson(data, MemberVO.class);
                if (vo == null) {
                    Toast.makeText(this, "아이디 또는 패스워드 틀림", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "로그인 "+vo.getName()+"님", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("loginInfo", vo);
                    startActivity(intent);
                    finish();
                }
            });
        });
    }
}