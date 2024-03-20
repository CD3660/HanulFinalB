package com.hanul.mysmarthome.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.ActivityJoinBinding;

public class JoinActivity extends AppCompatActivity {
    ActivityJoinBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAddress.setOnClickListener(v -> {

        });

    }
}