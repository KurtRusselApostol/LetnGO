package nav.account.hosting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letngo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class hosting_checking_description extends AppCompatActivity implements ValueEventListener{

    TextView title, hName, guest, bed, bedroom, bathroom, description, location,
            back, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_checking_description);

        //INITIALIZATIONS
        title = findViewById(R.id.tv_host_title1);
        hName = findViewById(R.id.tv_host_name);
        guest = findViewById(R.id.tv_guest_check);
        bed = findViewById(R.id.tv_bed_check);
        bedroom = findViewById(R.id.tv_bedroom_check);
        bathroom = findViewById(R.id.tv_bathroom_check);
        description = findViewById(R.id.tv_check_description);
        location = findViewById(R.id.tv_check_location);

        back = findViewById(R.id.tv_back);
        next = findViewById(R.id.tv_next);

        retrieveName();
//        retrievePlaceDetails();
//        retrievePlaceDescription();
//        TRIAL();

        next.setOnClickListener(v -> {
            Intent intent = new Intent(hosting_checking_description.this, hosting_success.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> onBackPressed());
    }

    //------------------------------ALL METHODS STARTS HERE----------------------------------------//

    public void TRIAL()
    {
        DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("Host_Account").child("Host_Profile");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String  h_name =snapshot.child("Full_Name").getValue(String.class);

                    hName.setText(h_name);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //CALLING FOR DETAILS FROM START_HOSTING MODULE
    public void retrieveName()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Host_Account").child("Host_Profile");

        reference.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                String h_name = dataSnapshot.child("Full_Name").getValue(String.class);

                hName.setText(h_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
                Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //CALLING FOR DETAILS FROM HOSTING_NEXT MODULE
    public void retrievePlaceDetails()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference1 = database.getReference().child("Host_Account");

        reference1.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                String h_guest = dataSnapshot.child("Guest").getValue(String.class);
                String h_bed = dataSnapshot.child("Bed").getValue(String.class);
                String h_bedroom = dataSnapshot.child("Room").getValue(String.class);
                String h_bathroom = dataSnapshot.child("Bathroom").getValue(String.class);
                String h_location = dataSnapshot.child("Street").getValue(String.class);
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    if (snapshot.child("Guest").getValue().equals(h_guest)) {
//                        guest.setText(snapshot.child("Guest").getValue(String.class));
//                        bed.setText(snapshot.child("Bed").getValue(String.class));
//                        bedroom.setText(snapshot.child("Room").getValue(String.class));
//                        bathroom.setText(snapshot.child("Bathroom").getValue(String.class));
//                        location.setText(snapshot.child("Street").getValue(String.class));
//                    }
//                }

                guest.setText(h_guest);
                bed.setText(h_bed);
                bedroom.setText(h_bedroom);
                bathroom.setText(h_bathroom);
                location.setText(h_location);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });
    }

    //CALLING FOR DETAILS FROM HOSTING_CHECKING DESCRIPTION MODULE
    public void retrievePlaceDescription()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference2 = database.getReference().child("Host_Account");

        reference2.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                String h_title = dataSnapshot.child("Title").getValue(String.class);
                String h_description = dataSnapshot.child("Description").getValue(String.class);

                title.setText(h_title);
                description.setText(h_description);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}