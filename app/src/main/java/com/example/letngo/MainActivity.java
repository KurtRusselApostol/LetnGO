package com.example.letngo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import nav.categories.HomePage;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;


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
//            SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);
//
//            String ema = sp.getString("ema", null);
//            String pass = sp.getString("pass", null);

            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);



    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }



}


