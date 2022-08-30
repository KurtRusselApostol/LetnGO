package nav.account.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class payment extends AppCompatActivity {


    ImageButton back;
    Button select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_payment);

        back = findViewById(R.id.activity_paymentback);
        select = findViewById(R.id.button9);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(payment.this, Payment_Homepage20.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(v -> onBackPressed());

    }
}