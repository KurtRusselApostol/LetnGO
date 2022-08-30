package nav.account.notif;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class notif_settings5 extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_notif_settings5);

        back = findViewById(R.id.img_back);

        back.setOnClickListener(v -> onBackPressed());

    }
}