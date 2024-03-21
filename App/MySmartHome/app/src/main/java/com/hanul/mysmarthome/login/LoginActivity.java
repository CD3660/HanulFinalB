package com.hanul.mysmarthome.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.hanul.mysmarthome.BuildConfig;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityLoginBinding;
import com.hanul.mysmarthome.member.MemberVO;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileResponse;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    String TAG = "login_method";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> {
            String user_id = binding.userId.getText().toString();
            String user_pw = binding.userPw.getText().toString();
            if(binding.autoLogin.isChecked()){
                SharedPreferences sp = getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user_id",user_id);
                editor.putString("user_pw",user_pw);
                editor.apply();
            }

            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();
                CommonConn conn = new CommonConn(this, "login")
                        .addParamMap("user_id",user_id)
                        .addParamMap("user_pw",user_pw)
                        .addParamMap("token", token);

                conn.onExcute((isResult, data) -> {
                    if(!isResult){
                        Toast.makeText(this, "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    MemberVO vo = new Gson().fromJson(data, MemberVO.class);
                    if (vo == null) {
                        Toast.makeText(this, "아이디 또는 패스워드 틀림", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "로그인 "+vo.getName()+"님", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("loginInfo", vo);
                        startActivity(intent);
                        finish();
                    }
                });
            });
        });

        binding.tvJoin.setOnClickListener(v -> {
            Intent intent = new Intent(this, JoinActivity.class);
            startActivity(intent);
        });
        getHashKey();
        initLogin();
        binding.naverLogin.setOAuthLogin(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                new NidOAuthLogin().callProfileApi(new NidProfileCallback<NidProfileResponse>() {
                    @Override
                    public void onSuccess(NidProfileResponse nidProfileResponse) {
                        MemberVO vo = new MemberVO();
                        vo.setPhone(nidProfileResponse.getProfile().getMobile());
                        vo.setName(nidProfileResponse.getProfile().getName());
                        vo.setUser_id(nidProfileResponse.getProfile().getId());
                        vo.setEmail(nidProfileResponse.getProfile().getEmail());
                        Log.d(TAG, "onSuccess: "+vo.getUser_id()+vo.getName());
                        socialLogin(vo);
                    }

                    @Override
                    public void onFailure(int i, @NonNull String s) {

                    }

                    @Override
                    public void onError(int i, @NonNull String s) {

                    }
                });
            }

            @Override
            public void onFailure(int i, @NonNull String s) {
                Toast.makeText(LoginActivity.this, "네이버 로그인이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int i, @NonNull String s) {

            }
        });
        binding.imgvKakaoLogin.setOnClickListener(v -> {
            kakaoLoginClick();
        });
    }
    public void initLogin(){
        NaverIdLoginSDK.INSTANCE.initialize(this, BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET, BuildConfig.NAVER_CLIENT_NAME);
        KakaoSdk.init(LoginActivity.this, BuildConfig.KAKAO_NATIVE_APP_KEY);
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
    public void kakaoLoginClick(){

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable error) {
                if (error == null) {
                    Log.d("카카오", "invoke: " + oAuthToken.getAccessToken());
                } else {
                    Log.d("카카오", "invoke: " + error.getMessage());
                }

                return null;
            }
        };
        //카카오톡 설치여부 확인 true->앱으로 인증(권장)
//                           false->웹뷰로 인증
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
            Log.d("카카오", "kakaoLogin: 카카오톡 설치됨->app");
            UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);

        } else {
            Log.d("카카오", "kakaoLogin: 카카오톡 설치 안됨->web");
            UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
            UserApiClient.getInstance().me((user, throwable) -> {
                if (throwable == null) {
                    Log.d("카카오정보", "invoke: " + user.getKakaoAccount().getProfile().getNickname());
                    Log.d("카카오정보", "invoke: " + user.getId());
                    Log.d("카카오정보", "invoke: " + user.getKakaoAccount().getProfile().getProfileImageUrl());
                } else {
                    Log.d("카카오정보", "invoke: " + throwable.getMessage());
                }
                return null;
            });
        }
    }
    public void socialLogin(MemberVO vo){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                return;
            }

            // Get new FCM registration token
            String token = task.getResult();

            CommonConn conn = new CommonConn(LoginActivity.this, "naverLogin")
                    .addParamMap("user_id",vo.getUser_id())
                    .addParamMap("name",vo.getName())
                    .addParamMap("email",vo.getEmail()==null?"":vo.getEmail())
                    .addParamMap("phone",vo.getPhone()==null?"":vo.getPhone())
                    .addParamMap("social","N")
                    .addParamMap("token", token);

            conn.onExcute((isResult, data) -> {
                if(!isResult){
                    Toast.makeText(this, "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                MemberVO vo2 = new Gson().fromJson(data, MemberVO.class);
                if (vo2 == null) {
                    Toast.makeText(this, "아이디 또는 패스워드 틀림", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "로그인 "+vo2.getName()+"님", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("loginInfo", vo2);
                    startActivity(intent);
                    finish();
                }
            });
        });
    }
}