package com.example.ntbargainhunter;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private String current_user_id;

    private FloatingActionButton addPostBtn;

    private BottomNavigationView mainbottomNav;
    public List<BargainPost> bargain_list;
    private HomeFragment homeFragment;
    private PostFragment postFragment;
    private AccountFragment accountFragment;
    private FavouriteFragment favouriteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("NT Bargains");
        if(mAuth.getCurrentUser() != null) {

            mainbottomNav = findViewById(R.id.mainBottomNav);

            // FRAGMENTS
            homeFragment = new HomeFragment();
            postFragment = new PostFragment();
            accountFragment = new AccountFragment();
            favouriteFragment = new FavouriteFragment();

            initializeFragment();

            mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

                    switch (item.getItemId()) {

                        case R.id.bottom_action_home:
                            Intent newPostInten = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(newPostInten);
                            MainActivity.this.overridePendingTransition(0,0);
                            //replaceFragment(homeFragment, currentFragment);
                            return true;

                        case R.id.bottom_action_account:

                            replaceFragment(accountFragment, currentFragment);
                            return true;

                        case R.id.bottom_action_post:


                            Intent newPostIntent = new Intent(MainActivity.this, PostBargainPage.class);
                            startActivity(newPostIntent);

                            //replaceFragment(postFragment, currentFragment);
                            return true;

                        case R.id.bottom_fav_account:

                            replaceFragment(favouriteFragment, currentFragment);
                            return true;

                        default:
                            return false;


                    }

                }
            });
//            addPostBtn = findViewById(R.id.add_post_btn);
//            addPostBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent newPostIntent = new Intent(MainActivity.this, PostBargainPage.class);
//                    startActivity(newPostIntent);
//
//                }
//            });

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){

            sendToLogin();

        } else {

            current_user_id = mAuth.getCurrentUser().getUid();

            firebaseFirestore.collection("Users").document(current_user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if(task.isSuccessful()){

                        if(!task.getResult().exists()){

                            Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
                            startActivity(setupIntent);
                            finish();

                        }

                    } else {

                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();


                    }

                }
            });

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout_btn:
                logOut();
                return true;

            case R.id.action_settings_btn:

                Intent settingsIntent = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(settingsIntent);

                return true;


            default:
                return false;


        }

    }

    private void logOut() {


        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, homeFragment);
        fragmentTransaction.add(R.id.main_container, postFragment);
        fragmentTransaction.add(R.id.main_container, accountFragment);
        fragmentTransaction.add(R.id.main_container, favouriteFragment);
        fragmentTransaction.hide(postFragment);
        fragmentTransaction.hide(accountFragment);
        fragmentTransaction.hide(favouriteFragment);
        fragmentTransaction.commit();

    }

    private void replaceFragment(Fragment fragment, Fragment currentFragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == homeFragment){

            fragmentTransaction.hide(accountFragment);
            fragmentTransaction.hide(postFragment);
            fragmentTransaction.hide(favouriteFragment);

        }

        if(fragment == accountFragment){

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(postFragment);
            fragmentTransaction.hide(favouriteFragment);

        }

        if(fragment == postFragment){

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(accountFragment);
            fragmentTransaction.hide(favouriteFragment);

        }
        if(fragment == favouriteFragment){

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(accountFragment);
            fragmentTransaction.hide(postFragment);

        }
        fragmentTransaction.show(fragment);

        //fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }

}