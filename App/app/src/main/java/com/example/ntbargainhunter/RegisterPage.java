//package com.example.ntbargainhunter;
//
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//public class RegisterPage extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    private DatabaseReference mDatabase;
//
//    private EditText usernameEntry;
//    private EditText nameEntry;
//    private EditText passwordEntry;
//    private EditText confirmPasswordEntry;
//
//    private TextView usernameError;
//    private TextView nameError;
//    private TextView passwordError;
//    private TextView confirmPasswordError;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register_page);
//
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        usernameEntry = findViewById(R.id.registerEmailEntry);
//        nameEntry = findViewById(R.id.registerNameEntry);
//        passwordEntry = findViewById(R.id.registerPasswordEntry);
//        confirmPasswordEntry = findViewById(R.id.registerConfirmPasswordEntry);
//
//        usernameError = findViewById(R.id.registerEmailProblemDecription);
//        nameError = findViewById(R.id.registerNameProblemDescription);
//        passwordError = findViewById(R.id.registerPasswordProblemDescription);
//        confirmPasswordError = findViewById(R.id.registerConfirmPasswordProblemDescription);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            //todo if there is a user logged in
//            //go home?
//        }
//    }
//
//    public void goToSignInPage(View v) {
//        Intent i = new Intent(this, SignInPage.class);
//        startActivity(i);
//    }
//
//    public void register(View v){
//        String username = usernameEntry.getText().toString();
//        String name = nameEntry.getText().toString();
//        String password = passwordEntry.getText().toString();
//        String confirmPassword = confirmPasswordEntry.getText().toString();
//
//        Boolean valid = true;
//
//        //todo input validation
//        if (username.equals("")) {
//            valid = false;
//            //note on screen that username is empty
//            usernameError.setText("Please enter an E-mail.");
//            usernameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
//        Matcher mat = pattern.matcher(username);
//        if (!mat.matches()) {
//            valid = false;
//            //the email is not a valid format
//            usernameError.setText("Please enter a valid address E-mail.");
//            usernameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        if (username.length() > 30) {
//            valid = false;
//            //note on screen that username is to long
//            usernameError.setText("E-mail must be less than 30 characters.");
//            usernameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        if (name.length() > 20) {
//            valid = false;
//            //note on screen that username is to long
//            nameError.setText("Name must be less than 20 characters.");
//            nameError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        if (password.equals("")) {
//            valid = false;
//            //note on screen that password is empty
//            passwordError.setText("Please enter a password.");
//            passwordError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        if (password.length() > 20) {
//            valid = false;
//            //note on screen that password is to long
//            passwordError.setText("Password must be less than 20 characters.");
//            passwordError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        if (confirmPassword.equals("")) {
//            valid = false;
//            //todo note on screen that confirm password is empty
//            confirmPasswordError.setText("Please confirm password.");
//            confirmPasswordError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//        if (!password.equals(confirmPassword)) {
//            valid = false;
//            //if the password and confirm password fields don't match
//            confirmPasswordError.setText("Passwords don't match.");
//            confirmPasswordError.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
//
//        if (valid) {
//            mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        Intent i = new Intent(RegisterPage.this, MainActivity.class);
//
//                        addUserInfoToDatabase(task.getResult().getUser().getUid(), nameEntry.getText().toString(), usernameEntry.getText().toString());
//
//                        Toast.makeText(RegisterPage.this, "Sign-up Successful", Toast.LENGTH_SHORT).show();
//                        startActivity(i);
//                    } else {
//                        //todo what happens when the login fails
//                        // answer : show the error to user on the screen
//                        Toast.makeText(RegisterPage.this, "Signup Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//
//
//    }
//
//    private void addUserInfoToDatabase(String userID, String name, String email) {
//        User user = new User(name, email);
//        mDatabase.child("Users").child(userID).setValue(user);
//
//        // for single data point change
//        //mDatabase.child("users").child(userId).child("username").setValue(name);
//
//    }
//    // user can cancel registration process
//    public void cancel(View v) {
//        Intent i = new Intent(RegisterPage.this, SignInPage.class);
//        startActivity(i);
//    }
//
//    public String getText() {
//        String EditText = this.getText();
//        return EditText;
//    }
//    //user clears form input
//    public void clearForm(View v) {
//        EditText firstField = (EditText)this.findViewById(R.id.registerPasswordEntry);
//        EditText secondField = (EditText)this.findViewById(R.id.registerConfirmPasswordEntry);
//        EditText thirdField = (EditText)this.findViewById(R.id.registerNameEntry);
//        EditText fourthField = (EditText)this.findViewById(R.id.registerEmailEntry);
//        //...etc...
//        if (firstField != null) firstField.setText("");
//        if (secondField != null) secondField.setText("");
//        if (secondField != null) thirdField.setText("");
//        if (secondField != null) fourthField.setText("");
//
//    }
//
//}
