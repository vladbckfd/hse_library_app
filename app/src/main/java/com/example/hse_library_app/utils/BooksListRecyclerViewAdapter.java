package com.example.hse_library_app.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hse_library_app.R;
import com.example.hse_library_app.model.ItemList;

import java.util.List;

public class BooksListRecyclerViewAdapter extends RecyclerView.Adapter<BooksListRecyclerViewAdapter.BookViewHolder> {
    private List<ItemList> mDataset;
    private static RecyclerViewOnItemClickListener clickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView textView;

        public BookViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v;
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BooksListRecyclerViewAdapter(List<ItemList> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_list_item_view, parent, false);

        BookViewHolder vh = new BookViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).getName());
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.book_icon_black_good_galleries__24, 0, 0);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener clickListener) {
        BooksListRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface RecyclerViewOnItemClickListener {
        void onItemClick(int position, View v);
    }
}