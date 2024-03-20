package com.hanul.mysmarthome.member;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.hanul.mysmarthome.NetworkStatus;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.common.CommonConn;
import com.hanul.mysmarthome.databinding.ActivityAddressBinding;

public class AddressActivity extends AppCompatActivity {
    ActivityAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int status = NetworkStatus.getConnectivityStatus(this);
        if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {
            //주소검색 웹 뷰를 띄우는 코드를 따로 편성
            init_webView();
        } else {
            Toast.makeText(this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
        }

    }

    WebView webView;
    Handler handler;

    public void webViewSetting() {
        handler = new Handler();

    }

    public void init_webView() {
        webViewSetting();
        // WebView 설정
        webView = binding.daumWebview;


        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);

        // JavaScript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        webView.addJavascriptInterface(new AndroidBridge(this), "My");

        //DOMStorage 허용
        webView.getSettings().setDomStorageEnabled(true);

        //ssl 인증이 없는 경우 해결을 위한 부분
        webView.setWebChromeClient(new WebChromeClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // SSL 에러가 발생해도 계속 진행
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // 페이지 로딩 시작시 호출
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("페이지 시작", url);
                binding.webProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.webProgress.setVisibility(View.GONE);
                Log.e("페이지 로딩", url);
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        // webview url load. php or html 파일 주소
        webView.loadUrl("http://192.168.0.57/finalb/resources/roadSearch.html");
    }

    class AndroidBridge {
        Context context;

        public AndroidBridge(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void result(String address, String post) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    new CommonConn(context, "user/update")
                            .addParamMap("address", post)
                            .addParamMap("address2", address)
                            .addParamMap("user_id", getIntent().getStringExtra("user_id"))
                            .onExcute((isResult, data) -> {
                                if (isResult) {
                                    Toast.makeText(context, "회원정보 수정 완료", Toast.LENGTH_SHORT).show();
                                    Intent goIntent = new Intent(context, MemberInfoActivity.class);
                                    MemberVO loginInfo = new Gson().fromJson(data, MemberVO.class);
                                    goIntent.putExtra("user_id", loginInfo.getUser_id());
                                    startActivity(goIntent);
                                    finish();
                                } else {
                                    Toast.makeText(context, "연결오류", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                }
            });
        }
    }
}