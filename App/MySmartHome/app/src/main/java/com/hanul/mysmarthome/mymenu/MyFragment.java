package com.hanul.mysmarthome.mymenu;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.ApiInterface;
import com.hanul.mysmarthome.common.Common;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.common.CommonRetroClient;
import com.hanul.mysmarthome.databinding.FragmentMyBinding;
import com.hanul.mysmarthome.member.MemberInfoActivity;
import com.hanul.mysmarthome.member.MemberVO;
import com.hanul.mysmarthome.notice.NoticeActivity;
import com.hanul.mysmarthome.qna.QnaActivity;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFragment extends Fragment {
    FragmentMyBinding binding;
    MainActivity mainActivity;


    public MyFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater, container, false);
        checkPermission();
        Glide.with(this)
                .load(mainActivity.getMemberVO().getProfile())
                .fallback(R.drawable.d_profile_img)
                .into(binding.profile);

        binding.userInfo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MemberInfoActivity.class);
            intent.putExtra("user_id", mainActivity.getMemberVO().getUser_id());
            startActivity(intent);
        });
        binding.userName.setText(mainActivity.getMemberVO().getName());
        binding.profile.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("프로필 사진 변경");
            builder.setSingleChoiceItems(new String[]{"갤러리", "카메라"}, -1, (dialog, i) -> {
                if (i == 0) {
                    showGallary();
                } else if (i == 1) {
                    showCamera();
                }
                dialog.dismiss();
            });
            AlertDialog dialog = builder.create();
            builder.show();
        });


        binding.qna.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), QnaActivity.class);
            startActivity(intent);
        });
        binding.notice.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), NoticeActivity.class);
            startActivity(intent);
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode()!=RESULT_CANCELED) {

                File cameraFile = new File(getRealPath(cameraUri));
                //Multipart
                RequestBody file = RequestBody.create(MediaType.parse("image/jpeg"), cameraFile);

                MultipartBody.Part filePart = MultipartBody.Part.createFormData("profile", mainActivity.getMemberVO().getUser_id() + "profile.jpg", file);//name:servlet구분자, 실제 파일명, 실제 파일
                ApiInterface service = CommonRetroClient.getRetrofit().create(ApiInterface.class);
                service.clientSendFile("user/updateProfile/" + mainActivity.getMemberVO().getUser_id(), new HashMap<>(), filePart).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            mainActivity.setLoginInfo(new Gson().fromJson(response.body(), MemberVO.class));
                            Glide.with(MyFragment.this).load(mainActivity.getMemberVO().getProfile()).into(binding.profile);//불러온 이미지를 이미지뷰에 붙임
                        } else {
                            Toast.makeText(getContext(), "서버 오류", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(), "시스템 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.policy.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PolicyActivity.class);
            startActivity(intent);
        });
        binding.appVersion.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();


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

    ActivityResultLauncher<Intent> launcher;//onCreate메소드에서 초기화 시 오류 발생
    final int GALLARY_REQ = 1000;
    final int CAMERA_REQ = 1001;

    public void showGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, GALLARY_REQ);
    }

    Uri cameraUri = null;

    public void showCamera() {
        //카메라로 사용자가 사진을 찍으면 우리가 미리 임시로 만들어둔 URI에 카메라 사진을 외부 저장소에 저장 후 알려줌
        cameraUri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        launcher.launch(cameraIntent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("갤러리", "onActivityResult: " + requestCode);


        if (requestCode == GALLARY_REQ && resultCode == RESULT_OK) {

            String filePath = getRealPath(data.getData());

            //Multipart
            RequestBody file = RequestBody.create(MediaType.parse("image/jpeg"), new File(filePath));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("profile", mainActivity.getMemberVO().getUser_id() + "profile.jpg", file);//name:servlet구분자, 실제 파일명, 실제 파일
            ApiInterface service = CommonRetroClient.getRetrofit().create(ApiInterface.class);
            service.clientSendFile("user/updateProfile/"+mainActivity.getMemberVO().getUser_id(), new HashMap<>(), filePart).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    mainActivity.setLoginInfo(new Gson().fromJson(response.body(), MemberVO.class ));
                    Glide.with(MyFragment.this).load(mainActivity.getMemberVO().getProfile()).into(binding.profile);//불러온 이미지를 이미지뷰에 붙임
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


        } else if (requestCode == CAMERA_REQ) {

        }
    }

    //contentReslver라는 컴포넌트를 이용하여 Uri를 통해 실제 이미지의 경로를 조회한다.
    //Android 내부에 있는 모든 요소는 전부 table 형태로 저장되어있다.
    public String getRealPath(Uri contentUri) {
        String res = null;//문자열 변수로 리턴하기 위해 변수 초기화
        String[] cols = {MediaStore.Images.Media.DATA};//컬럼이름을 받아온다.(조회시 alias)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {//안드 API26이전은 확인 불필요. 그냥 경로를 준다.
            Cursor cursor = getContext().getContentResolver().query(contentUri, cols, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
        }
        Log.d("갤러리", "getRealPath: " + res);

        return res;
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
            if (ActivityCompat.checkSelfPermission(getContext(), permissions[i]) == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity, permissions[i])) {
                    //최초 앱이 설치되고 실행 시 false가 나옴.=>사용자가 거부 후 true 재거부=>false
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("권한 요청").setMessage("권한이 반드시 필요합니다.!!미허용시 앱 사용 불가!");
                    builder.setPositiveButton("확인(권한허용)", (dialog, which) -> {
                        //2.권한 설명 후 다시보여줌.
                        ActivityCompat.requestPermissions(mainActivity, permissions, REQ_PERMISSION_DENY);
                    });
                    builder.setNegativeButton("종료(권한허용불가)", (dialog, which) -> {

                    });
                    builder.create().show();//<==넣어줘야함.
                } else {
                    //1.
                    ActivityCompat.requestPermissions(mainActivity, permissions, REQ_PERMISSION);
                }
                break;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    public void viewSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        intent.setData(Uri.parse("package:" + getContext().getApplicationContext().getPackageName()));
        startActivity(intent);

    }
}