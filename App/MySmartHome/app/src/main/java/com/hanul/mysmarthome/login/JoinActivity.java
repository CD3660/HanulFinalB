package com.hanul.mysmarthome.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        new ClearCacheTask(this).execute();

    }
    final int GALLARY_REQ = 1000;
    @Override
    protected void onStart() {
        super.onStart();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode()==RESULT_CANCELED){

            } else {
                String address = result.getData().getStringExtra("address");
                String post = result.getData().getStringExtra("post");
                binding.address.setText(post);
                binding.address2.setText(address);
            }
        });
        launcher_album = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() != RESULT_CANCELED) {
                binding.profile.setImageURI(result.getData().getData());
//                Glide.with(this).load(result.getData().getData()).skipMemoryCache(true)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.profile);//불러온 이미지를 이미지뷰에 붙임
                String filePath = getRealPath(result.getData().getData());
                RequestBody file = RequestBody.create(MediaType.parse("image/jpeg"), new File(filePath));
                filePart = MultipartBody.Part.createFormData("profile_file", "profile.jpg", file);//name:servlet구분자, 실제 파일명, 실제 파일
            }
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

        binding.btnJoin.setOnClickListener(v -> {
            if(binding.userId.getText().toString().equals("")){
                Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                binding.userId.requestFocus();
                return;
            }
            if(binding.userPw.getText().toString().equals("")){
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                binding.userPw.requestFocus();
                return;
            }
            if(binding.name.getText().toString().equals("")){
                Toast.makeText(this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                binding.name.requestFocus();
                return;
            }
            if(!binding.userPw.getText().toString().equals(binding.userPwConf.getText().toString())){
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                binding.userPw.requestFocus();
                return;
            }
            if(!binding.email.getText().toString().equals("")&&!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()){
                Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                binding.email.requestFocus();
            }
            if(!binding.phone.getText().toString().equals("")&&!Patterns.PHONE.matcher(binding.phone.getText().toString()).matches()){
                Toast.makeText(this, "전화번호 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                binding.phone.requestFocus();
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",binding.name.getText().toString());
            map.put("user_id",binding.userId.getText().toString());
            map.put("user_pw",binding.userPw.getText().toString());
            map.put("phone",binding.phone.getText().toString());
            map.put("address",binding.address.getText().toString());
            map.put("address2",binding.address2.getText().toString());
            map.put("email",binding.email.getText().toString());
            ApiInterface service = CommonRetroClient.getRetrofit().create(ApiInterface.class);
            Call<String> call = null;
            if(filePart==null){
                call = service.clientPostMethod("join", map);
            } else {
                call = service.clientSendFile("join", map , filePart);
            }
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String result = response.body().toString();
                    Log.d("TAG", "onResponse: "+result);
                    if("success".equals(result)){
                        Toast.makeText(JoinActivity.this, "회원가입 성공! 로그인 해주세요", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else if("duplicate id".equals(result)){
                        Toast.makeText(JoinActivity.this, "아이디가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        binding.userId.requestFocus();
                    } else if("fail".equals(result)){
                        Toast.makeText(JoinActivity.this, "오류 발생. 다시 시도하세요", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("TAG", "onFailure: "+t);
                }
            });
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
    class ClearCacheTask extends AsyncTask<Void, Void, Void> {
        private Context context;

        public ClearCacheTask(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Glide.get(context).clearDiskCache();
//            Glide.get(context).clearMemory();
            return null;
        }
    }
}