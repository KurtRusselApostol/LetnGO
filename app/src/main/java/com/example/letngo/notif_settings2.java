package com.example.letngo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class notif_settings2 extends AppCompatActivity {

    TextView con;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_settings2);

        con = findViewById(R.id.textView82);
        back = findViewById(R.id.img_back);

        back.setOnClickListener(v -> onBackPressed());

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notif_settings2.this, notif_settings3.class);
                startActivity(intent);
            }
        });
    }
}