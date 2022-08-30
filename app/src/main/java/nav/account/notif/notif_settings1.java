package nav.account.notif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class notif_settings1 extends AppCompatActivity {

    TextView save;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_notif_settings1);

        save = findViewById(R.id.textView81);
        back = findViewById(R.id.img_back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notif_settings1.this, notif_settings2.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(v -> onBackPressed());

    }
}