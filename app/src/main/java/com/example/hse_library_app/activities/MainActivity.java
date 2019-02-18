package com.example.hse_library_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.ItemList;
import com.example.hse_library_app.server.ServerMobileAppApi;
import com.example.hse_library_app.server.ServerUtils;

import java.util.ArrayList;
import java.util.List;

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
        ServerMobileAppApi serverMobileAppApi = ServerUtils.getRetrofit().create(ServerMobileAppApi.class);
        Call<List<ItemList>> booksList = serverMobileAppApi.getBooksList();
        final Intent intent = new Intent(this, BooksListActivity.class);
        booksList.enqueue(new Callback<List<ItemList>>() {
            @Override
            public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                //надо по нормальному передавать список
                List<ItemList> arrayList = response.body();
                String str = "itemList";
                int index = 0;
                for (ItemList itemList : arrayList) {
                    intent.putExtra(str + index, itemList);
                    index += 1;
                }
                intent.putExtra("size", index);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<ItemList>> call, Throwable t) {
                //нужно что-то с этим сделать, например показать тоаст
            }
        });
    }
}
