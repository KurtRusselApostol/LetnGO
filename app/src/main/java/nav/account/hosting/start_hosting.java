package nav.account.hosting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.github.drjacky.imagepicker.ImagePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nav.account.Edit_Profile;

public class start_hosting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private EditText full_name, email, phone, password;
    Spinner spinner;
    TextView next;
    ImageView host_photo,back;
    CountryCodePicker countryCodePicker;
    private Button img_host_id;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Host_Account").child("Host_Profile");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_hosting_start_hosting);

        //SETTING INITIALIZATIONS
        full_name = findViewById(R.id.ed_host_full_name);
        email = findViewById(R.id.ed_host_email);
        password = findViewById(R.id.ed_host_matchPass);
        phone = findViewById(R.id.ed_host_mobile);
        countryCodePicker = findViewById(R.id.ccp_host);
        spinner = findViewById(R.id.spinner_government_id);
        host_photo = findViewById(R.id.img_host_id);
        back = findViewById(R.id.img_Cancel);
        next = findViewById(R.id.tv_next);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //SAVING FIRST PART OF HOSTING
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                String h_fn = full_name.getText().toString();
                String h_em = email.getText().toString();
                String h_mb = phone.getText().toString();
                String h_id = spinner.getSelectedItem().toString();
                //String h_ccp = countryCodePicker.getSelectedCountryCodeWithPlus().toString();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                String h_fullname = "[a-zA-Z ]";
                Matcher h_fullnameMatcher;
                Pattern mobilePattern = Pattern.compile(h_fullname);
                h_fullnameMatcher = mobilePattern.matcher(h_fn);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                if (TextUtils.isEmpty(h_fn)){
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
                    host_Registration();
                }

            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        back.setOnClickListener(v -> onBackPressed());

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // CALLING SPINNER METHOD
        dropdownSpinner();

        // gets the picture
        /** DONT UPDATE THE DEPENDECY OF IMAGEPICKER! */
        img_host_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(start_hosting.this)
                        .crop()
                        .galleryOnly()
                        .maxResultSize(1000, 1000)
                        .start(101);
            }
        });
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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


    public  void movetoNextPage()
    {
        startActivity(new Intent(getApplicationContext(), com.example.letngo.hosting_next.class));
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

