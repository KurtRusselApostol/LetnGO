package nav.categories.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letngo.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import nav.categories.CatLabel;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    Context context;
    ArrayList<CatLabel> catLabelArrayList;

    public MyAdapter2(Context context, ArrayList<CatLabel> catLabelArrayList){
        this.context = context;
        this.catLabelArrayList = catLabelArrayList;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.categories_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CatLabel catLabel= catLabelArrayList.get(position);
        holder.tvHeading.setText(catLabel.heading);
        holder.titleImage.setImageResource(catLabel.titleImage);

    }

    @Override
    public int getItemCount() {

        return catLabelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvHeading;
        ShapeableImageView titleImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            titleImage = itemView.findViewById(R.id.title_image);
        }
    }
}

