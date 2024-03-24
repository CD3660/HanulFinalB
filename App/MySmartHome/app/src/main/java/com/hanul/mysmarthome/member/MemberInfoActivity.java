package com.hanul.mysmarthome.member;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hanul.mysmarthome.DataEditDialog;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityMemberInfoBinding;
import com.hanul.mysmarthome.login.LoginActivity;

public class MemberInfoActivity extends AppCompatActivity {

    ActivityMemberInfoBinding binding;
    MemberVO loginInfo;
    ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            finish();
        });
        new CommonConn(this, "userInfo").addParamMap("user_id", getIntent().getStringExtra("user_id")).onExcute((isResult, data) -> {
            loginInfo = new Gson().fromJson(data,MemberVO.class);
            binding.addressText.setText(loginInfo.getAddress2()!=null?loginInfo.getAddress2():"주소 정보 없음");
            binding.phoneText.setText(loginInfo.getPhone()!=null?loginInfo.getPhone():"전화번호 정보 없음");
            binding.emailText.setText(loginInfo.getEmail()!=null?loginInfo.getEmail():"이메일 정보 없음");
        });
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

        binding.phoneLayout.setOnClickListener(v -> {
            DataEditDialog dialog = new DataEditDialog(this,"전화번호 수정", null, loginInfo.getPhone());
            dialog.setSubmitListener(v1 -> {
                String phone = dialog.getBinding().editData.getText().toString();
                new CommonConn(this, "user/update")
                        .addParamMap("phone", phone)
                        .addParamMap("user_id", getIntent().getStringExtra("user_id"))
                        .onExcute((isResult, data) -> {
                            if(isResult){
                                Toast.makeText(this, "회원정보 수정 완료", Toast.LENGTH_SHORT).show();
                                binding.phoneText.setText(phone);
                                loginInfo = new Gson().fromJson(data, MemberVO.class);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(this, "연결오류", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
            });
            dialog.show();
        });
        binding.emailLayout.setOnClickListener(v -> {
            DataEditDialog dialog = new DataEditDialog(this,"이메일 수정", null, loginInfo.getEmail());
            dialog.setSubmitListener(v1 -> {
                String email = dialog.getBinding().editData.getText().toString();
                new CommonConn(this, "user/update")
                        .addParamMap("email", email)
                        .addParamMap("user_id", getIntent().getStringExtra("user_id"))
                        .onExcute((isResult, data) -> {
                            if(isResult){
                                Toast.makeText(this, "회원정보 수정 완료", Toast.LENGTH_SHORT).show();
                                binding.emailText.setText(email);
                                loginInfo = new Gson().fromJson(data, MemberVO.class);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(this, "연결오류", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
            });
            dialog.show();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode()==RESULT_CANCELED){

            } else {
                String address = result.getData().getStringExtra("address");
                String post = result.getData().getStringExtra("post");
                binding.addressText.setText(address);
            }
        });
        binding.addressLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddressActivity.class);
            intent.putExtra("user_id", loginInfo.getUser_id());
            launcher.launch(intent);
        });
    }
}