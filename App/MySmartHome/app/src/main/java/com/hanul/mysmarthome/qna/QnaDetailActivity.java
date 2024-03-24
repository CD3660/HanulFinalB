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


    int qna_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQnaDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //QnaVO vo = getIntent().getSerializableExtra("vo");
        qna_id = getIntent().getIntExtra("qna_id", 0);

        new CommonConn(this, "qnaDetail/"+qna_id)
                .onExcute((isResult, data) -> {
                    QnaVO vo = new Gson().fromJson(data, QnaVO.class);
                    binding.detailQnaTitle.setText(vo.getTitle().toString());
                    binding.detailQnaContent.setText(vo.getContent().toString());
                    binding.detailQnaReadcnt.setText(String.valueOf( vo.getReadcnt()) );
                    binding.detailQnaFilecnt.setText(vo.getFilecnt()+"");


                });






        binding.qnaDetailClose.setOnClickListener(v -> {
            finish();
        });


        getCommentList(qna_id);




    }







    void getCommentList(int qna_id) {


            CommonConn conn = new CommonConn(this, "qnaComment");
                conn.addParamMap("qna_id", qna_id );

                conn.onExcute((isResult, data) -> {

                    Log.d("데이터2", "getCommentList: " + data);

                Type listType = new TypeToken<ArrayList<QnaCommentVO>>(){}.getType();
                ArrayList<QnaCommentVO> commentList = new Gson().fromJson(data, listType);

                Log.d("리스트2", "getCommentList: " + commentList);

                binding.commentRecvQna.setAdapter(new QnaDetailRecvAdapter(getLayoutInflater(), commentList, this));
                binding.commentRecvQna.setLayoutManager(new LinearLayoutManager(this));

                });




    }





}




