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
        //navigation option for bottom menu
        BottomNavigationView footerNavBar = findViewById(R.id.homePageFooterNav);
        footerNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menuActionFavourites) {
                    return true;
                } else if (menuItem.getItemId() == R.id.menuActionAccount) {
                    Intent i = new Intent(FavoriteBargain.this, UserBargainList.class);
                    startActivity(i);
                } else if (menuItem.getItemId() == R.id.menuActionPost) {
                    Intent i = new Intent(FavoriteBargain.this, PostBargainPage.class);
                    startActivity(i);
                } else if (menuItem.getItemId() == R.id.menuActionHome) {
                    Intent i = new Intent(FavoriteBargain.this, FavoriteBargain.class);
                    startActivity(i);
                } else {
                    System.out.println("ERROR:-- Footer navigation selection not recognized");
                }
                return false;
            }
        });
    }

}
