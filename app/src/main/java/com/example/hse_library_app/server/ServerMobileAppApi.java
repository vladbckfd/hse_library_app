package com.example.hse_library_app.server;
import com.example.hse_library_app.model.Book;
import com.example.hse_library_app.model.ItemList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerMobileAppApi {
    @GET("/greeting")
    Call<Object> getMobileConfig();

    @GET("/booksList")
    Call<List<ItemList>> getBooksList();

    @GET("/bookDetail")
    Call<Book> getBookDetail(@Query("index") int index);
}
