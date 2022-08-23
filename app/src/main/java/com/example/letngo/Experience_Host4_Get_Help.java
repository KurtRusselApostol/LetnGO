package com.example.letngo;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Experience_Host4_Get_Help extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_host4_get_help);

        back = findViewById(R.id.btn_back_experience_host_4);

        back.setOnClickListener(v -> onBackPressed());
    }
}
//testing
/* testing but multiline */