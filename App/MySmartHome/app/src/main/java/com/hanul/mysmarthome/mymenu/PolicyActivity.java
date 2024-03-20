package com.hanul.mysmarthome.mymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.ActivityPolicyBinding;

import java.io.IOException;
import java.io.InputStream;

public class PolicyActivity extends AppCompatActivity {
    ActivityPolicyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try{
            // InputStream으로 raw 파일 읽기
            InputStream inputS = getResources().openRawResource(R.raw.policy);

            // raw파일 크기를 확인하여 byte타입 배열 변수 txt 준비
            byte[] txt = new byte[inputS.available()];

            // InputStream의 raw 내용을 txt에 저장
            inputS.read(txt);

            // txt내용을 문자열로 변환하여 EditText에 저장
            binding.policy.setText(new String(txt));

            inputS.close();
        }
        catch (IOException e){
        }
    }
}