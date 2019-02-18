package com.example.hse_library_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Book book = (Book) getIntent().getSerializableExtra("book");
        ((TextView) findViewById(R.id.activityBookDetailNameValue)).setText(book.getName());
        ((TextView) findViewById(R.id.activityBookDetailAuthorValue)).setText(book.getAuthor());
        ((TextView) findViewById(R.id.activityBookDetailVolumeValue)).setText(book.getVolume().toString());
        ((TextView) findViewById(R.id.activityBookDetailInStockValue)).setText(book.getInStock().toString());
    }
}
