package nav.account.hosting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letngo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Manage_Hosting extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerview;
    ArrayList<Hosting> list;
    DatabaseReference databaseReference;
    hostingListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_hosting_manage_hosting);

        back = findViewById(R.id.img_Cancel);
        Button button = findViewById(R.id.add_hosting);
        button.setOnClickListener(v -> {
            Intent intent=new Intent(Manage_Hosting.this,new_Hosting.class);
            startActivity(intent);
        });

        recyclerview=findViewById(R.id.recycleview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Host_Description");
        list = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new hostingListAdapter(this,list);
        recyclerview.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Hosting hosting = dataSnapshot.getValue(Hosting.class);
                    list.add(hosting);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(v -> onBackPressed());

    }


}