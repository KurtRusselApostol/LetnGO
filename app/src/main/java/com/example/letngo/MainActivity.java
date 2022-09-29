package com.example.letngo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import login.system.GLoginChecker;
import login.system.LoginInfoLocal;
import login.system.User;
import login.system.UserHompage;
import nav.categories.HomePage;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Objects.requireNonNull(getSupportActionBar()).hide(); //code might be important later
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {

            try {
                //Checks for Google Sign In status
                GLoginChecker gLoginChecker = new GLoginChecker(MainActivity.this);
                boolean gLogged = gLoginChecker.getStatGLogin();
                if (gLogged) {
                    GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);
                    if (lastSignedInAccount != null) {
                        Intent intent = new Intent(MainActivity.this, UserHompage.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    //Checks for regular logged in user (Persistent Login/Session Saving)
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
                                mAuth.signOut();
                            }
                        }
                    });
                }
            }

            //If there are no saved logins from both Google Sign In and Persistent Login/Session Saving
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


