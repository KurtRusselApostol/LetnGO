package com.example.letngo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class sharing_privacy extends AppCompatActivity {
    private EditText pass, password;
    private ProgressBar processing;
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private TextView verification1;
    private String passwordd;
    Button Verify, verify, Delete_1;
    private static final String TAG = "sharing_privacy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_privacy);
        //maam anong account po sa firebase pwede kong idelete?

        processing = findViewById(R.id.progressbar);
        pass = findViewById(R.id.Pass);
        password = findViewById(R.id.Password);
        Verify = findViewById(R.id.Verify);
        verify = findViewById(R.id.verify);
        TextView verification = findViewById(R.id.txtLabel);
        verification1 = findViewById(R.id.txtLabel1);
        Delete_1 = findViewById(R.id.btnDel);

        //Disable Delete User Button
        Delete_1.setEnabled(false);
        Verify.setEnabled(false);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
//
//        if (TextUtils.isEmpty(passwordd)){
//            Toast.makeText(sharing_privacy.this, "Please enter your password", Toast.LENGTH_LONG).show();
//            password.setError("password is required");
//
//        }
//        else{
//            reAuthenticateUser(firebaseUser);
//            showAlertDialog();
//            deleteUser(firebaseUser);
//        }


       if (firebaseUser != null && firebaseUser.equals("")) {
            Toast.makeText(sharing_privacy.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(sharing_privacy.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            reAuthenticateUser(firebaseUser);
        }
    }
    @SuppressLint("SetTextI18n")
    private void reAuthenticateUser(FirebaseUser firebaseUser) {
        verify.setOnClickListener(v -> {
            passwordd = password.getText().toString();


            //Setting password

            AuthCredential Credential = EmailAuthProvider.getCredential(Objects.requireNonNull(firebaseUser.getEmail()), passwordd);

            firebaseUser.reauthenticate(Credential).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {


                    //Disable edit text for password
                    pass.setEnabled(false);

                    //Enable delete button
                    Delete_1.setEnabled(true);
                    verify.setEnabled(false);

                    //verification for deleting
                    verification1.setText("You can now delete your account.");
                    Toast.makeText(sharing_privacy.this, "You can now delete your account.", Toast.LENGTH_SHORT).show();

                    Delete_1.setBackgroundTintList(ContextCompat.getColorStateList(sharing_privacy.this, R.color.colorRed));
                    verify.setBackgroundTintList(ContextCompat.getColorStateList(sharing_privacy.this, R.color.Light_grey));

                    Delete_1.setOnClickListener(v1 -> showAlertDialog());
                } else {
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (Exception e) {
                        Toast.makeText(sharing_privacy.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                processing.setVisibility(View.GONE);
            });
        });
    }

    private void showAlertDialog() {
        //Alert Dialog
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(sharing_privacy.this);
        builder.setTitle("Do you really want to delete the activities?");
        builder.setMessage("Deleting all activities, wasn't irreversible.");

        //going back to the main view apps
        builder.setPositiveButton("Continue", (dialog, which) -> deleteUser(firebaseUser));

        //cancel button
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            Intent intent = new Intent(sharing_privacy.this, sharing_privacy.class);
            startActivity(intent);
            finish();
        });

        //Alert dialog box
        androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        //change button color
        alertDialog.setOnShowListener(dialog -> alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorRed)));

        //show alert dialog
        alertDialog.show();
    }

    private void deleteUser(FirebaseUser firebaseUser) {
        firebaseUser.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                deleteUserData();
                authProfile.signOut();
                Toast.makeText(sharing_privacy.this, "This account has been cleared", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sharing_privacy.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (Exception e) {
                    Toast.makeText(sharing_privacy.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            processing.setVisibility(View.GONE);
        });
    }

    //Delete account in realtime database
    private void deleteUserData() {
        DatabaseReference DbReference = FirebaseDatabase.getInstance().getReference("User_account");
        DbReference.child(firebaseUser.getUid()).removeValue().addOnSuccessListener(unused -> Log.d(TAG,"OnSuccess: User Data Deleted")).addOnFailureListener(e -> {
            Log.d(TAG, e.getMessage());
            Toast.makeText(sharing_privacy.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}