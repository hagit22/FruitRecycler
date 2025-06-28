package com.example.fruitrecycler;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FruitActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FruitAdapter fruitAdapter;
    private List<FruitItem> fruitList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fruit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpFruitActivity();   // Alon called it setUpRecyclerView();
    }

    private void setUpFruitActivity() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // We use a linear layout manager for the recycler view

        // Sample fruit data
        fruitList = new ArrayList<>();
        fruitList.add(new FruitItem(R.drawable.apple, "Apple", "Rich in fiber and vitamin C.", false));
        fruitList.add(new FruitItem(R.drawable.banana, "Banana", "Great source of potassium.", false));
        fruitList.add(new FruitItem(R.drawable.orange, "Orange", "Loaded with vitamin C.", false));
        fruitList.add(new FruitItem(R.drawable.strawberry, "Strawberry", "Full of antioxidants.", false));
        fruitList.add(new FruitItem(R.drawable.watermelon, "Watermelon", "Very refreshing and hydrating.", false));
        // Add more fruits as desired

        // Here is where the magic happens, and we click everything together!!
        fruitAdapter = new FruitAdapter(fruitList); // We create an adapter with the list of fruits
        recyclerView.setAdapter(fruitAdapter); // We connect the adapter to the recycler view
        // Optional: Set item click listener if needed


        // use ItemTouchHelper for drag and drop or swipe actions if needed
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN, // Enable drag directions
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) // Enable swipe left and right
            {
                @Override
                public boolean onMove(RecyclerView recyclerView,
                                      RecyclerView.ViewHolder originViewHolder,
                                      RecyclerView.ViewHolder targetViewHolder) {
                    int fromPos = originViewHolder.getAdapterPosition();
                    int toPos = targetViewHolder.getAdapterPosition();
                    // Swap items and notify adapter
                    Collections.swap(fruitList, fromPos, toPos);
                    fruitAdapter.notifyItemMoved(fromPos, toPos);
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    fruitList.remove(position);
                    fruitAdapter.notifyItemRemoved(position);
                }
            }
        ; // End of anonymous inner class
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
