package com.ifsc.myapplication.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ifsc.myapplication.DAO.NutriTableDAO;
import com.ifsc.myapplication.R;

import java.util.List;

public class TabelaNutricionalController {
    NutriTableDAO nutriTableDAO;
    private ArrayAdapter<String> adapter;

    public TabelaNutricionalController(Context context) {
        nutriTableDAO = new NutriTableDAO(context);
    }

    public ArrayAdapter<String> initializeAdapter(Context context) {
        List<String> foodList = nutriTableDAO.getAllFoodNames();
        adapter = new ArrayAdapter<>(context, R.layout.list_item_food, foodList);
        return adapter;
    }

    public List<String> getAllFood() {
        return nutriTableDAO.getAllFood();
    }

    public String getFoodAttributes(String nome){
        return nutriTableDAO.getFoodAttributes(nome);
    }

}
