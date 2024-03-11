package com.hanul.mysmarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hanul.mysmarthome.databinding.ActivityMainBinding;
import com.hanul.mysmarthome.member.MemberService;
import com.hanul.mysmarthome.member.MemberVO;
import com.hanul.mysmarthome.myhome.MyHomeFragment;
import com.hanul.mysmarthome.mymenu.MyFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    ActivityMainBinding binding;
    MyFragment myFragment = new MyFragment(this);
    MyHomeFragment myHomeFragment = new MyHomeFragment();
    MemberVO loginInfo;
    public MemberVO getMemberVO(){
        return loginInfo;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        loginInfo = (MemberVO) intent.getSerializableExtra("loginInfo");
        new MemberService().replaceImgURL(loginInfo);

        binding.btmNav.setOnItemSelectedListener(this);
        onItemSelect(0);
    }

    @Override
    public boolean onItemSelect(int i) {
        if(i==0){
            changeFragment(myHomeFragment);
        } else if (i==1){
            changeFragment(myFragment);
        }
        return false;
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), fragment).commit();
    }
}