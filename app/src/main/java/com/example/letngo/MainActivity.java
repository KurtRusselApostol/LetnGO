package com.example.letngo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import login.system.LoginInfoLocal;
import login.system.User;
import login.system.UserHompage;
import login.system.new_login;
import nav.categories.HomePage;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {

            //Code below is only temporary. May be replaced and deleted
            //TODO Direct to UserHomepage when it logs in
            //SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);

            //String ema = sp.getString("ema", null);
            //String pass = sp.getString("pass", null);
            try {
                LoginInfoLocal loginInfoLocal = new LoginInfoLocal(MainActivity.this);
                User fetcher = loginInfoLocal.fetchLogin();
                String email = fetcher.getEma();
                String pass = fetcher.getPass();
                //Copied from new_login since using code there will return error
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(MainActivity.this, task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser firebaseUser = mAuth.getCurrentUser();

                        //Check if email is verified before user can access their profile
                        assert firebaseUser != null;
                        if (firebaseUser.isEmailVerified()) {
                            Intent intent = new Intent(MainActivity.this, UserHompage.class);
                            startActivity(intent);
                        } else {
                            firebaseUser.sendEmailVerification();
                            mAuth.signOut(); //Sign out user
                        }
                    }
                });
            }

            catch (Exception e) {
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);



    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        FirebaseAuth.getInstance().signOut();
//    }
}


