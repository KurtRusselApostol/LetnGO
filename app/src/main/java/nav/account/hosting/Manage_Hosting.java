package nav.account.hosting;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Manage_Hosting extends AppCompatActivity {

    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_hosting_manage_hosting);

        back = findViewById(R.id.manage_back);

        back.setOnClickListener(v -> onBackPressed());
    }
}