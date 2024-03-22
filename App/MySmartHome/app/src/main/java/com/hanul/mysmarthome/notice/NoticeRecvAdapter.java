package com.hanul.mysmarthome.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanul.mysmarthome.databinding.ItemQnaRecvBinding;
import com.hanul.mysmarthome.qna.QnaDetailActivity;
import com.hanul.mysmarthome.qna.QnaRecvAdapter;
import com.hanul.mysmarthome.qna.QnaVO;

import java.util.List;

public class NoticeRecvAdapter extends RecyclerView.Adapter<NoticeRecvAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<NoticeVO> list;
    Context context;

    public NoticeRecvAdapter(LayoutInflater inflater, List<NoticeVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQnaRecvBinding binding = ItemQnaRecvBinding.inflate(inflater,parent,false);

        return new NoticeRecvAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.itemQnaTitle.setText( list.get(i).getTitle());
        h.binding.itemQnaWriter.setText(list.get(i).getWriter());
        h.binding.itemQnaWritedate.setText(list.get(i).getWritedate().substring(0,10));



        h.binding.detailQnaLinear.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoticeDetailActivity.class);
            intent.putExtra("notice", list.get(i).getNotice_id());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemQnaRecvBinding binding;

        public ViewHolder(@NonNull ItemQnaRecvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
