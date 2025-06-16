package com.example.seminarfirstdemoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DemoActivity extends AppCompatActivity {

    private Button button;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initUI();
        model = new Model();
    }

    private void initUI() {

        button = findViewById(R.id.button00);
        /*
        button.setOnClickListener(view -> {

            int viewId = view.getId();
            String idAsString = getResources().getResourceEntryName(viewId);
            Log.d("ID AS STRING", "initUI: " +  idAsString);

            String  tag = view.getTag().toString();
             Log.d("TAG", "initUI: " + tag);


            // Handle button click
            // For example, you can show a Toast or start another activity
            // Toast.makeText(DemoActivity.this, "Button clicked!", Toast.LENGTH_SHORT).show();
        });
         */
    }

    public void onButtonClick(View view) {
        String tag = view.getTag().toString();
        int row = tag.charAt(0)-'0';
        int column = tag.charAt(1)-'0';
        Button clickedButton = (Button) view;
        clickedButton.setText("X");
        Log.d("Android Seminar", "row: " + row + "column: " + column );

    }
}