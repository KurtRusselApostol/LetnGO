package nav.categories.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.letngo.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static  final String Tag = "RecyclerView";
    Context context;
    ArrayList<Post> list;


    public MyAdapter(Context context, ArrayList<Post> list){
        this.context = context;
        this.list = list;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bathrooms, beds, country, price, rooms;
        ImageView image;

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


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.categories_description,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post post = list.get(position);
        
        holder.bathrooms.setText(post.getBathrooms());
        holder.beds.setText(post.getBeds());
        holder.country.setText(post.getCountry());
        holder.price.setText(post.getPrice());
        holder.rooms.setText(post.getRooms());


        //ImageView : Glide Library

        Glide.with(context)
                .load(post.getImage()) // Url of the picture
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
