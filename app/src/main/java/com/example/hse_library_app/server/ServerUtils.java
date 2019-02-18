package com.example.hse_library_app.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerUtils {
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://hse-library.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
