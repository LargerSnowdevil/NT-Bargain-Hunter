package com.example.ntbargainhunter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ntbargainhunter.Adapter.RecyclerViewHorizontalListAdapter;
import com.example.ntbargainhunter.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBargain extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bargain_favorite);
        this.setTitle("Your favourite items");
        this.getTitle();

        //favoriteItems=findViewById(R.id.menuActionFavourites);
      //  favoriteItems.setOnClickListener();
    }

}
