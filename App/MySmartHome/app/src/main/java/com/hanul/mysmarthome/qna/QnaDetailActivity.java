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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQnaDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        QnaVO vo = (QnaVO) getIntent().getSerializableExtra("vo");
        Log.d("내용", "onCreate: "+ vo);
        binding.detailQnaTitle.setText(vo.getTitle());
        binding.detailQnaContent.setText(vo.getContent());
        binding.detailQnaReadcnt.setText(vo.getReadcnt());
        binding.detailQnaFilecnt.setText(vo.getFilecnt());





        binding.qnaDetailClose.setOnClickListener(v -> {
            finish();
        });
    }




  /*  void getDetail() {

        CommonConn conn = new CommonConn(this, "qnaDetail");
        conn.addParamMap("qna_id", "qna_id");
        conn.onExcute((isResult, data) -> {
            Log.d("데이터", "getDetail: " + data);

            Type QnaVO = new TypeToken<QnaVO>(){}.getType();
            vo = new Gson().fromJson(data, QnaVO);
            Log.d("데이터", "getDetail: " + vo);

        });
        }*/






}




