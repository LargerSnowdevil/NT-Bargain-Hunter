package com.example.ntbargainhunter;

import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class AccountRegistrationUnitTest {

    public FirebaseAuth mAuth;
    @Test
    // check if user enters a valid email address and valid password it will pass the registraion
    
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {

        mAuth.createUserWithEmailAndPassword("example@example.com", "Testpassword234!").isSuccessful();
    }
}
