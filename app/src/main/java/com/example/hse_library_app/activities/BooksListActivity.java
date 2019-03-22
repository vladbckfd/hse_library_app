package com.example.hse_library_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.ItemList;
import com.example.hse_library_app.server.ServerMobileAppApi;
import com.example.hse_library_app.server.ServerUtils;
import com.example.hse_library_app.utils.BooksListRecyclerViewAdapter;

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
        findViewById(R.id.books_list_activity_recycler_view).setVisibility(View.INVISIBLE);
        loadBookList();
    }

    private void loadBookList() {
        ServerMobileAppApi serverMobileAppApi = ServerUtils.getRetrofit().create(ServerMobileAppApi.class);
        Call<List<ItemList>> booksList = serverMobileAppApi.getBooksList();
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
        RecyclerView recyclerView = findViewById(R.id.books_list_activity_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        BooksListRecyclerViewAdapter viewAdapter = new BooksListRecyclerViewAdapter(arrayList);
        viewAdapter.setOnItemClickListener(new BooksListRecyclerViewAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
                intent.putExtra("index", arrayList.get(position).getIndex());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(viewAdapter);
        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
