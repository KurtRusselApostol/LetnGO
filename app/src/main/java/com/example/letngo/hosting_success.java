package com.example.letngo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class hosting_success extends AppCompatActivity {

    Button success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_success);

        success = findViewById(R.id.success);

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hosting_success.this, Manage_Hosting.class);
                startActivity(intent);
            }
        });
    }
}