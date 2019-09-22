package com.example.multichoicesquizinjava.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multichoicesquizinjava.Common.Common;
import com.example.multichoicesquizinjava.Model.Category;
import com.example.multichoicesquizinjava.R;
import com.example.multichoicesquizinjava.activity.QuestionsActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyCategoryHolder> {

    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_item, parent, false);
        return new MyCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoryHolder holder, int position) {
        holder.txt_category_name.setText(categoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyCategoryHolder extends RecyclerView.ViewHolder {

        CardView card_category;
        TextView txt_category_name;


        public MyCategoryHolder(@NonNull View itemView) {
            super(itemView);
            card_category = itemView.findViewById(R.id.card_category);
            txt_category_name = itemView.findViewById(R.id.txt_category_name);

            card_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.selectedCategory = categoryList.get(getAdapterPosition());
                    Intent intent = new Intent(context, QuestionsActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
