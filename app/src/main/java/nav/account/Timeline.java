package nav.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Timeline extends AppCompatActivity {
    TextView fullNameTextView, descriptionTextView, emailTextView, contactNumTextView;

    FirebaseAuth auth;
    FirebaseUser user;
    ImageButton backButton;
    Button EditButton;

    DatabaseReference reference;


    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.account_timeline);

                auth = FirebaseAuth.getInstance();
                user = auth.getCurrentUser();
                fullNameTextView = findViewById(R.id.full_name);
                emailTextView = findViewById(R.id.emailAdd);
                contactNumTextView = findViewById(R.id.contactNum);
                descriptionTextView = findViewById(R.id.textDescription);

                reference = FirebaseDatabase.getInstance().getReference("User_account");

                //Google user description
               emailTextView.setText(user.getEmail());
               fullNameTextView.setText(user.getDisplayName());
               contactNumTextView.setText(user.getPhoneNumber());

        backButton = findViewById(R.id.back);

        backButton.setOnClickListener(v -> onBackPressed());

        EditButton = findViewById(R.id.EditButton);
        EditButton.setOnClickListener(v -> openActivity());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot ds : snapshot.getChildren()){
                      if(Objects.equals(ds.child("eemail").getValue(), user.getEmail())){

                          contactNumTextView.setText(ds.child("emobile").getValue(String.class));
                       }
                    }
                  }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

          }
        });
}
    public void openActivity(){
        Intent intent = new Intent(this, Edit_Profile.class);
        startActivity(intent);
    }
}



