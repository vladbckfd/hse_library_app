package com.example.hse_library_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.ItemList;
import com.example.hse_library_app.utils.BooksListRecyclerViewAdapter;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        ArrayList<String> myDataset = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            myDataset.add("TestString_" + i);
        }

//        RecyclerView recyclerView;
//        RecyclerView.Adapter mAdapter;
//        RecyclerView.LayoutManager layoutManager;

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BooksListRecyclerViewAdapter(new ArrayList<ItemList>() /*myDataset.toArray(new String[1])*/);
        ((BooksListRecyclerViewAdapter) mAdapter).setOnItemClickListener(new BooksListRecyclerViewAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(v.getContext(), "You clicked on item number " + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(mAdapter);

    }
}
