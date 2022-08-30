package nav.account.safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class safety_center extends AppCompatActivity {
    private Button advice;
    private Button guidance;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_safety_center);

        advice = findViewById(R.id.btn_advice);
        guidance = findViewById(R.id.btn_guidance);
        back = findViewById(R.id.back);

        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advice_click();
            }
        });

        guidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guidance_click();
            }
        });

        advice = findViewById(R.id.btn_advice);
        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advice_click();
            }
        });

        back.setOnClickListener(v -> onBackPressed());

    }

        public void guidance_click() {
        Intent intent = new Intent(safety_center.this, guidance_click.class);
        startActivity(intent);
    }

        public void advice_click() {
        Intent intent = new Intent(safety_center.this, advice_click.class);
        startActivity(intent);
    }
}