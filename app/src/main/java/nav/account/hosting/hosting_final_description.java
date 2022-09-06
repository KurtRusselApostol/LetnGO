package nav.account.hosting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class hosting_final_description extends AppCompatActivity {

    EditText title, description, offer, price, discount;
    CheckBox peaceful, family, central, unique, stylish, spacious,
            cctv, weapon, animal;
    TextView back, review;
    ImageView pAdd, pMinus, dAdd, dMinus;

    int countPrice = 0, countDiscount = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Host_Account").child("Host_Description");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_final_description);

        //INITIALIZATIONS
        title = findViewById(R.id.ed_create_title);
        description = findViewById(R.id.ed_describe_title);
        offer = findViewById(R.id.ed_offer);
        price = findViewById(R.id.ed_host_price);
        discount = findViewById(R.id.ed_discount_price);

        peaceful = findViewById(R.id.chk_peaceful);
        family = findViewById(R.id.chk_family);
        central = findViewById(R.id.chk_central);
        unique = findViewById(R.id.chk_unique);
        stylish = findViewById(R.id.chk_stylish);
        spacious = findViewById(R.id.chk_spacious);
        cctv = findViewById(R.id.chk_CCTV);
        weapon = findViewById(R.id.chk_weapon);
        animal = findViewById(R.id.chk_animals);

        back = findViewById(R.id.tv_back);
        review = findViewById(R.id.tv_next);

        pAdd = findViewById(R.id.img_price_add);
        pMinus = findViewById(R.id.img_price_minus);
        dAdd = findViewById(R.id.img_discount_add);
        dMinus = findViewById(R.id.img_discount_minus);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String h_title = title.getText().toString();
                String h_desc = description.getText().toString();
                String h_offer = offer.getText().toString();

                if (TextUtils.isEmpty(h_title)){
                    Toast.makeText(hosting_final_description.this, "Please enter a title.", Toast.LENGTH_LONG).show();
                    title.setError("Title is required");
                    title.requestFocus();
                }
                else if (TextUtils.isEmpty(h_desc)){
                    Toast.makeText(hosting_final_description.this, "Please enter a description.", Toast.LENGTH_LONG).show();
                    description.setError("Description is required");
                    description.requestFocus();
                }
                else if (TextUtils.isEmpty(h_offer)){
                    Toast.makeText(hosting_final_description.this, "Please enter a description.", Toast.LENGTH_LONG).show();
                    offer.setError("Offer Description is required");
                    offer.requestFocus();
                }
                else if(!peaceful.isChecked() && !family.isChecked() && !central.isChecked() && !unique.isChecked() && !stylish.isChecked() && !spacious.isChecked())
                {
                    Toast.makeText(hosting_final_description.this, "You haven't selected any Highlights.", Toast.LENGTH_LONG).show();
                }
                else if(!cctv.isChecked() && !weapon.isChecked() && !animal.isChecked())
                {
                    Toast.makeText(hosting_final_description.this, "You haven't selected any of these.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    hostDescriptions();

                    Intent intent = new Intent(hosting_final_description.this, hosting_checking_description.class);
                    startActivity(intent);
                }

            }
        });


        back.setOnClickListener(v -> onBackPressed());
    }

    //----------------------------------ALL METHODS STARTS HERE-------------------------------------//

    //HOST PLACE DESCRIPTION REGISTRATION METHOD
    public void hostDescriptions()
    {
        //STRINGS
        String h_title = title.getText().toString();
        String h_desc = description.getText().toString();
        String h_offer = offer.getText().toString();
        String h_price = price.getText().toString();
        String h_discount = discount.getText().toString();

        String h_peace = peaceful.getText().toString();
        String h_family = family.getText().toString();
        String h_central = central.getText().toString();
        String h_unique = unique.getText().toString();
        String h_stylish = stylish.getText().toString();
        String h_spacious = spacious.getText().toString();

        String h_cctv = cctv.getText().toString();
        String h_weapon = weapon.getText().toString();
        String h_animal = animal.getText().toString();

        //HASH MAP
        HashMap<String, String> hostMap = new HashMap<>();
        hostMap.put("Title", h_title);
        hostMap.put("Description", h_desc);
        hostMap.put("Offer Description", h_offer);
        hostMap.put("Price", h_price);
        hostMap.put("Price Discount", h_discount);

        //IF-ELSE STATEMENT FOR HIGHLIGHTS CHECK BOX
        if(peaceful.isChecked())
        {
            hostMap.put("Highlights", h_peace);
        }
        else if(family.isChecked())
        {
            hostMap.put("Highlights", h_family);
        }
        else if(central.isChecked())
        {
            hostMap.put("Highlights", h_central);
        }
        else if(unique.isChecked())
        {
            hostMap.put("Highlights", h_unique);
        }
        else if(stylish.isChecked())
        {
            hostMap.put("Highlights", h_stylish);
        }
        else if(spacious.isChecked())
        {
            hostMap.put("Highlights", h_spacious);
        }
        //
        else if(cctv.isChecked())
        {
            hostMap.put("Others", h_cctv);
        }
        else if(weapon.isChecked())
        {
            hostMap.put("Others", h_weapon);
        }
        else if(animal.isChecked())
        {
            hostMap.put("Others", h_animal);
        }

        reference.push().setValue(hostMap);
        Toast.makeText(hosting_final_description.this, "SAVED!", Toast.LENGTH_SHORT).show();
    }

    //INCREMENT METHOD
    public void incrementPrice(View v)
    {
        pAdd = findViewById(R.id.img_price_add);

        countPrice++;
        price.setText("" + countPrice);
    }

    public void incrementDiscount(View v)
    {
        dAdd = findViewById(R.id.img_discount_add);

        countDiscount++;
        discount.setText("" + countDiscount);
    }

    //DECREMENT METHOD
    public void decrementPrice(View v)
    {
        pMinus = findViewById(R.id.img_price_minus);

        if (countPrice <= 0) {
            countPrice = 0;
            Toast.makeText(hosting_final_description.this, "QUANTITY CANNOT BE LOWER THAN ZERO", Toast.LENGTH_LONG).show();
        }
        else {
            countPrice--;
            price.setText("" + countPrice);
        }
    }

    public void decrementDiscount(View v)
    {
        dMinus = findViewById(R.id.img_discount_minus);

        if (countDiscount <= 0) {
            countDiscount = 0;
            Toast.makeText(hosting_final_description.this, "QUANTITY CANNOT BE LOWER THAN ZERO", Toast.LENGTH_LONG).show();
        }
        else {
            countDiscount--;
            discount.setText("" + countDiscount);
        }
    }
}