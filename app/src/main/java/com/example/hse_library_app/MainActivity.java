package com.example.hse_library_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.mainActivityTextView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hse-library.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MobileConfigService mobileConfigService = retrofit.create(MobileConfigService.class);
        Call<Object> mobileConfigApi = mobileConfigService.getMobileConfig();
        mobileConfigApi.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                textView.setText("Async response" + response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                textView.setText("Async fail!!");
            }
        });
    }
}
