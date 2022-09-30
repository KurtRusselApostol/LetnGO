package nav.categories.recycleview;
//Adapter for CatLabel.java

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letngo.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Locale;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{

    Context context;
    ArrayList<CatLabel> catLabelArrayList;

    public MyAdapter2(Context context, ArrayList<CatLabel> catLabelArrayList){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CatLabel catLabel = catLabelArrayList.get(position);
        holder.tvHeading.setText(catLabel.heading);
        holder.titleImage.setImageResource(catLabel.titleImage);

    }

    @Override
    public int getItemCount() {
        return catLabelArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView titleImage;
        TextView tvHeading;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleImage = itemView.findViewById(R.id.title_image);
            tvHeading = itemView.findViewById(R.id.tvHeading);
        }
    }


}
