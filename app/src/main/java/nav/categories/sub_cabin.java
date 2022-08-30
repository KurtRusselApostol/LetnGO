package nav.categories;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class sub_cabin extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_sub_cabin);

        back = findViewById(R.id.cabin_back);

        back.setOnClickListener(v -> onBackPressed());
    }
}