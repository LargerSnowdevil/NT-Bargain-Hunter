package com.example.ntbargainhunter;

import android.content.Intent;
<<<<<<< HEAD
=======
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 758a0a45ecd0e52493fb6c05631e789cec139728
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class RegisterPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText usernameEntry;
    private EditText passwordEntry;
    private EditText confirmPasswordEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mAuth = FirebaseAuth.getInstance();

        usernameEntry = findViewById(R.id.registerEmailEntry);
        passwordEntry = findViewById(R.id.registerPasswordEntry);
        confirmPasswordEntry = findViewById(R.id.registerConfirmPasswordEntry);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //todo if there is a user logged in
            //go home?
        }
    }

    public void goToSignInPage(View v) {
        Intent i = new Intent(this, SignInPage.class);
        startActivity(i);
    }

    public void register(View v){
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        String confirmPassword = confirmPasswordEntry.getText().toString();

        //todo input validation

        if (password.equals(confirmPassword)) {
            mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(RegisterPage.this, HomePage.class);
                        Toast.makeText(RegisterPage.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    } else {
                        //todo what happens when the login fails
                        Toast.makeText(RegisterPage.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
