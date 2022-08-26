package nav.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class security3 extends AppCompatActivity {

    Button button4;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security3);

        button4 = (Button) findViewById(R.id.button3);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensecurity2();
            }
        });

        btn_back = (ImageButton) findViewById(R.id.button_back);

        btn_back.setOnClickListener(v -> onBackPressed());

    }
    public void opensecurity2() {
        Intent intent = new Intent(security3.this, FragmentAccount.class);
        startActivity(intent);

    }
}