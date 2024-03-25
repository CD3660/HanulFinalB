package com.hanul.mysmarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hanul.mysmarthome.databinding.ActivityMainBinding;
import com.hanul.mysmarthome.member.MemberService;
import com.hanul.mysmarthome.member.MemberVO;
import com.hanul.mysmarthome.myhome.MyHomeFragment;
import com.hanul.mysmarthome.mymenu.MyFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    ActivityMainBinding binding;
    MyFragment myFragment = new MyFragment(this);
    MyHomeFragment myHomeFragment = new MyHomeFragment(this);
    MemberVO loginInfo;
    public MemberVO getMemberVO(){
        return loginInfo;
    }

    public void setLoginInfo(MemberVO loginInfo) {
        this.loginInfo = loginInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        loginInfo = (MemberVO) intent.getSerializableExtra("loginInfo");
        if(loginInfo.getProfile() != null){
            new MemberService().replaceImgURL(loginInfo);
        }

        binding.btmNav.setOnItemSelectedListener(this);
        onItemSelect(0);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU
                && PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1005);
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1005 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "푸시알림이 허용되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}