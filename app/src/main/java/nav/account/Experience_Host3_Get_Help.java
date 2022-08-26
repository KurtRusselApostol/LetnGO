package nav.account;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Experience_Host3_Get_Help extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_host3_get_help);

        back = findViewById(R.id.btn_back_experience_host_3);

        back.setOnClickListener(v -> onBackPressed());
    }
}