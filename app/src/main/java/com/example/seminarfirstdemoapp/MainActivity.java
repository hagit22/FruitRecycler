package com.example.seminarfirstdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        String name =getIntent().getStringExtra(getString(R.string.user_name));
        int number = getIntent().getIntExtra("number", 0);

        Toast.makeText(this, "Hello, " + name + "!", Toast.LENGTH_SHORT).show();
    }

    private void initUI() {
        EditText etName = findViewById(R.id.etNameMain);

    /*    Button showButton = findViewById(R.id.btnShowMain);

            showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();

                Toast.makeText(MainActivity.this, "Hello, " + name + "!", Toast.LENGTH_SHORT).show();


            }
        });

     */
    }


    public void moveActivity(View view) {
        Intent intent = new Intent(this, ExampleActivity.class);
        startActivity(intent);
    }
}