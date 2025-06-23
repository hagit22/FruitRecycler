package com.example.seminarfirstdemoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void checkIfFirstRun() {
    }

    public void moveActivities(View view) {
        // read the data from the edit text
        // pass the data to the next activity
        EditText editText =findViewById(R.id.etNameExample);
        String name = editText.getText().toString();

        checkToSaveName(name);


        Intent intent =  new Intent(this, MainActivity.class);
        intent.putExtra(getString(R.string.user_name), name);
        intent.putExtra("number", 123);
        startActivity(intent);
        finish();
    }

    private void checkToSaveName(String name) {

        CheckBox cbSaveName = findViewById(R.id.cbSaveName);
        if (cbSaveName.isChecked()) {
            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            prefs.edit().putString("saved_name", name).apply();
        }
    }
}