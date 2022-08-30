package login.system;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class new_login extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "GOOGLE_SIGN_IN_TAG";
    //INITIALIZATION
    TextView textView;
    EditText email, password;
    boolean passwordVisible;
    Button google;
    ImageButton back;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_system_login);

        email = findViewById(R.id.ed_username);
        password = findViewById(R.id.ed_password);

        mAuth = FirebaseAuth.getInstance();

        back = findViewById(R.id.imageCancel);

        back.setOnClickListener(v -> onBackPressed());

        //ON CLICK SIGN UP BUTTON
        View button = findViewById(R.id.btn_signup);

        button.setOnClickListener(v -> Register());
        //ON CLICK SIGN UP BUTTON END

        //ON CLICK FORGOT PASS TEXT VIEW
        textView = findViewById(R.id.tv_forgot_pass);
        textView.setOnClickListener(view -> openForgotPassActivity());
        //ON CLICK FORGOT PASS TEXT VIEW END

        // ON CLICK LOG IN BUTTON
        button = findViewById(R.id.btn_login);
        button.setOnClickListener(v -> {
            String Email = email.getText().toString();
            String pass = password.getText().toString();

            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(new_login.this, "Please enter your username", Toast.LENGTH_LONG).show();
                email.setError("Username is required");
                email.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                Toast.makeText(new_login.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                email.setError("Valid email is required");
                email.requestFocus();
            } else if (TextUtils.isEmpty(pass)) {
                Toast.makeText(new_login.this, "Please enter your password", Toast.LENGTH_LONG).show();
                password.setError("Password is required");
                password.requestFocus();
            } else {
                LoginUser(Email, pass);
            }
        });


        //For  show and hide password
        password.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                    int selection = password.getSelectionEnd();
                    if (passwordVisible) {
                        //set drawable image here
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.hidden_eyeee, 0);
                        //for hide password
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVisible = false;
                    } else {
                        //set drawable image here
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.open_eye, 0);
                        //for show password
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                    password.setSelection(selection);
                    return true;
                }
            }
            return false;

        });

        google = findViewById(R.id.btn_google);


        //configure google sign in
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        google.setOnClickListener(v -> {

            Log.d(TAG, "onClick: begin Google SignIn");
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, RC_SIGN_IN);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google Sign in intent result");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Something went WRONG", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle: begin fireeeeee");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnSuccessListener(authResult -> {
                    Log.d(TAG, "Success");

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    assert firebaseUser != null;
                    String uid = firebaseUser.getUid();
                    String email = firebaseUser.getEmail();

                    Log.d(TAG, "Success: Email: " + email);
                    Log.d(TAG, "Success: UID: " + uid);

                    if (Objects.requireNonNull(authResult.getAdditionalUserInfo()).isNewUser()) {
                        Log.d(TAG, "Success");
                        Toast.makeText(new_login.this, "Account Created...\n" + email, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(new_login.this, "Login Successfully\n" + email, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(new_login.this, UserHompage.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> Log.d(TAG, "onFailure: Login failed: " + e.getMessage()));
    }


    //METHODS STARTS HERE
    private void LoginUser(String mail, String passw) {

        mAuth.signInWithEmailAndPassword(mail, passw).addOnCompleteListener(new_login.this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(new_login.this, "logged in successfully", Toast.LENGTH_SHORT).show();

                //get instance of the current user
                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                //Check if email is verified before user can access their profile
                assert firebaseUser != null;
                if (firebaseUser.isEmailVerified()) {
                    Toast.makeText(new_login.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new_login.this, UserHompage.class);
                    startActivity(intent);
                } else {
                    firebaseUser.sendEmailVerification();
                    mAuth.signOut(); //Sign out user
                    showAlertDialog();
                }
            } else {
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (FirebaseAuthInvalidUserException e) {
                    email.setError("User does not exist or is no longer valid. Please register again.");
                    email.requestFocus();
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    email.setError("Invalid credentials. Kindly, check and re-enter.");
                    email.requestFocus();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(new_login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void showAlertDialog() {
        //Setup the Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(new_login.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("Please verify your email now. You can't login without email verification.");

        //Open Email App if user clicks/taps continue button
        builder.setPositiveButton("Continue", (dialog, which) -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_EMAIL);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //To email app
            startActivity(intent);
        });
        //Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        /*Show AlertDialog*/
        alertDialog.show();

    }

    /*OPENING SIGN UP METHOD*/
    public void Register() {
        Intent intent = new Intent(this, new_signup.class);
        startActivity(intent);
    }
    //OPENING SIGN UP METHOD END

    //OPENING FORGOT PASS METHOD
    public void openForgotPassActivity() {
        Intent intent = new Intent(new_login.this, forgot_password.class);
        startActivity(intent);
    }
    //OPENING FORGOT PASS METHOD END
}