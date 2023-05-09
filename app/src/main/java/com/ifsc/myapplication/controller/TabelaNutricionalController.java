package com.ifsc.myapplication.controller;

import android.content.Context;

import com.ifsc.myapplication.Models.Alimento;
import com.ifsc.myapplication.DAO.NutriTableDAO;

import java.util.ArrayList;

public class TabelaNutricionalController {
    NutriTableDAO nutriTableDAO;
    Context mContext;

    public TabelaNutricionalController(Context context) {
        this.mContext = context;
        this.nutriTableDAO = new NutriTableDAO(mContext);
    }

    public ArrayList<String> getNomeAlimentos() {
        ArrayList<String> nomesAlimentos = new ArrayList<String>();
        for (Alimento alimento : this.nutriTableDAO.getAlimentos()) {
            nomesAlimentos.add(alimento.getNome());
        }
        return nomesAlimentos;
    }

    public ArrayList<Alimento> getAlimentos() {
        return this.nutriTableDAO.getAlimentos();
    }
}
