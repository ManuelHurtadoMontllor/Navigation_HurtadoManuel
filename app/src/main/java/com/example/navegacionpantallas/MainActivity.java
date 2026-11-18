package com.example.navegacionpantallas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAddMeal = findViewById(R.id.btnAddMeal);
        Button btnDeleteMeal = findViewById(R.id.btnDeleteMeal);
        Button btnSearchMeal = findViewById(R.id.btnSearchMeal);
        Button btnListMeals = findViewById(R.id.btnListMeals);

        btnAddMeal.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddMealActivity.class);
            startActivity(intent);
        });

        btnDeleteMeal.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeleteMealActivity.class);
            startActivity(intent);
        });

        btnSearchMeal.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchByTitleActivity.class);
            startActivity(intent);
        });

        btnListMeals.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListMealsActivity.class);
            startActivity(intent);
        });
    }
}
