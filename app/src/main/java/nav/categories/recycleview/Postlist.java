package nav.categories.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.letngo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import nav.categories.FragmentCategories;

public class Postlist extends AppCompatActivity {

    RecyclerView recyclerView;

    // Variables
    private ArrayList<Post> list;
    //private String currentLocation;
    private boolean beach;
    private boolean treehouse;
    private boolean camping;
    private boolean caves;
    private boolean countryside;
    private boolean cabin;
    private boolean island;
    String location;


    // Firebase
    private DatabaseReference databaseReference;
    String userUid;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    {
        assert currentUser != null;
        userUid = currentUser.getUid();
    }

    MyAdapter adapter;
    ImageButton back;


    public void postlist(Location location) {
        beach = location.isPost_beach();
        treehouse = location.isPost_treehouse();
        camping = location.isPost_camping();
        caves = location.isPost_caves();
        countryside = location.isPost_countryside();
        cabin = location.isPost_cabin();
        island = location.isPost_island();

        // checks the location

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_sub_beach);
        recyclerView = findViewById(R.id.recycleview_info);


        back = findViewById(R.id.beach_back);
        back.setOnClickListener(v -> onBackPressed());

        // Array list
        list = new ArrayList<>();


        // Clear Array list
        clearAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        locationChecker(beach, treehouse, camping, caves, countryside, cabin, island);
        getDataFromFirebase("beach");

    }

    private void getDataFromFirebase(String currentLocation) {
        Query query = databaseReference.child("post").child(currentLocation);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                for (DataSnapshot dataSnapShot : snapshot.getChildren()) {
                    Post posts = new Post();

                    posts.setBathrooms(dataSnapShot.child("info").child("bathrooms").getValue().toString());
                    posts.setBeds(dataSnapShot.child("info").child("beds").getValue().toString());
                    posts.setCountry(dataSnapShot.child("info").child("country").getValue().toString());
                    posts.setImage(dataSnapShot.child("info").child("image").getValue().toString());
                    posts.setPrice(dataSnapShot.child("info").child("price").getValue().toString());
                    posts.setRooms(dataSnapShot.child("info").child("rooms").getValue().toString());
                    posts.setRooms(dataSnapShot.child("user").getValue().toString());
                    list.add(posts);
                }
                adapter = new MyAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearAll() {
        if (list != null) {
            list.clear();

            if (adapter != null) {

            }
        }
        list = new ArrayList<>();
    }

    public void locationChecker(boolean locbeach,
                                boolean loctreehouse,
                                boolean loccamping,
                                boolean loccaves,
                                boolean loccountryside,
                                boolean loccabin,
                                boolean locisland) {

        if (locbeach) {
            location = "beach";

        } else if (loctreehouse) {
            location = "treehouse";

        } else if (loccamping) {
            location = "camping";

        } else if (loccaves) {
            location = "caves";

        } else if (loccountryside) {
            location = "countryside";

        } else if (loccabin) {
            location = "cabin";

        } else if (locisland) {
            location = "island";
        }
    }
}
