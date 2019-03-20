package com.example.hse_library_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.ItemList;
import com.example.hse_library_app.server.ServerMobileAppApi;
import com.example.hse_library_app.server.ServerUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        findViewById(R.id.booksListActivityListView).setVisibility(View.INVISIBLE);
        loadBookList();
    }

    private void loadBookList() {
        ServerMobileAppApi serverMobileAppApi = ServerUtils.getRetrofit().create(ServerMobileAppApi.class);
        Call<List<ItemList>> booksList = serverMobileAppApi.getBooksList();
        final Intent intent = new Intent(this, BooksListActivity.class);
        booksList.enqueue(new Callback<List<ItemList>>() {
            @Override
            public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                showBookList(response.body());
            }

            @Override
            public void onFailure(Call<List<ItemList>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Call to the server is failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showBookList(final List<ItemList> arrayList) {
        ArrayAdapter<ItemList> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        ListView listView = findViewById(R.id.booksListActivityListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
                intent.putExtra("index", arrayList.get(position).getIndex());
                startActivity(intent);
            }
        });
        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }
}
