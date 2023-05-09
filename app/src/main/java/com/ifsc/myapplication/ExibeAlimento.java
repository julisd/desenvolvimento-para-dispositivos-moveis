package com.ifsc.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ifsc.myapplication.Models.Alimento;
import com.ifsc.myapplication.controller.TabelaNutricionalController;

public class ExibeAlimento extends AppCompatActivity {

    TabelaNutricionalController tabelaNutricionalController;
    Alimento alimento;

    TextView textViewCategoria;
    TextView textViewAlimento;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_alimento);

        int idAlimento = getIntent().getExtras().getInt("id");

        this.tabelaNutricionalController = new TabelaNutricionalController(getBaseContext());
        this.alimento = tabelaNutricionalController.getAlimentos().get(idAlimento);

        this.textViewCategoria = findViewById(R.id.textViewCategoria);
        this.textViewAlimento = findViewById(R.id.textViewAlimento);

        this.textViewCategoria.setText(this.alimento.getCategoria());
        this.textViewAlimento.setText(this.alimento.getNome());
    }
}
