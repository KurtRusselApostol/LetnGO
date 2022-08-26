package nav.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Payment_Homepage20 extends AppCompatActivity {
    ImageButton back;
    Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_homepage20);

        back = findViewById(R.id.activity_payment_homepage20_back);
        select = findViewById(R.id.button11);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment_Homepage20.this, payment_homepage21.class);
                startActivity(intent);
            }
        });


        back.setOnClickListener(v -> onBackPressed());

    }
}