package com.example.ntbargainhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
    }

    public void goToRegisterPage(View v) {
        Intent i = new Intent(this, RegisterPage.class);
        startActivity(i);
    }

    public void signIn(View v) {
        Intent i = new Intent(this, HomePage.class);
        startActivity(i);
    }
}
