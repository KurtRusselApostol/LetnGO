package nav.account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class security1 extends AppCompatActivity {
    ImageButton button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security1);

        button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(v -> openSecurity2());

        button2 = (ImageButton) findViewById(R.id.button_back);

        button2.setOnClickListener(v -> onBackPressed());

    }

    public void openSecurity2() {
        Intent intent = new Intent(security1.this, security2.class);
        startActivity(intent);

    }
}