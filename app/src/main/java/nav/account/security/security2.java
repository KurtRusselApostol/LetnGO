package nav.account.security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class security2 extends AppCompatActivity {

 Button button2;
 ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security2);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensecurity3();
            }
        });

        btn_back = (ImageButton) findViewById(R.id.button_back);

        btn_back.setOnClickListener(v -> onBackPressed());

    }


    public void opensecurity3() {
        Intent intent = new Intent(security2.this, security3.class);
        startActivity(intent);


    }
}