package com.example.ntbargainhunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class SignInPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText usernameEntry;
    private EditText passwordEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        mAuth = FirebaseAuth.getInstance();

        usernameEntry = findViewById(R.id.signInEmailEntry);
        passwordEntry = findViewById(R.id.signInPasswordEntry);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //todo if there is a user logged in
            //maybe log the user out then load the login page so that the logout function is just coming here while logged in
        }
    }

    public void goToRegisterPage(View v) {
        Intent i = new Intent(this, RegisterPage.class);
        startActivity(i);
    }

    public void signIn(View v) {
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();

        //todo input validation

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(SignInPage.this, HomePage.class);
                    Toast.makeText(SignInPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                } else {
                    //todo what happens when the login fails
                    Toast.makeText(SignInPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
