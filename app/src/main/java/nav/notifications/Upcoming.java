package nav.notifications;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Upcoming extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_upcoming);

        back = findViewById(R.id.upcoming_back);

        back.setOnClickListener(v -> onBackPressed());
    }
}