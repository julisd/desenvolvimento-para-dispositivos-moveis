package com.ifsc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ifsc.myapplication.controller.TabelaNutricionalController;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TabelaNutricionalController tabelaNutricionalController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = findViewById(R.id.listView);
        this.tabelaNutricionalController = new TabelaNutricionalController(getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                this.tabelaNutricionalController.getNomeAlimentos());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ExibeAlimento.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}