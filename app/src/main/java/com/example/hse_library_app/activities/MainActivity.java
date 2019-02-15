package com.example.hse_library_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hse_library_app.R;
import com.example.hse_library_app.server.ServerMobileAppApi;

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

        //        final TextView textView = (TextView) findViewById(R.id.mainActivityTextView);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://hse-library.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ServerMobileAppApi serverMobileAppApi = retrofit.create(ServerMobileAppApi.class);
//        Call<Object> mobileConfigApi = serverMobileAppApi.getMobileConfig();
//        mobileConfigApi.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                textView.setText("Async response" + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                textView.setText("Async fail!!");
//            }
//        });
    }

    public void showListOfBooks(View view) {
        startActivity(new Intent(this, BooksListActivity.class));
    }
}
