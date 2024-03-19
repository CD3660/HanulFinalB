package com.hanul.mysmarthome.myhome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.cctv.CCTVActivity;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ItemUserSensorBinding;
import com.hanul.mysmarthome.member.MemberVO;
import com.hanul.mysmarthome.showdata.ShowDataActivity;
import com.hanul.mysmarthome.usersensor.UserSensorVO;

import java.util.List;

public class UserSensorAdapter extends RecyclerView.Adapter<UserSensorAdapter.UserSensorViewHolder> {
    List<UserSensorVO> list;
    SharedPreferences sp;
    Context context;
    MemberVO loginInfo;

    public UserSensorAdapter(List<UserSensorVO> list, SharedPreferences sp, MemberVO loginInfo) {
        this.list = list;
        this.sp = sp;
        this.loginInfo = loginInfo;
    }

    @NonNull
    @Override
    public UserSensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        ItemUserSensorBinding binding = ItemUserSensorBinding.inflate(LayoutInflater.from(context), parent, false);
        return new UserSensorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSensorViewHolder h, int i) {
        h.binding.sensorName.setText(list.get(i).getSensor_name());
        int prod_id = list.get(i).getProd_id();
        if(prod_id == 13){
            if(sp.getBoolean("light_mode", false)){
                h.binding.sensorImg.setImageResource(R.drawable.icon_light_on);
                h.binding.sensorCtrl.setText("전원 끄기");
            } else {
                h.binding.sensorImg.setImageResource(R.drawable.icon_light_off);
                h.binding.sensorCtrl.setText("전원 켜기");
            }
            h.binding.sensorLayout.setOnClickListener(v -> {
                String url = "";
                if(sp.getBoolean("light_mode", false)){
                    url = "light_off";
                } else {
                    url = "light_on";
                }
                new CommonConn(context, url)
                        .addParamMap("user_id",loginInfo.getUser_id())
                        .addParamMap("sensor_id",list.get(i).getSensor_id())
                        .onExcute((isResult, data) -> {
                    if(isResult){
                        if(data.equals("led_off")){
                            sp.edit().putBoolean("light_mode", false).apply();
                            h.binding.sensorImg.setImageResource(R.drawable.icon_light_off);
                            h.binding.sensorCtrl.setText("전원 켜기");
                            Toast.makeText(context, "led off", Toast.LENGTH_SHORT).show();
                        } else if(data.equals("led_on")){
                            sp.edit().putBoolean("light_mode", true).apply();
                            h.binding.sensorImg.setImageResource(R.drawable.icon_light_on);
                            h.binding.sensorCtrl.setText("전원 끄기");
                            Toast.makeText(context, "led on", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "연동 오류. 다시 시도하세요.", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        } else if(prod_id == 14) {
            h.binding.sensorImg.setImageResource(R.drawable.icon_cctv);
            h.binding.sensorCtrl.setText("cctv 보기");
            h.binding.sensorLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, CCTVActivity.class);
                intent.putExtra("sensor_id", list.get(i).getSensor_id());
                context.startActivity(intent);
            });
        } else {
            h.binding.sensorImg.setImageResource(R.drawable.icon_datas);
            h.binding.sensorCtrl.setText("데이터 보기");
            h.binding.sensorLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, ShowDataActivity.class);
                intent.putExtra("sensor_id", list.get(i).getSensor_id());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserSensorViewHolder extends RecyclerView.ViewHolder {
        ItemUserSensorBinding binding;
        public UserSensorViewHolder(@NonNull ItemUserSensorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
