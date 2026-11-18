package com.example.navegacionpantallas;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class DeleteMealActivity extends AppCompatActivity {

    private ArrayList<String> mealNames;
    private ArrayList<String> mealCategories;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_meal);

        ImageView btnBack = findViewById(R.id.btnBackDelete);
        btnBack.setOnClickListener(v -> finish());

        ListView lvDeleteMeals = findViewById(R.id.lvDeleteMeals);
        Button btnConfirmDelete = findViewById(R.id.btnConfirmDelete);

        // Initial sample data
        mealNames = new ArrayList<>(Arrays.asList("Paella Valenciana", "Tortilla de Patatas", "Macarrones Boloñesa", "Ensalada César"));
        mealCategories = new ArrayList<>(Arrays.asList("Principal", "Tapa", "Pasta", "Ensalada"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_meal, R.id.tvItemName, mealNames) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tvName = view.findViewById(R.id.tvItemName);
                TextView tvCategory = view.findViewById(R.id.tvItemCategory);
                RadioButton rbItem = view.findViewById(R.id.rbItem);

                tvName.setText(mealNames.get(position));
                tvCategory.setText(mealCategories.get(position));
                rbItem.setChecked(position == selectedPosition);

                return view;
            }
        };

        lvDeleteMeals.setAdapter(adapter);

        lvDeleteMeals.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            adapter.notifyDataSetChanged();
        });

        btnConfirmDelete.setOnClickListener(v -> {
            if (selectedPosition != -1 && selectedPosition < mealNames.size()) {
                String deletedMeal = mealNames.get(selectedPosition);
                mealNames.remove(selectedPosition);
                mealCategories.remove(selectedPosition);
                selectedPosition = -1;
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Plato eliminado: " + deletedMeal, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Por favor, seleccione un plato para eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
