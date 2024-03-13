package com.hanul.mysmarthome.qna;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanul.mysmarthome.databinding.ItemQnaRecvBinding;

import java.util.ArrayList;

public class QnaRecvAdapter extends RecyclerView.Adapter<QnaRecvAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<QnaDTO> list;
    Context context;

    public QnaRecvAdapter(LayoutInflater inflater, ArrayList<QnaDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQnaRecvBinding binding = ItemQnaRecvBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        h.binding.itemQnaTitle.setText( list.get(i).getTitle());

        h.binding.itemQnaLinear.setOnClickListener(v -> {
            Intent intent = new Intent(context, QnaActivity.class);
            intent.putExtra("dto", list.get(i));
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
