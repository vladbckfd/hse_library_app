package com.example.hse_library_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.Book;
import com.example.hse_library_app.model.ItemList;
import com.example.hse_library_app.server.ServerMobileAppApi;
import com.example.hse_library_app.server.ServerUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksListActivity extends AppCompatActivity {

    List<ItemList> booksNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        booksNameList = new ArrayList<>();
        String str = "itemList";
        int size = getIntent().getIntExtra("size", 0);
        for (int i = 0; i < size; i++) {
            booksNameList.add(((ItemList) getIntent().getSerializableExtra(str + i)));
        }
//                (ArrayList<ItemList>) getIntent().getSerializableExtra("booksList");
        ArrayAdapter<ItemList> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booksNameList);
        ListView listView = findViewById(R.id.booksListActivityListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
                Call<Book> bookCall = ServerUtils.getRetrofit().create(ServerMobileAppApi.class)
                        .getBookDetail(booksNameList.get(position).getIndex());
                bookCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        intent.putExtra("book", response.body());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
            }
        });
    }
}
