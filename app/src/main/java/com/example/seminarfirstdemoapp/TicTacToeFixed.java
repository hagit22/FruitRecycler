package com.example.seminarfirstdemoapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TicTacToeFixed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tic_tac_toe_fixed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initUI();
    }

    private void initUI() {
        ImageView[] imageViews = {findViewById(R.id.imageView00),
                findViewById(R.id.imageView01),
                findViewById(R.id.imageView02),
                findViewById(R.id.imageView10),
                findViewById(R.id.imageView11),
                findViewById(R.id.imageView12),
                findViewById(R.id.imageView20),
                findViewById(R.id.imageView21),
                findViewById(R.id.imageView22)};

        for(ImageView imageView : imageViews) {
            imageView.setOnClickListener(view -> {
                // Handle the click event for each ImageView
                // For example, you can change the image or perform some action
                imageView.setImageResource(R.drawable.xmage); // Example: Set an 'X' icon
            });
        }
    }
}