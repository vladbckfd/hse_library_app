package com.example.hse_library_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.Book;
import com.example.hse_library_app.server.ServerMobileAppApi;
import com.example.hse_library_app.server.ServerUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        findViewById(R.id.activityBookDetailLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.activityBookDetailProgressBarLayout).setVisibility(View.VISIBLE);
        loadBook();
    }

    private void loadBook() {
        int index = getIntent().getIntExtra("index", 0);
        Call<Book> bookCall = ServerUtils.getRetrofit().create(ServerMobileAppApi.class).getBookDetail(index);
        bookCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                showLoadedBook(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Call to the server is failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showLoadedBook(Book book) {
        ((TextView) findViewById(R.id.activityBookDetailNameValue)).setText(book.getName());
        ((TextView) findViewById(R.id.activityBookDetailAuthorValue)).setText(book.getAuthor());
        ((TextView) findViewById(R.id.activityBookDetailVolumeValue)).setText(book.getVolume().toString());
        ((TextView) findViewById(R.id.activityBookDetailInStockValue)).setText(book.getInStock().toString());
        findViewById(R.id.activityBookDetailProgressBarLayout).setVisibility(View.INVISIBLE);
        findViewById(R.id.activityBookDetailLayout).setVisibility(View.VISIBLE);
    }
}
