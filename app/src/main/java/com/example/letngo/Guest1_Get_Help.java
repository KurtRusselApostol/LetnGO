package com.example.letngo;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Guest1_Get_Help extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest1_get_help);

        back = findViewById(R.id.btn_back_host);

        back.setOnClickListener(v -> onBackPressed());
    }
}

//test commit & push 2