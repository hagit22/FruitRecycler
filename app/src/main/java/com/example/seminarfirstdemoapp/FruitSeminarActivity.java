package com.example.seminarfirstdemoapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FruitSeminarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fruit_seminar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initUI();

        setupRecyclerView();



    }

    private void setupRecyclerView() {
        // set the data!
        // consider the data is received from Database
        // 1 Create ArrayList Items
        ArrayList<FruitSeminarItem> fruitList = new ArrayList<>();
        fruitList.add(new FruitSeminarItem(R.drawable.apple, "Apple", "Rich in fiber and vitamin C."));
        fruitList.add(new FruitSeminarItem(R.drawable.banana, "Banana", "Great source of potassium."));
        fruitList.add(new FruitSeminarItem(R.drawable.orange, "Orange", "Loaded with vitamin C."));
        fruitList.add(new FruitSeminarItem(R.drawable.strawberry, "Strawberry", "Full of antioxidants."));
        fruitList.add(new FruitSeminarItem(R.drawable.watermelon, "Watermelon", "Very refreshing and hydrating."));

        // 2 create adapter
        FruitSeminarAdapter adapter = new FruitSeminarAdapter(fruitList);
        // 3 set the adpater in the recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initUI() {
        recyclerView = findViewById(R.id.fruitRecyclerView);
    }
}