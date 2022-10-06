package login.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class forgot_password extends AppCompatActivity {

    TextView forEmail;
    String email;
    Button forSend;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_system_forgot_password);

        forEmail = findViewById(R.id.email);
        forSend = findViewById(R.id.btn_send);
        back = findViewById(R.id.img_exit);

        back.setOnClickListener(v -> onBackPressed());

        forSend.setOnClickListener(v -> {
             email = forEmail.getText().toString();

            if (email.isEmpty()){
                Toast.makeText(forgot_password.this, "Please provide your email", Toast.LENGTH_LONG).show();
                forEmail.setError("Email is required");
                forEmail.requestFocus();
            }
            else{
                forgotPassword();
                showResetPasswordAlert();
            }
        });
    }

    private void forgotPassword() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {

            if (task.isSuccessful()){
                Toast.makeText(forgot_password.this, "Check your Email", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(forgot_password.this, Change_password.class));

            }
            else {
                Toast.makeText(forgot_password.this, "Error: "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    private void showResetPasswordAlert() {
        //Setup the Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(forgot_password.this);
        builder.setTitle("Reset Your Password");
        builder.setMessage("You may now set a new password. Kindly check your email.");

        //open email app when "continue" button is clicked
        builder.setPositiveButton("Open Gmail", (dialog, which) -> {
            startActivity(new Intent(forgot_password.this, new_login.class));
            finish();
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
}