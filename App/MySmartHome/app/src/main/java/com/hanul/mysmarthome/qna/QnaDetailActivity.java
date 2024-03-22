package com.hanul.mysmarthome.qna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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

    ArrayList<QnaCommentVO> commentList;


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



        getCommentList();



    }







    void getCommentList() {


            CommonConn conn = new CommonConn(this, "qnaComment");
                conn.onExcute((isResult, data) -> {

                    Log.d("데이터2", "getCommentList: " + data);

                Type listType = new TypeToken<ArrayList<QnaCommentVO>>(){}.getType();
                commentList = new Gson().fromJson(data, listType);

                    Log.d("리스트2", "getCommentList: " + commentList);

                binding.commentRecvQna.setAdapter(new QnaDetailRecvAdapter(getLayoutInflater(), commentList, this));
                binding.commentRecvQna.setLayoutManager(new LinearLayoutManager(this));

                });




    }





}




