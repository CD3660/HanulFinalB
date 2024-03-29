package com.hanul.mysmarthome.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.hanul.mysmarthome.mymenu.MyFragment;

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
            checkPermission();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_MEDIA_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                launcher_album.launch(intent);
            } else {
                Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show();
            }
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
    public final int REQ_PERMISSION = 900;
    public final int REQ_PERMISSION_DENY = 901;

    private void checkPermission() {

        String[] permissions = {Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE

        };//카메라 권한을 String으로 가져옴.


        // ContextCompat(액티비티가 아닌곳) , ActivityCompat(액티비티)
        for (int i = 0; i < permissions.length; i++) {
            //내가 모든 권한이 필요하다면 전체 권한을 하나씩 체크해서 허용 안됨이 있는경우 다시 요청을 하게 만든다.
            if (ActivityCompat.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {

                    ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION_DENY);

                } else {
                    //1.
                    ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION);
                }
                break;
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQ_PERMISSION == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    //거절된권한이있음.
                    checkPermission();
                    break;
                }
            }

            Log.d("권한", "onRequestPermissionsResult: 권한 요청 완료 ");
        } else if (REQ_PERMISSION_DENY == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Log.d("권한", "onRequestPermissionsResult: 다시 권한요청 화면을 띄울수가 없음.2회 거절당함. ");
                    //editor.putInt("permission" , -2);
                    //3.
                    //viewSetting();
                    //checkPermission();
                }
            }

        }
    }
}