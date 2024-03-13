package com.hanul.mysmarthome.showdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

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
        int sensor_id = getIntent().getIntExtra("sensor_id",0);
        new CommonConn(this, "datas")
                .addParamMap("sensor_id", sensor_id)
                .onExcute((isResult, data) -> {
                    if (isResult) {
                        List<DatasVO> sensor_data = new Gson().fromJson(data, new TypeToken<List<DatasVO>>() {}.getType());
                        makeLineChart(sensor_data);
                    }
                });



    }

    public void makeLineChart(List<DatasVO> datas) {
        LineChart lineChart = binding.lineChart;


        List<Entry> entries1 = new ArrayList();
        List<String> xVal = new ArrayList<>();
        int i = 0;
        for (DatasVO data:datas) {
            entries1.add(new Entry(i++,data.getSensor_data1()));
            xVal.add(data.getFormattedTime());
        }
        String data_label = datas.get(0).getData_name1();
        if(data_label.equals("gas")){
            data_label = "가스";
        } else if(data_label.equals("dust")){
            data_label = "미세먼지";
        } else if(data_label.equals("fire")){
            data_label = "화재위험";
        } else if(data_label.equals("temp")){
            data_label = "온도";
        }
        LineDataSet lineDataSet = new LineDataSet(entries1, data_label);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFFFFF"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//라인 곡선으로 표시

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        if(datas.get(0).getData_name2() != null){
            List<Entry> entries2 = new ArrayList();
            i=0;
            for (DatasVO data:datas) {

                entries2.add(new Entry(i++,data.getSensor_data2()));
            }
            LineDataSet lineDataSet2 = new LineDataSet(entries2, "습도");
            lineDataSet.setLineWidth(2);
            lineDataSet.setCircleRadius(6);
            lineDataSet.setCircleColor(Color.parseColor("#FFFFFF"));
            lineDataSet.setCircleHoleColor(Color.BLUE);
            lineDataSet.setColor(Color.parseColor("#5356FF"));
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setDrawHighlightIndicators(false);
            lineDataSet.setDrawValues(false);
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//라인 곡선으로 표시
            dataSets.add(lineDataSet2);
        }


        LineData lineData = new LineData(dataSets);

        lineChart.setData(lineData);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVal));
        xAxis.setDrawLabels(true);
        xAxis.setLabelRotationAngle(45);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        //Description description = new Description();
        //description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        //lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EaseInCubic);
        lineChart.invalidate();

        MyMarkerView marker = new MyMarkerView(this, R.layout.marker_view_text);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);
    }
}