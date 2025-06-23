package com.example.seminarfirstdemoapp;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FruitSeminarAdapter extends RecyclerView.Adapter<FruitSeminarAdapter.FruitSeminarViewHolder> {


    private ArrayList<FruitSeminarItem> dataList;
    public FruitSeminarAdapter(ArrayList<FruitSeminarItem> dataList) {
        this.dataList = dataList;
    }

    public class FruitSeminarViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvDescription;
        public final TextView tvName;
        public final ImageView ivFruitImage;
        // Define your view holder here, e.g., ImageView, TextView
        public FruitSeminarViewHolder(View view) {
            super(view);
            // Initialize your views here
            tvDescription = view.findViewById(R.id.textViewFruitDescription);
            tvName = view.findViewById(R.id.textViewFruitName);
            ivFruitImage = view.findViewById(R.id.imageViewFruit);

        }
    }
    @NonNull
    @Override
    public FruitSeminarAdapter.FruitSeminarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.fruit_seminar_row,null);
        return new FruitSeminarViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FruitSeminarAdapter.FruitSeminarViewHolder holder, int position) {

        FruitSeminarItem currentItem = dataList.get(position);
        holder.tvDescription.setText(currentItem.getDescription());
        holder.tvName.setText(currentItem.getName());
        holder.ivFruitImage.setImageResource(currentItem.getImageResource());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click here, e.g., show a Toast or start a new activity
                // Toast.makeText(v.getContext(), "Clicked: " + currentItem.getName(), Toast.LENGTH_SHORT).show();
                Log.d("FRUIT EXAMPLE", "onClick: " + currentItem.getName());
            }

        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
