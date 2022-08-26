package nav.notifications;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Travel_Offers extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_offers);

        back = findViewById(R.id.travel_offers_back);

        back.setOnClickListener(v -> onBackPressed());
    }
}