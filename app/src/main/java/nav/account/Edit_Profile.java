package nav.account;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.HashMap;

public class Edit_Profile extends AppCompatActivity {

    EditText Firstname;
    EditText Lastname;
    EditText Email;
    EditText Birthday;
    EditText ContactNo;
    EditText Age;
    EditText Gender;
    EditText Address;
    EditText Description;
    EditText BankAccount;
    EditText BankNumber;
    ImageButton back;
    Button update;
    DatabaseReference reference;

    private Button captureTxt;
    private String path;
    private Uri uri;
    private StorageTask uploadTask;
    private ImageView captureImage;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageProfilePic;

    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();


    String userUid;
    {
        assert currentUser != null;
        userUid = currentUser.getUid();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit_profile);

        back = findViewById(R.id.editprofile_back);
        update = findViewById(R.id.btn_update);
        mAuth = FirebaseAuth.getInstance();


        //Hooks
        captureImage = findViewById(R.id.my_avatar);
        captureTxt = findViewById(R.id.gallery_Pick);
        Firstname = findViewById(R.id.ed_Fname);
        Lastname = findViewById(R.id.ed_Lname);
        Email = findViewById(R.id.ed_email);
        Birthday = findViewById(R.id.ed_birthday);
        ContactNo = findViewById(R.id.ed_contact_no);
        Age = findViewById(R.id.ed_Age);
        Gender = findViewById(R.id.ed_Gender);
        Address = findViewById(R.id.ed_Address);
        Description = findViewById(R.id.ed_Description);
        BankAccount = findViewById(R.id.ed_bank_account);
        BankNumber = findViewById(R.id.ed_bank_number);



        reference = FirebaseDatabase.getInstance().getReference("User_account");
        reference.child(userUid).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                if (task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();

                    databaseReference = FirebaseDatabase.getInstance().getReference().child("User_account");
                    storageProfilePic = FirebaseStorage.getInstance().getReference().child("Profile Picture");
                    String profilePic = String.valueOf(dataSnapshot.child("image").getValue());






                    String firstname = String.valueOf(dataSnapshot.child("FirstName").getValue());
                    String lastname = String.valueOf(dataSnapshot.child("LastName").getValue());
                    String email = String.valueOf(dataSnapshot.child("Email").getValue());
                    String birthday = String.valueOf(dataSnapshot.child("Birthday").getValue());

                    String mobile = String.valueOf(dataSnapshot.child("Mobile").getValue());
                    String age = String.valueOf(dataSnapshot.child("Age").getValue());
                    String gender = String.valueOf(dataSnapshot.child("Gender").getValue());
                    String address = String.valueOf(dataSnapshot.child("Address").getValue());

                    String description = String.valueOf(dataSnapshot.child("Description").getValue());


                    Firstname.setText(firstname);
                    Lastname.setText(lastname);
                    Email.setText(email);
                    Email.setEnabled(false); //make email field uneditable
                    Birthday.setText(birthday);
                    ContactNo.setText(mobile);
                    Age.setText(age);
                    Gender.setText(gender);
                    Address.setText(address);
                    Description.setText(description);

                } else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(v -> onBackPressed());
        update.setOnClickListener(view -> {
            reference = FirebaseDatabase.getInstance().getReference("User_account");
            reference.child(userUid).get().addOnCompleteListener(task -> {
                task.getResult();
                /**
                 * can only show one result!
                 */
                if (Firstname.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "First Name must not be empty", Toast.LENGTH_LONG).show();
                    Firstname.requestFocus();
                }
                else if (Lastname.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Last Name must not be empty", Toast.LENGTH_LONG).show();
                    Lastname.requestFocus();
                }
                else if (Birthday.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Birthday must not be empty", Toast.LENGTH_LONG).show();
                    Birthday.requestFocus();
                }
                else if (ContactNo.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Contact No. must not be empty", Toast.LENGTH_LONG).show();
                    ContactNo.requestFocus();
                }
                else if (ContactNo.getText().toString().length() != 10) {
                    Toast.makeText(getApplicationContext(), "Contact No. must be 10 digits", Toast.LENGTH_LONG).show();
                    ContactNo.requestFocus();
                }
                else if (Age.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Age must be not be empty", Toast.LENGTH_LONG).show();
                    Age.requestFocus();
                }
                else if (Integer.parseInt(Age.getText().toString()) <= 18) {
                    Toast.makeText(getApplicationContext(), "Age must be equal or greater than 18", Toast.LENGTH_LONG).show();
                    Age.requestFocus();
                }
                else if (Gender.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Gender must not be empty", Toast.LENGTH_LONG).show();
                    Gender.requestFocus();
                }
                else if (Description.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Description must not be empty", Toast.LENGTH_LONG).show();
                    Description.requestFocus();
                } else{
                    reference.child(userUid).child("FirstName").setValue(Firstname.getText().toString());
                    reference.child(userUid).child("LastName").setValue(Lastname.getText().toString());
                    reference.child(userUid).child("Birthday").setValue(Birthday.getText().toString());
                    reference.child(userUid).child("Age").setValue(Age.getText().toString());
                    reference.child(userUid).child("Gender").setValue(Gender.getText().toString());
                    reference.child(userUid).child("Address").setValue(Address.getText().toString());
                    reference.child(userUid).child("Description").setValue(Description.getText().toString());
                    uploadProfileImage();
                    Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Edit_Profile.class);
                    finish();
                    startActivity(i);
                }

            });

        });

        // gets the picture
        /** DONT UPDATE THE DEPENDECY OF IMAGEPICKER! */
        captureTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Edit_Profile.this)
                        .crop()
                        .galleryOnly()
                        .maxResultSize(1000, 1000)
                        .start(101);
            }
        });

        getUserPic();
    }

    private void getUserPic(){
        reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount() > 0){
                    if(snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(captureImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    // sets the picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            captureImage.setImageURI(uri);
        } else {
            Toast.makeText(getApplicationContext(), "No image selected!", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadProfileImage() {

        if(uri != null){
            final StorageReference fileRef = storageProfilePic
                    .child(mAuth.getCurrentUser().getUid()+".jpg");
            uploadTask = fileRef.putFile(uri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Uri downloadUrl = (Uri) task.getResult();
                        path = downloadUrl.toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("image",path);
                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                    }
                }
            });
        }
        else{

            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }
    }
}


