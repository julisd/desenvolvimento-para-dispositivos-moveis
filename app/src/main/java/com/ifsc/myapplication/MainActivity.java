package com.ifsc.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ifsc.myapplication.controller.TabelaNutricionalController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabelaNutricionalController controller;

    private SearchView searchView;
    private ListView foodListView;
    private ArrayAdapter<String> adapter;
    private List<String> foodList;
    private List<String> originalFoodList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new TabelaNutricionalController(getApplicationContext());

        searchView = findViewById(R.id.searchView);
        foodListView = findViewById(R.id.foodListView);

        originalFoodList = controller.getAllFood();
        foodList = new ArrayList<>(originalFoodList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodList);
        foodListView.setAdapter(adapter);
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String food = adapter.getItem(position);
                if (food != null) {
                    String attributes = controller.getFoodAttributes(food).toString();
                    if (!TextUtils.isEmpty(attributes)) {
                        Toast.makeText(MainActivity.this, attributes, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
        foodList.clear();
        if (TextUtils.isEmpty(query)) {
            foodList.addAll(originalFoodList);
        } else {
            for (String food : originalFoodList) {
                if (food.toLowerCase().contains(query.toLowerCase())) {
                    foodList.add(food);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}