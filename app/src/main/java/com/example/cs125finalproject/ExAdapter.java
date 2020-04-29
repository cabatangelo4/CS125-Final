package com.example.cs125finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ExViewHolder> {
    private ArrayList<CustomCard> xCards;

    public static class ExViewHolder extends RecyclerView.ViewHolder {
        public TextView mcontent;
        public ExViewHolder(View itemView) {
            super(itemView);
            mcontent = itemView.findViewById(R.id.content);
        }
    }

    public ExAdapter(ArrayList<CustomCard> cards) {
        xCards = cards;
    }

    @NonNull
    @Override
    public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_entry, parent, false);
        ExViewHolder ex = new ExViewHolder(v);
        return ex;
    }

    @Override
    public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {
        CustomCard currentItem = xCards.get(position);

        holder.mcontent.setText(currentItem.getContent());
    }

    @Override
    public int getItemCount() {
        return xCards.size();
    }
}
