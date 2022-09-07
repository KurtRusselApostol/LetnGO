package nav.account.hosting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

public class Manage_Hosting extends AppCompatActivity {

    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_hosting_manage_hosting);

        back = findViewById(R.id.img_Cancel);
        Button button = findViewById(R.id.add_hosting);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Manage_Hosting.this,new_Hosting.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(v -> onBackPressed());

    }


}