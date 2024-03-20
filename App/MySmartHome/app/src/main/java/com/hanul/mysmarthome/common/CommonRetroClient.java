package com.hanul.mysmarthome.common;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CommonRetroClient {

    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://118.40.91.135:8057/finalb/app/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit;
    }
}
