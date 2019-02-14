package com.example.hse_library_app;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MobileConfigService {
    @GET("/greeting")
    Call<Object> getMobileConfig();
}
