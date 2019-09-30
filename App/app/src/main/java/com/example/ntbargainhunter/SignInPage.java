package com.example.ntbargainhunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText usernameEntry;
    private EditText passwordEntry;
    private TextView usernameError;
    private TextView passwordError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        mAuth = FirebaseAuth.getInstance();

        usernameEntry = findViewById(R.id.signInEmailEntry);
        passwordEntry = findViewById(R.id.signInPasswordEntry);
        usernameError = findViewById(R.id.signInPageEmailProblemDescription);
        passwordError = findViewById(R.id.signInPasswordProblemDescription);
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

    public void signIn(View v) {
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        Boolean valid = true;

        //todo input validation
        if (username.equals("")) {
            valid = false;
            //note on screen that username is empty
            usernameError.setText("Please enter an E-mail.");
            usernameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(username);
        if (!mat.matches()) {
            valid = false;
            //the email is not a valid format
            usernameError.setText("Please enter a valid address E-mail.");
            usernameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (username.length() > 30) {
            valid = false;
            //note on screen that username is to long
            usernameError.setText("E-mail must be less than 30 characters.");
            usernameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (password.equals("")) {
            valid = false;
            //note on screen that password is empty
            passwordError.setText("Please enter a password.");
            passwordError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (password.length() > 20) {
            valid = false;
            //todo note on screen that password is to long
            passwordError.setText("Password must be less than 20 characters.");
            passwordError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(SignInPage.this, HomePage.class);
                    Toast.makeText(SignInPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "createUserWithEmail:success");
                    startActivity(i);
                } else {
                    //todo what happens when the login fails
                    Toast.makeText(SignInPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    //if user can not be found on database redirect to register-user page
    public void goToRegisterPage(View v) {
        Intent i = new Intent(this, RegisterPage.class);
        startActivity(i);
    }

    /**
     * Form validations
     * */

}
