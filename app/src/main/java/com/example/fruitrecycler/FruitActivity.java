package com.example.fruitrecycler;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitrecycler.data.FruitItem;
import com.example.fruitrecycler.data.FruitRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FruitActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FruitAdapter fruitAdapter;
    private List<FruitItem> fruitList;
    FruitRepository fruitRepo;

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
        setUpRecyclerView();
        setUpFloatingActionButton();

        fruitRepo = new FruitRepository(this);
        fruitRepo.getAll(list -> updateFruitList(list));
    }

    private void setUpRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // We use a linear layout manager for the recycler view

        // Here is where the magic happens, and we click everything together!!
        fruitList = new ArrayList<>(); // Initialize the list of fruits
        fruitAdapter = new FruitAdapter(fruitList); // We create an adapter with the list of fruits
        recyclerView.setAdapter(fruitAdapter); // We connect the adapter to the recycler view
        // Optional: Set item click listener if needed


        // use ItemTouchHelper for drag and drop or swipe actions if needed
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
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
                FruitItem fruitItem = fruitList.get(position);
                if (direction == ItemTouchHelper.RIGHT) {   // Swipe right
                    shareFruitItem(fruitItem);
                    fruitAdapter.notifyItemChanged(position);
                }
                else if (direction == ItemTouchHelper.LEFT) {  // Swipe left
                    removeFruit(position);
                    undoFruitRemoval(fruitItem, position);
                }
            }

            @Override
            public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                View itemView = viewHolder.itemView;
                Paint paint = new Paint();

                if (dX < 0) { // Swiping left
                    paint.setColor(Color.RED);
                    canvas.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                            (float) itemView.getRight(), (float) itemView.getBottom(), paint);
                }
                else if (dX > 0) { // Swiping right
                    paint.setColor(Color.GREEN);
                    canvas.drawRect((float) itemView.getLeft(), (float) itemView.getTop(),
                            (float) itemView.getLeft() + dX, (float) itemView.getBottom(), paint);
                }
            }

            private void shareFruitItem(FruitItem fruitItem) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this fruit: " + fruitItem.getName());
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }

            private void removeFruit(int position) {
                fruitList.remove(position);
                fruitAdapter.notifyItemRemoved(position);
            }

            private void undoFruitRemoval(FruitItem fruitItem, int position) {
                Snackbar snackbar = Snackbar.make(recyclerView, "Item removed", Snackbar.LENGTH_SHORT);
                snackbar.setAction("UNDO remove " + fruitItem.getName().toUpperCase(),
                        view -> addBackToFruitList(fruitItem, position));
                snackbar.addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                            // Delete permanently if not undone
                            fruitRepo.delete(fruitItem, list -> updateFruitList(list));
                        }
                    }
                });
                snackbar.show();
            }

            private void addBackToFruitList(FruitItem fruitItem, int position) {
                fruitList.add(position, fruitItem);
                fruitAdapter.notifyItemInserted(position);
            }

        }; // End of anonymous inner class

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setUpFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fabAddFruit);

        fab.setOnClickListener( view -> {
                // inflate the custom dialog layout
                View dialogView = LayoutInflater.from(FruitActivity.this).inflate(R.layout.dialog_add_fruit, null);

                EditText nameInput = dialogView.findViewById(R.id.edit_fruit_name);
                EditText descInput = dialogView.findViewById(R.id.edit_fruit_desc);
                ImageView fruitImg = dialogView.findViewById(R.id.img_fruit);

                // set default image (later we will get from gallery/camera)
                fruitImg.setImageResource(R.drawable.mango);
                fruitImg.setTag(R.drawable.mango);

                AlertDialog.Builder builder = new AlertDialog.Builder(FruitActivity.this);
                builder.setTitle("Add New Fruit")
                        .setView(dialogView)
                        .setPositiveButton("Add", (dialog, which) -> {
                            String name = nameInput.getText().toString();
                            String description = descInput.getText().toString();
                            int imageResource = (int) fruitImg.getTag();
                            //fruitList.add(new FruitItem(imageResource, name, description));
                            //fruitAdapter.notifyItemInserted(fruitList.size() - 1);
                            FruitItem newFruitItem = new FruitItem(imageResource, name, description);
                            fruitRepo.insert(newFruitItem, list -> updateFruitList(list));

                        })
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                        .setCancelable(false)
                        .show();
                //builder.show();
        });
    }

    /*private void createInitialFruits() {
        // Sample fruit data
        addFruit("Apple", "Rich in fiber and vitamin C.", R.drawable.apple);
        addFruit("Banana", "Great source of potassium.", R.drawable.banana);
        addFruit("Orange", "Loaded with vitamin C.", R.drawable.orange);
        addFruit("Strawberry", "Full of antioxidants.", R.drawable.strawberry);
        addFruit("Watermelon", "Very refreshing and hydrating.", R.drawable.watermelon);
        // You can add more fruits as desired
    }*/

    // Create a single callback method
    private void updateFruitList(List<FruitItem> list) {
        runOnUiThread(() -> {
            fruitList.clear();
            fruitList.addAll(list);
            fruitAdapter.notifyDataSetChanged();
        });
    }


}
