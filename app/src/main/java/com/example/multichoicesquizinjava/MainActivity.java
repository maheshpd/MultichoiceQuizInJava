package com.example.multichoicesquizinjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.multichoicesquizinjava.Adapter.CategoryAdapter;
import com.example.multichoicesquizinjava.Common.SpaceDecoration;
import com.example.multichoicesquizinjava.DBHelper.DBHelper;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView_Category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Create app 2019");
        setSupportActionBar(toolbar);

        recyclerView_Category = findViewById(R.id.category_recyclerview);
        recyclerView_Category.setHasFixedSize(true);
        recyclerView_Category.setLayoutManager(new GridLayoutManager(this,2));

        //Get Screen height
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        CategoryAdapter adapter = new CategoryAdapter(this, DBHelper.getInstance(this).getAllCategories());

        int spaceInPixel = 4;
        recyclerView_Category.addItemDecoration(new SpaceDecoration(spaceInPixel));
        recyclerView_Category.setAdapter(adapter);
    }
}
