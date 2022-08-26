package nav.account.safety;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class advice_click extends AppCompatActivity {
//eto sir?
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_click);

        back = findViewById(R.id.back_btn1); //back button

        back.setOnClickListener(v -> onBackPressed());
    }
}