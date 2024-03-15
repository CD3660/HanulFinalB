package com.hanul.mysmarthome.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.ActivityQnaDetailBinding;

public class QnaDetailActivity extends AppCompatActivity {

    ActivityQnaDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQnaDetailBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_qna_detail);

//
//        QnaDTO dto = (QnaDTO) getIntent().getSerializableExtra("dto");
//        binding.title_detail.setTitle(dto.getTitle());
//        binding.content_detail.setcontent(dto.getContent());
//
//        binding.backToList.setOnClickListener(v->{
//            finish();
//
//    })
    }
}