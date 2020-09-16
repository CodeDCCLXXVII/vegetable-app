package com.example.vegprice.DataService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vegprice.R;
import com.example.vegprice.pojo.Vegetable;
import com.example.vegprice.pojo.VegetableTrans;
import com.example.vegprice.ui.home.HomeFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {
    private List<VegetableTrans> vegetableTransList;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView vegName, vegPrice, vegQty, vegCost;

        public ViewHolder(View itemView) {
            super(itemView);

            vegName = itemView.findViewById(R.id.vegCostName);
            vegPrice = itemView.findViewById(R.id.vegCostPrice);
            vegQty = itemView.findViewById(R.id.vegCostQty);
            vegCost = itemView.findViewById(R.id.vegCost);
        }
    }

    public TransactionsAdapter(List<VegetableTrans> vegetableTransList, Context context) {
        this.vegetableTransList = vegetableTransList;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_veg_cost, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TransactionsAdapter.ViewHolder holder, final int position) {

        final VegetableTrans vegetableTrans = vegetableTransList.get(position);

        TextView vegName = holder.vegName;
        vegName.setText(vegetableTrans.getVegName());
        TextView vegPrice = holder.vegPrice;
        vegPrice.setText("Price: "+vegetableTrans.getPrice());
        TextView vegQty = holder.vegQty;
        vegQty.setText("Qty: "+vegetableTrans.getQuantity());
        TextView vegCost = holder.vegCost;
        vegCost.setText("Cost: "+vegetableTrans.getSubTotal());
    }

    @Override
    public int getItemCount() {
        return vegetableTransList.size();
    }
}
