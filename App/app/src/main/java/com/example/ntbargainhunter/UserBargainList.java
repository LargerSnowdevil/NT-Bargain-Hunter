package com.example.ntbargainhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserBargainList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bargain_list);
    }

    public void goToSignInPage(View v) {
        Intent i = new Intent(this, SignInPage.class);
        startActivity(i);
    }
}
