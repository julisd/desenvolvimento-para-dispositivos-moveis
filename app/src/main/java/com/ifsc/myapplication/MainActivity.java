package com.ifsc.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FrutaController frutaController;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvNome = view.findViewById(R.id.tvNome);
                String nome = tvNome.getText().toString();

                Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_SHORT).show();
            }
        });

        frutaController = new FrutaController();
        atualizaLista();
    }

    public void atualizaLista(){
        listView.setAdapter(
                new ArrayFrutaAdapter(
                        getApplicationContext(),
                        R.layout.item_lista,
                        frutaController.FRUTAS
                )
        );
    }
}