package com.example.fruitrecycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyViewHolder> {

    private List<FruitItem> itemList;

    public FruitAdapter(List<FruitItem> itemList) {
        this.itemList = itemList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewItem;
        TextView textViewTitle;
        TextView textViewDescription;
        ImageView imageViewLike;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageViewItem = itemView.findViewById(R.id.imageViewItem);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewLike = itemView.findViewById(R.id.imageViewLike);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fruit, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FruitItem currentItem = itemList.get(position);
        holder.imageViewItem.setImageResource(currentItem.getImageResource());
        holder.textViewTitle.setText(currentItem.getName());
        holder.textViewDescription.setText(currentItem.getDescription());
        holder.imageViewLike.setImageResource(currentItem.isLiked() ? R.drawable.like_filled : R.drawable.like_outline);


        // beginner - option
        // Set an OnClickListener on the itemView to handle clicks
        // better is use an interface for click events
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click here, you have access to currentItem or position
                Toast.makeText(v.getContext(), "Clicked: " + currentItem.getName(), Toast.LENGTH_SHORT).show();
                Log.d("Adapter", "Clicked: " + currentItem.getName());
            }
        });

        holder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click here, you have access to currentItem or position
                //holder.imageViewLike.setImageResource(currentItem.isLiked() ? R.drawable.like_filled : R.drawable.like_outline);
                Log.d("Adapter", "Toggle Like: " + currentItem.getName());
                currentItem.setLike(!currentItem.isLiked());
                if (currentItem.isLiked())
                    Toast.makeText(v.getContext(), "I LOVE: " + currentItem.getName() + "s", Toast.LENGTH_SHORT).show();
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}