package nav.categories.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letngo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Post> list;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    ImageView image;



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.categories_description,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        reference = FirebaseDatabase.getInstance().getReference("User_account");
        Post post = list.get(position);
        mAuth = FirebaseAuth.getInstance();


        holder.bathrooms.setText(post.getBathrooms());
        holder.beds.setText(post.getBeds());
        holder.country.setText(post.getCountry());
        holder.price.setText(post.getPrice());
        holder.rooms.setText(post.getRooms());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bathrooms, beds, country, price, rooms;
        public ImageView image;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            bathrooms = itemView.findViewById(R.id.beds_info);
            beds = itemView.findViewById(R.id.bathrooms_info);
            country = itemView.findViewById(R.id.country_info);
            price = itemView.findViewById(R.id.price_info);
            rooms = itemView.findViewById(R.id.rooms_info);

            image = itemView.findViewById(R.id.image_info);
        }

    }

//    private void getPostPic(){
//        reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists() && snapshot.getChildrenCount() > 0){
//                    if(snapshot.hasChild("image")){
//                        String image_ = snapshot.child("image").getValue().toString();
//                        Picasso.get().load(image_).into(image);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

}
