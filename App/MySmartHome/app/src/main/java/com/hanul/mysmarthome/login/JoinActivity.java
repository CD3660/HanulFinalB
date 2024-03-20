package com.hanul.mysmarthome.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.ApiInterface;
import com.hanul.mysmarthome.common.CommonRetroClient;
import com.hanul.mysmarthome.databinding.ActivityJoinBinding;
import com.hanul.mysmarthome.member.AddressActivity;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    ActivityJoinBinding binding;
    MultipartBody.Part filePart;//사진 데이터 저장
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    @Override
    protected void onStart() {
        super.onStart();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            String address = result.getData().getStringExtra("address");
            String post = result.getData().getStringExtra("post");
            binding.address.setText(address);
            binding.address2.setText(post);
        });
        launcher_album = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Glide.with(this).load(result.getData().getData()).into(binding.profile);//불러온 이미지를 이미지뷰에 붙임
            String filePath = getRealPath(result.getData().getData());
            RequestBody file = RequestBody.create(MediaType.parse("image/jpeg"), new File(filePath));
            filePart = MultipartBody.Part.createFormData("profile", "profile.jpg", file);//name:servlet구분자, 실제 파일명, 실제 파일

//            ApiInterface service = CommonRetroClient.getRetrofit().create(ApiInterface.class);
//            service.clientSendFile("user/updateProfile/", new HashMap<>(), filePart).enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//
//                }
//            });


        });
        binding.btnAddress.setOnClickListener(v -> {
            Intent intent = new Intent(this, JoinAddressActivity.class);
            launcher.launch(intent);
        });
        binding.btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            launcher_album.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> launcher;
    ActivityResultLauncher<Intent> launcher_album;

    public String getRealPath(Uri contentUri) {
        String res = null;//문자열 변수로 리턴하기 위해 변수 초기화
        String[] cols = {MediaStore.Images.Media.DATA};//컬럼이름을 받아온다.(조회시 alias)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {//안드 API26이전은 확인 불필요. 그냥 경로를 준다.
            Cursor cursor = this.getContentResolver().query(contentUri, cols, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
        }
        Log.d("갤러리", "getRealPath: " + res);

        return res;
    }
}