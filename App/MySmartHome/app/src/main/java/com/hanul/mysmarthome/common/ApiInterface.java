package com.hanul.mysmarthome.common;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {
    @FormUrlEncoded
    @POST
    Call<String> clientPostMethod(@Url String url, @FieldMap HashMap<String, Object> params);

    @GET("{mapping}")
    Call<String> clientGetMethod(@Path("mapping") String url, @QueryMap HashMap<String, Object> params);

    @Multipart//파일 전송 시 필요한 어노테이션
    @POST
    Call<String> clientSendFile(@Url String url, @PartMap HashMap<String, RequestBody> maps, @Part MultipartBody.Part file);
    //@PartMap HashMap<String, MultipartBody.Part> fileMap

    @Multipart//파일 전송 시 필요한 어노테이션
    @POST
    Call<String> clientSendFiles(@Url String url, @PartMap HashMap<String, RequestBody> maps, @PartMap HashMap<String, MultipartBody.Part> fileMap);
}
