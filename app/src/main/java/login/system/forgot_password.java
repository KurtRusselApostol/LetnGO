package login.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    TextView forEmail;
    String email;
    Button forSend;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forEmail = findViewById(R.id.email);
        forSend = findViewById(R.id.btn_send);
        back = findViewById(R.id.img_exit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        forSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = forEmail.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(forgot_password.this, "Please provide your email", Toast.LENGTH_LONG).show();
                    forEmail.setError("Email is required");
                    forEmail.requestFocus();
                }
                else{
                    forgotPassword();
                }
            }
        });
    }

    private void forgotPassword() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(forgot_password.this, "Check your Email", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(forgot_password.this, Change_password.class));
                    startActivity(new Intent(forgot_password.this, new_signup.class));
                    finish();
                }
                else {
                    Toast.makeText(forgot_password.this, "Error : "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}