package nav.account;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Host1_Get_Help extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host1_get_help);

        back = findViewById(R.id.btn_back_guest_4);

        back.setOnClickListener(v -> onBackPressed());
    }
}