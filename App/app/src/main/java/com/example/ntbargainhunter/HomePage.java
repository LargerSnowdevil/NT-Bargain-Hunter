package com.example.ntbargainhunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ntbargainhunter.Adapter.RecyclerViewHorizontalListAdapter;
import com.example.ntbargainhunter.Model.Category;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {
    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView categoryRecyclerView;
    private RecyclerViewHorizontalListAdapter categoryAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        categoryRecyclerView = findViewById(R.id.idRecyclerViewHorizontalList);
        // add a divider after each item for more clarity
        categoryRecyclerView.addItemDecoration(new DividerItemDecoration(HomePage.this, LinearLayoutManager.HORIZONTAL));
        categoryAdapter = new RecyclerViewHorizontalListAdapter(categoryList, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(horizontalLayoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
        populateCategoryList();
        
        BottomNavigationView footerNavBar = findViewById(R.id.homePageFooterNav);
        footerNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menuActionHome) {
                    return true;
                } else if (menuItem.getItemId() == R.id.menuActionAccount) {
                        Intent i = new Intent(HomePage.this, UserBargainList.class);
                        startActivity(i);
                } else if (menuItem.getItemId() == R.id.menuActionPost) {
                    Intent i = new Intent(HomePage.this, PostBargainPage.class);
                    startActivity(i);
                } else if (menuItem.getItemId() == R.id.menuActionFavourites) {
                    // todo add something here
                } else {
                    System.out.println("ERROR:-- Footer navigation selection not recognized");
                }
                return false;
            }
        });
    }


    private void populateCategoryList(){
        Category automotive = new Category("Automotive", R.drawable.icons8_car_service_80);
        Category technology = new Category("Technology", R.drawable.icons8_laptop_80);
        Category shed = new Category("Shed", R.drawable.icons8_home_automation_80);
        Category fashion = new Category("Fashion", R.drawable.icons8_clothes_64);
        categoryList.add(automotive);
        categoryList.add(technology);
        categoryList.add(shed);
        categoryList.add(fashion);
        categoryAdapter.notifyDataSetChanged();
    }
    
    
    
    
    public void goToBargainDetailPage(View v) {
        Intent i = new Intent(this, DisplayBargainPage.class);
        // todo add code to tell next page what data to load
        startActivity(i);
    }
}

