package com.hanul.mysmarthome.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityQnaDetailBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QnaDetailActivity extends AppCompatActivity {

    ActivityQnaDetailBinding binding;

    ArrayList<QnaCommentVO> commentlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQnaDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        QnaVO vo = (QnaVO) getIntent().getSerializableExtra("vo");
        binding.detailQnaTitle.setText(vo.getTitle().toString());
        binding.detailQnaContent.setText(vo.getContent().toString());
        binding.detailQnaReadcnt.setText(String.valueOf( vo.getReadcnt()) );
        binding.detailQnaFilecnt.setText(vo.getFilecnt()+"");





        binding.qnaDetailClose.setOnClickListener(v -> {
            finish();
        });

        binding.commentRecvQna.setAdapter(new QnaDetailRecvAdapter(getLayoutInflater(), commentlist, this));


    }








}




