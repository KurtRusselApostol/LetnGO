package nav.account.hosting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import nav.account.FragmentAccount;

public class hosting_final_description extends AppCompatActivity {

    EditText title, description, offer, price, discount;
    CheckBox peaceful, family, central, unique, stylish, spacious;
    TextView back, review;
    ImageView pAdd, pMinus, dAdd, dMinus;
    ImageView uploadPhoto1, uploadPhoto2;
    Uri uri1, uri2;
    String highlights = "";

    int countPrice = 0, countDiscount = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Host_Description");
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

        uploadPhoto1 = findViewById(R.id.img_upload);
        uploadPhoto2 = findViewById(R.id.img_new_photo);

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
                else
                {
                    hostDescriptions();
                    Toast.makeText(hosting_final_description.this, "Your Place was successfully saved.", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(hosting_final_description.this, hosting_checking_description.class);
//                    startActivity(intent);
                }

            }
        });
        // PICTURES ARE NOT YET BEING SAVED IN THE DATABASE
        uploadPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery(101);
            }
        });

        uploadPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery(1);
            }
        });


        back.setOnClickListener(v -> onBackPressed());

    }
    //----------------------------------ALL METHODS STARTS HERE-------------------------------------//
    private void OpenGallery(int reqCode){
        ImagePicker.Companion.with(hosting_final_description.this)
                .crop()	//Crop image(Optional), Check Customization for more option
                .galleryOnly()
                .compress(1024)   //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start(reqCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            uri1 = data.getData();
            uploadPhoto1.setImageURI(uri1);
        }
        else if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            uri2 = data.getData();
            uploadPhoto2.setImageURI(uri2);
        }
        else {
            Toast.makeText(getApplicationContext(), "No image selected!", Toast.LENGTH_SHORT).show();
        }
    }

//    private void uploadProfileImage(Uri uri) {
//
//        if(uri != null){
//            final StorageReference fileRef = storageProfilePic
//                    .child(mAuth.getCurrentUser().getUid()+".jpg");
//            uploadTask = fileRef.putFile(uri);
//            uploadTask.continueWithTask(new Continuation() {
//                @Override
//                public Object then(@NonNull Task task) throws Exception {
//                    if (!task.isSuccessful()){
//                        throw  task.getException();
//                    }
//                    return fileRef.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task task) {
//                    if (task.isSuccessful()){
//                        Uri downloadUrl = (Uri) task.getResult();
//                        path = downloadUrl.toString();
//
//                        HashMap<String, Object> userMap = new HashMap<>();
//                        userMap.put("image",path);
//                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
//                    }
//                }
//            });
//        }
//        else{
//
//            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
//        }
//    }




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

        //HASH MAP
        HashMap<String, String> hostMap = new HashMap<>();
        hostMap.put("Title", h_title);
        hostMap.put("Description", h_desc);
        hostMap.put("Offer Description", h_offer);
        hostMap.put("Price", h_price);
        hostMap.put("Price Discount", h_discount);

        //IF-ELSE STATEMENT FOR HIGHLIGHTS CHECK BOX
        if(peaceful.isChecked()) highlights += ", " + h_peace;
        if(family.isChecked()) highlights += ", " + h_family;
        if(central.isChecked()) highlights += ", " + h_central;
        if(unique.isChecked()) highlights += ", " + h_unique;
        if(stylish.isChecked()) highlights += ", " + h_stylish;
        if(spacious.isChecked()) highlights += ", " + h_spacious;

        if (!Objects.equals(highlights, "")){
            highlights = highlights.substring(2);
            hostMap.put("Highlights", highlights);
        }
        else{
            hostMap.put("Highlights", "None");
        }

        //

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