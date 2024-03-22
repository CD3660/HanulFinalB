package com.hanul.mysmarthome.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityNoticeDetailBinding;
import com.hanul.mysmarthome.qna.QnaVO;

public class NoticeDetailActivity extends AppCompatActivity {
    ActivityNoticeDetailBinding binding;
    int notice_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notice_id = getIntent().getIntExtra("notice_id", 0);
        new CommonConn(this, "noticeDetail/"+notice_id)
                .onExcute((isResult, data) -> {
                    NoticeVO vo = new Gson().fromJson(data, new TypeToken<NoticeVO>(){}.getType());
                    binding.detailNoticeTitle.setText(vo.getTitle().toString());
                    binding.detailNoticeContent.setText(vo.getContent().toString());
                    binding.detailNoticeReadcnt.setText(String.valueOf( vo.getReadcnt()) );
                });
        binding.noticeDetailClose.setOnClickListener(v -> {
            finish();
        });
    }
}