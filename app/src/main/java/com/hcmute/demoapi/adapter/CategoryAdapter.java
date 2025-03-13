package com.hcmute.demoapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hcmute.demoapi.R;
import com.hcmute.demoapi.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    List<Category> listCategory;
    public CategoryAdapter(Context context, List<Category> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);

        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        // gan data
        Category cate =  listCategory.get(position);
        holder.txtNameCate.setText(cate.getName());

        // load anh voi glide
        // Test tren local
//        String urlImage = "http://10.0.2.2:8081/uploads/" + cate.getImage();
//        Glide.with(context).load(urlImage).into(holder.imgCate);

        // test tren iotstar
        Glide.with(context).load(cate.getImage()).into(holder.imgCate);
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCate;
        private TextView txtNameCate;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCate = itemView.findViewById(R.id.image_cate);
            txtNameCate = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}
