package com.hanul.mysmarthome.qna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanul.mysmarthome.databinding.ItemQnaDetailRecvBinding;

import java.util.ArrayList;

public class QnaDetailRecvAdapter extends RecyclerView.Adapter<QnaDetailRecvAdapter.ViewHolder> {


    LayoutInflater inflater;

    ArrayList<QnaCommentVO> commentList;

    Context context;

    public QnaDetailRecvAdapter(LayoutInflater inflater, ArrayList<QnaCommentVO> commentList, Context context) {
        this.inflater = inflater;
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQnaDetailRecvBinding binding = ItemQnaDetailRecvBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

          h.binding.detailQnaCommentWriter.setText(commentList.get(i).getWriter());
          h.binding.detailQnaCommentWritedate.setText(commentList.get(i).getWritedate());
          h.binding.detailQnaCommentContent.setText(commentList.get(i).getWritedate());



    }

    @Override
    public int getItemCount() { return commentList.size(); }






    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemQnaDetailRecvBinding binding;

        public ViewHolder(@NonNull ItemQnaDetailRecvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }






}
