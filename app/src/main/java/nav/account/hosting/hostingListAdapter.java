package nav.account.hosting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letngo.R;

import java.util.ArrayList;

import nav.categories.recycleview.MyAdapter;

public class hostingListAdapter extends RecyclerView.Adapter<hostingListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Hosting> list;

    public hostingListAdapter(Context context, ArrayList<Hosting> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.hostingentry,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hosting hosting = list.get(position);
        holder.title.setText(hosting.getPlaceTitle());
        holder.description.setText(hosting.getDescription());
        holder.highlights.setText(hosting.getHighlights());
        holder.offer_description.setText(hosting.getOffer_Description());
        holder.others.setText(hosting.getOthers());
        holder.price.setText(hosting.getPrice());
        holder.price_discount.setText(hosting.getPriceDiscount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,highlights,offer_description,others,price,price_discount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            highlights = itemView.findViewById(R.id.textHighlights);
            offer_description = itemView.findViewById(R.id.textOffers);
            others = itemView.findViewById(R.id.textOther);
            price = itemView.findViewById(R.id.textPrice);
            price_discount = itemView.findViewById(R.id.textDiscount);
        }
    }
}
