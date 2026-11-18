package com.example.navegacionpantallas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView btnBack = findViewById(R.id.btnBackDetails);
        btnBack.setOnClickListener(v -> finish());

        TextView tvDetailName = findViewById(R.id.tvDetailName);
        TextView tvDetailCategory = findViewById(R.id.tvDetailCategory);
        TextView tvDetailDesc = findViewById(R.id.tvDetailDesc);

        String mealName = getIntent().getStringExtra("meal_name");
        String mealCategory = getIntent().getStringExtra("meal_category");
        String mealDesc = getIntent().getStringExtra("meal_desc");

        if (mealName != null) {
            tvDetailName.setText(mealName);
        }
        if (mealCategory != null) {
            tvDetailCategory.setText("Categoría: " + mealCategory);
        }

        if (mealDesc != null) {
            tvDetailDesc.setText(mealDesc);
        }
    }
}
