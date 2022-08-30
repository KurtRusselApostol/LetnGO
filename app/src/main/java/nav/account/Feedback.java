package nav.account;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_feedback);

        ImageButton backButton = findViewById(R.id.back_btn);

        backButton.setOnClickListener(v -> onBackPressed());
    }
}