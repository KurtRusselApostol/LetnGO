package nav.account.security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class security extends AppCompatActivity {
    private ImageButton button, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        btn_back = (ImageButton) findViewById(R.id.back);

        button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensecurity1();
            }
        });

        btn_back.setOnClickListener(v -> onBackPressed());

    }

    public void opensecurity1() {
        Intent intent = new Intent(security.this, security1.class);
        startActivity(intent);
    }


}

