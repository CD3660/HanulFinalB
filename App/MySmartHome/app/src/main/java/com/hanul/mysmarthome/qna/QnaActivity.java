package com.hanul.mysmarthome.qna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityQnaBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QnaActivity extends AppCompatActivity {

    ActivityQnaBinding binding;
    ArrayList<QnaVO> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQnaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.qnaClose.setOnClickListener(v -> {
            finish();
        });
        getQnaList();
    }
    void getQnaList() {

        CommonConn conn = new CommonConn(this, "qna");
        //conn.addParamMap("asdsd", "data");//보낼 데이터 저장하는 메소드
        conn.onExcute((isResult, data) -> {//서버에 데이터를 보내고, 받아오는 부분
//              data = 받아오는 값
            Log.d("데이터", "onCreate: " + data);

            Type listType = new TypeToken<ArrayList<QnaVO>>(){}.getType();
            list = new Gson().fromJson(data, listType);
            Log.d("데이터", "list: " + list);


           LinearLayoutManager manager = new LinearLayoutManager(this);
           // manager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recvQna.setAdapter(new QnaRecvAdapter(getLayoutInflater(), list , this));
            binding.recvQna.setLayoutManager(manager);
        });
    }
}