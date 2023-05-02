package com.example.testandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    ArrayList<DonutItems> item;

    public MyAdapter(Context context, ArrayList<DonutItems> item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.donut_item_view ,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.donutView.setText(item.get(position).getDonutName());
//        Spinner spinner = findViewById(R.layout.quantityDonut);
//        holder.quantityDonut.setAdapter(item.get(position).getQuantity());

        holder.imageView.setImageResource(item.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
