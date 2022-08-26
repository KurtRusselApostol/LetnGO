package nav.account;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Terms_Services extends AppCompatActivity {

    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_services);

        back = findViewById(R.id.terms_back);

        back.setOnClickListener(v -> onBackPressed());
    }
}