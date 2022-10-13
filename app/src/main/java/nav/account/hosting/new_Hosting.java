package nav.account.hosting;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letngo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.ImageView;


public class new_Hosting extends AppCompatActivity {
    ImageView back;
    Button btnInsert, btnView;
    EditText PlaceTitle,Description,Highlights,Offer_Description,Others,Price,PriceDiscount ;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hosting);

        back = findViewById(R.id.img_Cancel);
        back.setOnClickListener(v -> onBackPressed());

        back = findViewById(R.id.img_Cancel);
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        PlaceTitle = findViewById(R.id.editPlaceTitle);
        Description = findViewById(R.id.editDescription);
        Highlights = findViewById(R.id.editHighlights);
        Offer_Description = findViewById(R.id.editOfferDescription);
        Others = findViewById(R.id.editOthers);
        Price = findViewById(R.id.editPrice);
        PriceDiscount = findViewById(R.id.editPriceDiscount);
        databaseUser= FirebaseDatabase.getInstance().getReference();

        btnInsert.setOnClickListener(v -> InsertData());

        btnView.setOnClickListener(v -> {
            startActivity(new Intent(new_Hosting.this, Hostinglist.class));
            finish();
        });
    }

    private void InsertData() {
        String title=PlaceTitle.getText().toString();
        String description=Description.getText().toString();
        String highlights= Highlights.getText().toString();
        String offer_description=Offer_Description.getText().toString();
        String others=Others.getText().toString();
        String price=Price.getText().toString();
        String price_discount=PriceDiscount.getText().toString();
        String id=databaseUser.push().getKey();

        Hosting hosting= new Hosting(title,description,highlights,offer_description,others,price,price_discount);
        assert id != null;
        databaseUser.child("Host_Description").child(id).setValue(hosting)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(new_Hosting.this,"Data is inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(new_Hosting.this,"Data insert failed", Toast.LENGTH_SHORT).show();
                    }
                });



    }

}