package com.hanul.mysmarthome.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityNoticeBinding;
import com.hanul.mysmarthome.qna.QnaRecvAdapter;
import com.hanul.mysmarthome.qna.QnaVO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    ActivityNoticeBinding binding;
    List<NoticeVO> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.noticeClose.setOnClickListener(v -> {
            finish();
        });
        getNoticeList();
    }
    public void getNoticeList(){
        CommonConn conn = new CommonConn(this, "notice");
        conn.onExcute((isResult, data) -> {//서버에 데이터를 보내고, 받아오는 부분
//              data = 받아오는 값
            Log.d("데이터", "onCreate: " + data);

            Type listType = new TypeToken<List<NoticeVO>>(){}.getType();
            list = new Gson().fromJson(data, listType);
            Log.d("데이터", "list: " + list);


            LinearLayoutManager manager = new LinearLayoutManager(this);
            // manager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recvNotice.setAdapter(new NoticeRecvAdapter(getLayoutInflater(), list , this));
            binding.recvNotice.setLayoutManager(manager);
        });
    }
}