package nav.account.payment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

import login.system.UserHompage;

public class payment_homepage24 extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.account_payment_homepage24);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(payment_homepage24.this, UserHompage.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);


    }
}