//package com.example.ntbargainhunter;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.ntbargainhunter.Model.Bargains;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.squareup.picasso.Picasso;
//
//
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.ViewGroup;
//
//import com.example.ntbargainhunter.Adapter.RecyclerViewHorizontalListAdapter;
//import com.example.ntbargainhunter.Model.Category;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class HomePage extends AppCompatActivity {
////    private List<Category> categoryList = new ArrayList<>();
////    private RecyclerView categoryRecyclerView;
////    private RecyclerViewHorizontalListAdapter categoryAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);
//
////        categoryRecyclerView = findViewById(R.id.idRecyclerViewHorizontalList);
////        // add a divider after each item for more clarity
////        categoryRecyclerView.addItemDecoration(new DividerItemDecoration(HomePage.this, LinearLayoutManager.HORIZONTAL));
////        categoryAdapter = new RecyclerViewHorizontalListAdapter(categoryList, getApplicationContext());
////        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
////        categoryRecyclerView.setLayoutManager(horizontalLayoutManager);
////        categoryRecyclerView.setAdapter(categoryAdapter);
////        populateCategoryList();
////
////        BottomNavigationView footerNavBar = findViewById(R.id.homePageFooterNav);
////        footerNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
////                if (menuItem.getItemId() == R.id.menuActionHome) {
////                    return true;
////                } else if (menuItem.getItemId() == R.id.menuActionAccount) {
////                    Intent i = new Intent(HomePage.this, UserBargainList.class);
////                    startActivity(i);
////                } else if (menuItem.getItemId() == R.id.menuActionPost) {
////                    Intent i = new Intent(HomePage.this, PostBargainPage.class);
////                    startActivity(i);
////                } else if (menuItem.getItemId() == R.id.menuActionFavourites) {
////                    Intent i = new Intent(HomePage.this, FavoriteBargain.class);
////                    startActivity(i);
////                } else {
////                    System.out.println("ERROR:-- Footer navigation selection not recognized");
////                }
////                return false;
////            }
////        });
//
//    }
//
//
//
//
////        private void populateCategoryList() {
////            Category automotive = new Category("Automotive", R.drawable.icons8_car_service_80);
////            Category technology = new Category("Technology", R.drawable.icons8_laptop_80);
////            Category shed = new Category("Shed", R.drawable.icons8_home_automation_80);
////            Category fashion = new Category("Fashion", R.drawable.icons8_clothes_64);
////            categoryList.add(automotive);
////            categoryList.add(technology);
////            categoryList.add(shed);
////            categoryList.add(fashion);
////            categoryAdapter.notifyDataSetChanged();
////        }
//
//
//    }
//
//
