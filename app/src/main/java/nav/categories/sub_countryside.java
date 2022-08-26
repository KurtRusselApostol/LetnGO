package nav.categories;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class sub_countryside extends AppCompatActivity {

    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_countryside);

        back = findViewById(R.id.country_side_back);

        back.setOnClickListener(v -> onBackPressed());

    }
}