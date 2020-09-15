package com.example.vegprice.DataService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vegprice.R;
import com.example.vegprice.pojo.Vegetable;
import com.example.vegprice.ui.home.HomeFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VegetablesAdapter extends RecyclerView.Adapter<VegetablesAdapter.ViewHolder> {
    private List<Vegetable> vegetables;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView vegName, vegPrice;
        public ImageButton updateBtn, delBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            vegName = itemView.findViewById(R.id.vegName);
            vegPrice = itemView.findViewById(R.id.vegPrice);
            updateBtn = itemView.findViewById(R.id.vegUpdate);
            delBtn = itemView.findViewById(R.id.vegDel);
        }
    }

    public VegetablesAdapter(List<Vegetable> vegetables, Context context) {
        this.vegetables = vegetables;
        this.context = context;
    }

    @NonNull
    @Override
    public VegetablesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_veg, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VegetablesAdapter.ViewHolder holder, final int position) {

        final Vegetable vegetable = (Vegetable) vegetables.get(position);

        TextView vegName = holder.vegName;
        vegName.setText(vegetable.getName());
        TextView vegPrice = holder.vegPrice;
        vegPrice.setText(vegetable.getPrice()+"/=");
        ImageButton updateBtn = holder.updateBtn;
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.updateVegetable(vegetable);
            }
        });
        ImageButton delBtn = holder.delBtn;
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.addVegetable();
            }
        });

    }

    @Override
    public int getItemCount() {
        return vegetables.size();
    }
}
