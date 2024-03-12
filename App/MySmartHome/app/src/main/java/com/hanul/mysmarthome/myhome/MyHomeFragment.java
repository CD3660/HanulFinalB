package com.hanul.mysmarthome.myhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.FragmentMyHomeBinding;
import com.hanul.mysmarthome.member.MemberVO;
import com.hanul.mysmarthome.usersensor.UserSensorVO;

import java.util.List;

public class MyHomeFragment extends Fragment {
    FragmentMyHomeBinding binding;
    MainActivity mainActivity;

    public MyHomeFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    MemberVO loginInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyHomeBinding.inflate(inflater, container, false);
        loginInfo = mainActivity.getMemberVO();
        SharedPreferences sp = getContext().getSharedPreferences("UserSensor", Context.MODE_PRIVATE);
        String userSensorListJson = sp.getString("list", "");
        List<UserSensorVO> list = new Gson().fromJson(userSensorListJson, new TypeToken<List<UserSensorVO>>() {
        }.getType());
        if (list != null) {
            UserSensorAdapter adapter = new UserSensorAdapter(list, sp, loginInfo);
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            binding.recyclerView.setAdapter(adapter);
        }
        binding.refreshSensor.setOnClickListener(v -> {
            new CommonConn(getContext(), "updateSensor")
                    .addParamMap("user_id", loginInfo.getUser_id())
                    .onExcute((isResult, data) -> {
                        if (isResult) {
                            this.
                            getContext().getSharedPreferences("UserSensor", Context.MODE_PRIVATE).edit().putString("list", data).commit();
                            List<UserSensorVO> list2 = new Gson().fromJson(data, new TypeToken<List<UserSensorVO>>() {
                            }.getType());
                            UserSensorAdapter adapter2 = new UserSensorAdapter(list2, sp, loginInfo);
                            binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                            binding.recyclerView.setAdapter(adapter2);
                        }
                    });
        });


        return binding.getRoot();
    }
}