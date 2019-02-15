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

import java.util.ArrayList;
import java.util.List;

public class BooksListActivity extends AppCompatActivity {

    List<String> booksNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        booksNameList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booksNameList);
        ListView listView = findViewById(R.id.booksListActivityListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
//        intent.putExtra()
                startActivity(intent);
            }
        });

        booksNameList.add("Shades of gray");
        adapter.notifyDataSetChanged();
    }
}
