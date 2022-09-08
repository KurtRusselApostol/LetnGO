package nav.account.hosting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.letngo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Hostinglist extends AppCompatActivity {
    RecyclerView recyclerview;
    ArrayList<Hosting> list;
    DatabaseReference databaseReference;
    hostingListAdapter adapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Hostinglist.this, Manage_Hosting.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostinglist);

        recyclerview=findViewById(R.id.recycleview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Host_Description");
        list = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new hostingListAdapter(this,list);
        recyclerview.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
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


    }
}