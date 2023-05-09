package com.ifsc.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private NotaController controller;
    private List<Nota> arrayNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
    }

    public void cadastrarNota(View v) {
        Intent intent = new Intent(this, ActivityExibirNota.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new NotaController(this);
        ArrayList<String> dataset = controller.listaTituloNotas();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataset);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),Long.toString(parent.getItemIdAtPosition(position) ), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), ActivityExibirNota.class);
                Nota n = controller.recuperaNota(controller.listaNotas().get(position).getId());
                i.putExtra("notaId",controller.listaNotas().get(position).getId());
                startActivity(i);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Confirmar exclusão nota");
                adb.setMessage("Excluir nota?");
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.excluirNota(controller.listaNotas().get(position));
                        onResume();
                    }
                });
                adb.show();
                return true;
            }
        });
    }
}