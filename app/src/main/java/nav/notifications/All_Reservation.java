package nav.notifications;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class All_Reservation extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reservation);

        back = findViewById(R.id.all_reservation_btn);

        back.setOnClickListener(v -> onBackPressed());

    }
}