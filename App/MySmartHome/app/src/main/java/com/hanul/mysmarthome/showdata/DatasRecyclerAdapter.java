package com.hanul.mysmarthome.showdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanul.mysmarthome.databinding.ItemDatasRecvBinding;

import java.util.List;

public class DatasRecyclerAdapter extends RecyclerView.Adapter<DatasRecyclerAdapter.ViewHolder> {

    List<DatasVO> list;
    Context context;

    public DatasRecyclerAdapter(List<DatasVO> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        ItemDatasRecvBinding binding = ItemDatasRecvBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.data1.setText(list.get(i).getSensor_data1()+"");
        if(list.get(i).getSensor_data2() != 0.0f){
            h.binding.data2.setText(list.get(i).getSensor_data2()+"");
        }
        h.binding.time.setText(list.get(i).getTime().toString());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemDatasRecvBinding binding;
        public ViewHolder(@NonNull ItemDatasRecvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
