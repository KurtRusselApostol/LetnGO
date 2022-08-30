package nav.notifications;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Checking_out extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_checking_out);
        back = findViewById(R.id.checkout_back);

        back.setOnClickListener(v -> onBackPressed());
    }
}
//
//
//