package com.hanul.mysmarthome.member;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityMemberInfoBinding;
import com.hanul.mysmarthome.login.LoginActivity;

public class MemberInfoActivity extends AppCompatActivity {

    ActivityMemberInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            finish();
        });
        MemberVO loginInfo = (MemberVO) getIntent().getSerializableExtra("loginInfo");
        binding.addressText.setText(loginInfo.getAddress2()!=null?loginInfo.getAddress2():"주소 정보 없음");
        binding.phoneText.setText(loginInfo.getPhone()!=null?loginInfo.getPhone():"전화번호 정보 없음");
        binding.emailText.setText(loginInfo.getEmail()!=null?loginInfo.getEmail():"이메일 정보 없음");
        binding.logoutLayout.setOnClickListener(v -> {
            getSharedPreferences("AutoLogin", Context.MODE_PRIVATE).edit().remove("user_id").remove("user_pw").commit();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        binding.resignLayout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("회원탈퇴");
            builder.setMessage("정말 회원 탈퇴를 신청하시겠습니까?");
            builder.setNegativeButton("아니요",(dialog, which) -> {
                dialog.dismiss();
            });
            builder.setPositiveButton("예",(dialog, which) -> {
                new CommonConn(this,"resign")
                        .addParamMap("user_id", loginInfo.getUser_id())
                        .onExcute((isResult, data) -> {
                            if (isResult) {
                                getSharedPreferences("AutoLogin", Context.MODE_PRIVATE).edit().remove("user_id").remove("user_pw").commit();
                                Intent intent = new Intent(this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this,"연결 오류", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
            });
            builder.create().show();
        });
    }
}