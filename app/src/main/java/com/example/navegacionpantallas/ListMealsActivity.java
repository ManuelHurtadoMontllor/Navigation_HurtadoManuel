package com.example.navegacionpantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

public class ListMealsActivity extends AppCompatActivity {

    private ArrayList<String> mealNames;
    private ArrayList<String> mealCategories;
    private ArrayList<String> mealDesc;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meals);

        ImageView btnBack = findViewById(R.id.btnBackList);
        btnBack.setOnClickListener(v -> finish());

        ListView lvMeals = findViewById(R.id.lvMeals);
        Button btnViewDetailsList = findViewById(R.id.btnViewDetailsList);

        mealNames = new ArrayList<>(Arrays.asList("Paella Valenciana", "Tortilla de Patatas", "Macarrones Boloñesa", "Ensalada César"));
        mealCategories = new ArrayList<>(Arrays.asList("Principal", "Tapa", "Pasta", "Ensalada"));
        mealDesc = new ArrayList<>(Arrays.asList("Pues una paella, valenciana, no se que mas quieres", "Patata, huevo... ¿De verdad no sabes que lleva una tortilla de patata?", "Pasta, salsa, queso, carne...", "Lechuga con unas cuantas cosas mas"));

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

        lvMeals.setAdapter(adapter);

        lvMeals.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            adapter.notifyDataSetChanged();
        });

        btnViewDetailsList.setOnClickListener(v -> {
            if (selectedPosition != -1 && selectedPosition < mealNames.size()) {
                Intent intent = new Intent(ListMealsActivity.this, DetailsActivity.class);
                intent.putExtra("meal_name", mealNames.get(selectedPosition));
                intent.putExtra("meal_category", mealCategories.get(selectedPosition));
                intent.putExtra("meal_desc", mealDesc.get(selectedPosition));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Por favor, seleccione un plato para ver sus detalles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
