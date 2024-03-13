package com.hanul.mysmarthome.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.ActivityQnaBinding;

public class QnaActivity extends AppCompatActivity {

    ActivityQnaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQnaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        QnaDTO dto = (QnaDTO) getIntent().getSerializableExtra("dto");



    }






























}