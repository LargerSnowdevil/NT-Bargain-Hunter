package com.example.ntbargainhunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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

    public void goToBargainDetailPage(View v) {
        Intent i = new Intent(this, DisplayBargainPage.class);
        // todo add code to tell next page what data to load
        startActivity(i);
    }
}
