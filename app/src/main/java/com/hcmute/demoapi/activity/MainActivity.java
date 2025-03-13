package com.hcmute.demoapi.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.demoapi.R;
import com.hcmute.demoapi.RetrofitClient;
import com.hcmute.demoapi.adapter.CategoryAdapter;
import com.hcmute.demoapi.api.APIService;
import com.hcmute.demoapi.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcCategory;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> listCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhxa();
        getAllcategory();
    }

    public void anhxa(){
        rcCategory = (RecyclerView) findViewById(R.id.rcCategory);
    }

    public void getAllcategory(){
        // goi api service
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
//                    Log.d("success", "Thành công");
//                    Log.d("API Response", new Gson().toJson(listCategory));
                    listCategory = response.body();
                    // tao adapter
                    categoryAdapter = new CategoryAdapter(MainActivity.this, listCategory);
                    LinearLayoutManager layoutmanger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    rcCategory.setLayoutManager(layoutmanger);
                    rcCategory.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else {
                    int statusCode = response.code();
                    Log.d("status", "" + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
}