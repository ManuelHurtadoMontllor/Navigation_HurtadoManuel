package com.example.navegacionpantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByTitleActivity extends AppCompatActivity {

    private List<String> allMealNames;
    private List<String> allMealCategories;
    private List<String> displayedMealNames;
    private List<String> displayedMealCategories;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_title);

        ImageView btnBack = findViewById(R.id.btnBackSearch);
        btnBack.setOnClickListener(v -> finish());

        EditText etSearchTitle = findViewById(R.id.etSearchTitle);
        Button btnDoSearch = findViewById(R.id.btnDoSearch);
        ListView lvSearchResults = findViewById(R.id.lvSearchResults);
        Button btnViewDetails = findViewById(R.id.btnViewDetails);

        allMealNames = new ArrayList<>(Arrays.asList("Paella Valenciana", "Tortilla de Patatas", "Macarrones Boloñesa", "Ensalada César"));
        allMealCategories = new ArrayList<>(Arrays.asList("Principal", "Tapa", "Pasta", "Ensalada"));

        displayedMealNames = new ArrayList<>(allMealNames);
        displayedMealCategories = new ArrayList<>(allMealCategories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_meal, R.id.tvItemName, displayedMealNames) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tvName = view.findViewById(R.id.tvItemName);
                TextView tvCategory = view.findViewById(R.id.tvItemCategory);
                RadioButton rbItem = view.findViewById(R.id.rbItem);

                tvName.setText(displayedMealNames.get(position));
                tvCategory.setText(displayedMealCategories.get(position));
                rbItem.setChecked(position == selectedPosition);

                return view;
            }
        };

        lvSearchResults.setAdapter(adapter);

        btnDoSearch.setOnClickListener(v -> {
            String query = etSearchTitle.getText().toString().toLowerCase().trim();
            displayedMealNames.clear();
            displayedMealCategories.clear();
            selectedPosition = -1;

            for (int i = 0; i < allMealNames.size(); i++) {
                if (allMealNames.get(i).toLowerCase().contains(query)) {
                    displayedMealNames.add(allMealNames.get(i));
                    displayedMealCategories.add(allMealCategories.get(i));
                }
            }
            adapter.notifyDataSetChanged();
        });

        lvSearchResults.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            adapter.notifyDataSetChanged();
        });

        btnViewDetails.setOnClickListener(v -> {
            if (selectedPosition != -1 && selectedPosition < displayedMealNames.size()) {
                Intent intent = new Intent(SearchByTitleActivity.this, DetailsActivity.class);
                intent.putExtra("meal_name", displayedMealNames.get(selectedPosition));
                intent.putExtra("meal_category", displayedMealCategories.get(selectedPosition));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Por favor, seleccione un plato para ver sus detalles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
