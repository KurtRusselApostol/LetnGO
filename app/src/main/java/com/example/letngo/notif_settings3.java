package com.example.letngo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class notif_settings3 extends AppCompatActivity {

    ImageButton back, payments, credits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_settings3);

        back = findViewById(R.id.backButton);
        payments = findViewById(R.id.your_payments);
        credits = findViewById(R.id.credits_coupon);

        back.setOnClickListener(v -> onBackPressed());

        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notif_settings3.this, notif_settings4.class);
                startActivity(intent);
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notif_settings3.this, notif_settings5.class);
                startActivity(intent);
            }
        });
    }
}