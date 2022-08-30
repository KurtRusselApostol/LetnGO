package nav.account.support;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class letngo_support2 extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_support_letngo_support2);

        back = findViewById(R.id.back_btn);

        back.setOnClickListener(v -> onBackPressed());
    }
}