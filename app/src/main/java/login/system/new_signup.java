package login.system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.example.letngo.ReadWriteUserDeatils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class new_signup extends AppCompatActivity {

    //get data form edit text into string variables
    private EditText username, email, mobile,  password , confirmPass;
    Button button, back;
    boolean passwordVisible;
    private CountryCodePicker countryCodePicker;
    private static final String TAG = "new_signup";


    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"); //Minimum eight characters, at least one letter and one number
//            Pattern.compile("^" +
//                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
//                    "(?=\\S+$)" +            // no white spaces
//                    ".{6,}" +                // at least 6 characters
//                    "$");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);

        username = findViewById(R.id.ed_username);
        email = findViewById(R.id.ed_email);
        mobile = findViewById(R.id.ed_mobile);
        password = findViewById(R.id.ed_password);
        confirmPass = findViewById(R.id.ed_cpassword);
        countryCodePicker = findViewById(R.id.ccp);
        button = findViewById(R.id.btn_signup);
        back = findViewById(R.id.btn_back);



        //Show & Hide password action
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX() >= password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if (passwordVisible){
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden, 0);
                            //for hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else {
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_1, 0);
                            //for show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;

                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        confirmPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX() >= confirmPass.getRight()- confirmPass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if (passwordVisible){
                            //set drawable image here
                            confirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden, 0);
                            //for hide password
                            confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else {
                            //set drawable image here
                            confirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_1, 0);
                            //for show password
                            confirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;

                        }
                        confirmPass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }

        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtain data entered
                String un = username.getText().toString();
                String em = email.getText().toString();
                String mb = mobile.getText().toString();
                String pass = password.getText().toString();
                String cpassword = confirmPass.getText().toString();
                String ccpicker = countryCodePicker.getSelectedCountryCode();
                String country = countryCodePicker.getSelectedCountryEnglishName();

                String fn = "";
                String ln = "";
                String bd = "";
                String ag = "";
                String gd = "";
                String ad = "";
                String dc = "";
                String ba = "";
                String bn = "";


                //Validate Mobile number using matcher and pattern(Regular expression)
                String mobilenum = "[6-9][0-9]{9}"; //First no. can be {6,8,9} and rest 9 nos. can be any no.
                Matcher mobileMatcher;
                Pattern mobilePattern = Pattern.compile(mobilenum);
                mobileMatcher = mobilePattern.matcher(mb);

                if (TextUtils.isEmpty(un)){
                    Toast.makeText(new_signup.this, "Please enter your username", Toast.LENGTH_LONG).show();
                    username.setError("Username is required");
                    username.requestFocus();
                }
                else if (TextUtils.isEmpty(em)){
                    Toast.makeText(new_signup.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                    Toast.makeText(new_signup.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                    email.setError("Valid email is required");
                    email.requestFocus();
                }
                else if (TextUtils.isEmpty(mb)){
                    Toast.makeText(new_signup.this, "Please enter your mobile no.", Toast.LENGTH_LONG).show();
                    mobile.setError("Mobile no. is required");
                    mobile.requestFocus();
                }
                else if (mb.length() != 10){
                    Toast.makeText(new_signup.this, "Please re-enter your mobile no.", Toast.LENGTH_LONG).show();
                    mobile.setError("Valid Mobile no. should be 10 digits");
                    mobile.requestFocus();
                }
                else if (!mobileMatcher.find()){
                    Toast.makeText(new_signup.this, "Please re-enter your mobile no.", Toast.LENGTH_LONG).show();
                    mobile.setError("Mobile number is not valid");
                    mobile.requestFocus();
                }
                else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(new_signup.this, "Please enter your password", Toast.LENGTH_LONG).show();
                    password.setError("Password is required");
                    password.requestFocus();
                }
                else if (!PASSWORD_PATTERN.matcher(pass).matches()){
                    Toast.makeText(new_signup.this, "Password should be at least 8 characters with at least one letter and one number", Toast.LENGTH_LONG).show();
                    password.setError("Password too weak");
                    password.requestFocus();
                }
                else if (TextUtils.isEmpty(cpassword)){
                    Toast.makeText(new_signup.this, "Please confirm your password", Toast.LENGTH_LONG).show();
                    confirmPass.setError("Password confirmation is required");
                    confirmPass.requestFocus();
                }
                else if (!pass.equals(cpassword)){
                    Toast.makeText(new_signup.this, "Password not match!", Toast.LENGTH_LONG).show();
                    confirmPass.setError("Password confirmation is required");
                    confirmPass.requestFocus();

                    //Clear entered password
                    password.clearComposingText();
                    confirmPass.clearComposingText();
                }
                else{
                    registerUser(un, em, mb, pass, fn, ln, bd, ag, gd, ad, dc);

                }


            }

        });


    }

    //Register User using the credentials given
    private void registerUser(String un, String em, String mb, String pass, String fn, String ln, String bd, String ag, String gd, String ad, String dc) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        //Create User Profile
        auth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new_signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    //Update Display Name of User
                    //UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(un).build();
                    //firebaseUser.updateProfile(profileChangeRequest);

                    //Enter user data into the firebase Realtime database
                    ReadWriteUserDeatils writeUserDetails = new ReadWriteUserDeatils(un,em, mb, pass, fn, ln, bd, ag, gd, ad, dc);

                    //Extracting User reference from Database for "User_ account"
                    DatabaseReference referenceP = FirebaseDatabase.getInstance().getReference("User_account");

                    referenceP.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                //Send Verification Email
                                firebaseUser.sendEmailVerification();
                                Toast.makeText(new_signup.this, "User successfully registered. Please verify your email", Toast.LENGTH_LONG).show();
                                moveToLogin();//ituuuu

//                                //Open Profile after successful registration
//                                Intent intent = new Intent(new_signup.this, FragmentAccount.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();

                            }
                            else {
                                Toast.makeText(new_signup.this, "User  registered failed. Please try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        password.setError("Your password is too weak. Kindly use a mix of alphabets, numbers and special characters");
                        password.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        password.setError("Your email is invalid or already in use. Kindly re-enter.");
                        password.requestFocus();
                    }catch (FirebaseAuthUserCollisionException e){
                        email.setError("User is already registered with this email. Use another email");
                        email.requestFocus();
                    }catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(new_signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }


    private void moveToLogin() {
        startActivity(new Intent(getApplicationContext(), new_login.class));
        finish();
    }
    // Added Comment
}

