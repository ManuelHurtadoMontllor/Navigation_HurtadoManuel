package com.example.navegacionpantallas;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meals);

        ImageView btnBack = findViewById(R.id.btnBackAdd);
        btnBack.setOnClickListener(v -> finish());
    }
}
