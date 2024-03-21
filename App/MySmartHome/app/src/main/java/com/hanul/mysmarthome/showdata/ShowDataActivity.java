package com.hanul.mysmarthome.showdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.chart.MyMarkerView;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityShowDataBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ShowDataActivity extends AppCompatActivity {
    ActivityShowDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new CommonConn(this, "datas")
                .addParamMap("sensor_id", getIntent().getStringExtra("sensor_id").toString())
                .onExcute((isResult, data) -> {
                    if(isResult){
                        List<DatasVO> list = new Gson().fromJson(data, new TypeToken<List<DatasVO>>(){}.getType());

                        binding.dataname1.setText(list.get(0).getData_name1());
                        binding.dataname2.setText(list.get(0).getData_name2()==null?"데이터 없음":list.get(0).getData_name2());

                        DatasRecyclerAdapter adapter = new DatasRecyclerAdapter(list);
                        binding.recyclerView.setAdapter(adapter);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


                    } else {
                        Toast.makeText(this, "데이터를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}