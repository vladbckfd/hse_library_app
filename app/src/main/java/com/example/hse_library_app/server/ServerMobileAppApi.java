package com.example.hse_library_app.server;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerMobileAppApi {
    @GET("/greeting")
    Call<Object> getMobileConfig();
}
