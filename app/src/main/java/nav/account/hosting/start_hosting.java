package nav.account.hosting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.letngo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class start_hosting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private EditText full_name, email, phone, password;
    String firstname_user, lastname_user, fullname_user, email_user, password_user, contactnumber_user;
    Spinner spinner;
    TextView next;
    ImageView host_photo, cancel;
    CountryCodePicker countryCodePicker;
    private ImageView img_host_id;
    private Button upload_pic;
    //public Uri imageURI;
    private Uri filepath;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    //private StorageReference storageIdPic;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    Bitmap capturedImage;

    String selectedImagePath;



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String userUid;
    {
        assert currentUser != null;
        userUid = currentUser.getUid();
    }


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Host_Account").child("Host_Profile");
    StorageReference storageIdPic = FirebaseStorage.getInstance().getReference().child("ID Picture");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_hosting_start_hosting);


        //SETTING INITIALIZATIONS FOR XML
        full_name = findViewById(R.id.ed_host_full_name);
        email = findViewById(R.id.ed_host_email);
        password = findViewById(R.id.ed_host_matchPass);
        phone = findViewById(R.id.ed_host_mobile);
        countryCodePicker = findViewById(R.id.ccp_host);
        spinner = findViewById(R.id.spinner_government_id);
        host_photo = findViewById(R.id.img_host_id);
        cancel = findViewById(R.id.img_Cancel);
        next = findViewById(R.id.tv_next);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        img_host_id = findViewById(R.id.img_host_id);
        upload_pic = findViewById(R.id.upload_pic);


        System.out.println(userUid);

        mAuth = FirebaseAuth.getInstance();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //GETTING THE DATA OF THE CURRENT USER USING HIS/HER ID
        reference = FirebaseDatabase.getInstance().getReference("User_account");
        reference.child(userUid).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                if (task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();

                    firstname_user = String.valueOf(dataSnapshot.child("FirstName").getValue());
                    lastname_user = String.valueOf(dataSnapshot.child("LastName").getValue());
                    fullname_user = firstname_user + " " + lastname_user;
                    email_user = String.valueOf(dataSnapshot.child("Email").getValue());
                    password_user = String.valueOf(dataSnapshot.child("Password").getValue());
                    contactnumber_user = String.valueOf(dataSnapshot.child("Mobile").getValue());

                    //SETTING THE DEFAULT VALUES FOR EDITTEXT THAT CAME FROM USER FETCHED DATA
                    full_name.setText(fullname_user);
                    email.setText(email_user);
                    phone.setText(contactnumber_user);

                    //SET THE FIELD UNEDITABLE

                    String full_name_editText = full_name.getText().toString();
                    if(!full_name_editText.matches("")) {
                        full_name.setEnabled(false);
                    }
                    email.setEnabled(false);

                } else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //SAVING FIRST PART OF HOSTING
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                String h_fn = full_name.getText().toString();
                String h_em = email.getText().toString();
                String h_pass = password.getText().toString();
                String h_mb = phone.getText().toString();
                String h_id = spinner.getSelectedItem().toString();
                //String h_ccp = countryCodePicker.getSelectedCountryCodeWithPlus().toString();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                String h_fullname = "[a-zA-Z ]";
                Matcher h_fullnameMatcher;
                Pattern mobilePattern = Pattern.compile(h_fullname);
                h_fullnameMatcher = mobilePattern.matcher(h_fn);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (!h_pass.equals(password_user)){
                    Toast.makeText(start_hosting.this, "Your password is incorrect", Toast.LENGTH_LONG).show();
                    password.setError("Password is incorrect");
                    password.requestFocus();
                }
                else if (TextUtils.isEmpty(h_fn)){
                    Toast.makeText(start_hosting.this, "Please enter your Full Name", Toast.LENGTH_LONG).show();
                    full_name.setError("Full Name is required");
                    full_name.requestFocus();
                }
                else if (!h_fullnameMatcher.find())
                {
                    Toast.makeText(start_hosting.this, "Please only enter letters.", Toast.LENGTH_SHORT).show();
                    full_name.setError("No other characters allowed");
                    full_name.requestFocus();
                }
                else if (TextUtils.isEmpty(h_em)){
                    Toast.makeText(start_hosting.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(h_em).matches()){
                    Toast.makeText(start_hosting.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                    email.setError("Valid email is required");
                    email.requestFocus();

                }
                else if (TextUtils.isEmpty(h_mb)){
                    Toast.makeText(start_hosting.this, "Please enter your mobile no.", Toast.LENGTH_LONG).show();
                    phone.setError("Mobile no. is required");
                    phone.requestFocus();
                }
                else if (h_mb.length() != 10){
                    Toast.makeText(start_hosting.this, "Please re-enter your mobile no.", Toast.LENGTH_LONG).show();
                    phone.setError("Valid Mobile no. should be 10 digits");
                    phone.requestFocus();
                }
                else {
                    //host_Registration();
                    movetoNextPage();
                }

            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        cancel.setOnClickListener(v -> onBackPressed());

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // CALLING SPINNER METHOD
        dropdownSpinner();

        //REQUEST FOR CAMERA PERMISSION
        if (ContextCompat.checkSelfPermission(start_hosting.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(start_hosting.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPENS CAMERA
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });
//        upload_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImagePicker.Companion.with(start_hosting.this)
//                        .crop()
//                        .provider(ImageProvider.BOTH)
//                        //.galleryOnly()
//                        .maxResultSize(1000, 1000)
//                        .start(101);
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            //DISPLAYS THE PICTURE

            //Using imageURI
            //imageURI = data.getData();
            //img_host_id.setImageURI(imageURI);

            filepath = data.getData();

            //Using bitmap
            capturedImage = (Bitmap) data.getExtras().get("data");
            img_host_id.setImageBitmap(capturedImage);

            //uploadPicture();
        }
        else {
            Toast.makeText(getApplicationContext(), "No image selected!", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomkey = UUID.randomUUID().toString();

        StorageReference mountainsRef = storageReference.child("image/*" + randomkey);

        mountainsRef.putFile(imageURI)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to Upload", Toast.LENGTH_LONG).show();;
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100* taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentage" + (int)progressPercent + "%");
                    }
                });



    }*/
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*private void uploadProfileImage() {

        if(capturedImage != null){
            final StorageReference fileRef = storageIdPic
                    .child(mAuth.getCurrentUser().getUid()+".jpg");
            uploadTask = fileRef.putFile(capturedImage);
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
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void host_Registration()
    {
        String h_fn = full_name.getText().toString();
        String h_em = email.getText().toString();
        String h_mb = phone.getText().toString();
        String h_ccp = countryCodePicker.getSelectedCountryName();
        String h_id = spinner.getSelectedItem().toString();

        //HASH MAP
        HashMap<String, String> hostMap = new HashMap<>();
        hostMap.put("Full_Name", h_fn);
        hostMap.put("Email", h_em);
        hostMap.put("Mobile", h_mb);
        hostMap.put("Country", h_ccp);
        hostMap.put("Gov_ID", h_id);

        reference.push().setValue(hostMap);
        Toast.makeText(start_hosting.this, "Host Profile Created!", Toast.LENGTH_SHORT).show();
        movetoNextPage();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//--------------------------------------ALL METHODS STARTS HERE------------------------------------------------------//


    public void movetoNextPage()
    {
        startActivity(new Intent(getApplicationContext(), hosting_next.class));
        finish();
    }

    //SPINNER METHOD START
    public void dropdownSpinner()
    {
        Spinner spinner = findViewById(R.id.spinner_government_id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(start_hosting.this, R.array.government_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(start_hosting.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
  //  SPINNER METHOD END

    // HOST EMAIL VALIDATION
    private boolean isValidEmail(String email)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

