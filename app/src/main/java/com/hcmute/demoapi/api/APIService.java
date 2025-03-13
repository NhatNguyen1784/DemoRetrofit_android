package com.hcmute.demoapi.api;

import com.hcmute.demoapi.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getAllCategory();
}
