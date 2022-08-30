package nav.account.safety;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class guidance_click extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety_guidance_click);


        back = findViewById(R.id.back_btn2);

        back.setOnClickListener(v -> onBackPressed());
    }

}