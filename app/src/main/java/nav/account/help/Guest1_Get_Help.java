package nav.account.help;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Guest1_Get_Help extends AppCompatActivity {

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_help_guest1_get_help);

        back = findViewById(R.id.btn_back_host);

        back.setOnClickListener(v -> onBackPressed());
    }
}

