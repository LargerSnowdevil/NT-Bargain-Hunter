//package com.example.ntbargainhunter;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
//import android.view.View;
//
//
//public class UserBargainList extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_bargain_list);
//        BottomNavigationView footerNavBar = findViewById(R.id.userPageFooterNav);
//        footerNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                if (menuItem.getItemId() == R.id.menuActionHome) {
//                    Intent i = new Intent(UserBargainList.this, HomePage.class);
//                    startActivity(i);
//                } else if (menuItem.getItemId() == R.id.menuActionAccount) {
//                    return true;
//                } else if (menuItem.getItemId() == R.id.menuActionPost) {
//                    Intent i = new Intent(UserBargainList.this, PostBargainPage.class);
//                    startActivity(i);
//                } else if (menuItem.getItemId() == R.id.menuActionFavourites) {
//                    Intent i = new Intent(UserBargainList.this, FavoriteBargain.class);
//                    startActivity(i);
//                } else {
//                    System.out.println("ERROR:-- Footer navigation selection not recognized");
//                }
//                return false;
//            }
//        });
//    }
//
//    public void goToSignInPage(View v) {
//        Intent i = new Intent(this, SignInPage.class);
//        startActivity(i);
//    }
//}
