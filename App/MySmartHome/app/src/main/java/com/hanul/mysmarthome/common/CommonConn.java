package com.hanul.mysmarthome.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.hanul.mysmarthome.R;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonConn {
    //재사용 할 요소 : URL, Param, Method
    //다이얼로그 : 데이터 로딩중인 경우 알려준다. -> 화면 상태를 알기 위한 context 필요
    private Context context;
    private String url;
    private final String TAG = "CommonConn";
    private HashMap<String, Object> paramMap;
    private ProgressDialog dialog;


    public CommonConn(Context context, String url) {
        this.context = context;
        this.url = url;
        this.paramMap = new HashMap<>();
    }

    public CommonConn addParamMap(String key, Object value) {
        if (key == null) return this;
        paramMap.put(key, value);
        return this;
    }

    private void onPreExcute() {
        if (context != null && dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle(context.getString(R.string.app_name));
            dialog.setMessage("현재 데이터 로딩중...");
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    public void onExcute(CallBack callBack) {
        onPreExcute();
        ApiInterface service = CommonRetroClient.getRetrofit().create(ApiInterface.class);
        service.clientPostMethod(url, paramMap).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.errorBody() == null) {
                    callBack.onResult(true, response.body());
                } else {
                    callBack.onResult(false, response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBack.onResult(false, t.getMessage());
            }
        });


        onPostExcute();
    }

    private void onPostExcute() {
        if (context != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public interface CallBack {
        public void onResult(boolean isResult, String data);//MainActivity에서 new로 생성 후 CommonConn의 결과가 onResult를 실행하도록 한다.
    }

}
