package com.ifsc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


public class ActivityExibirNota extends AppCompatActivity {

    NotaController controller;
    EditText edTitulo, edTexto;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_nota);
        controller = new NotaController(getApplicationContext());

        edTitulo = findViewById(R.id.edTitulo);
        edTexto = findViewById(R.id.edTexto);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Integer actionId = bundle.getInt("notaId");
            nota = controller.recuperaNota(actionId);
        } else {
            nota = new Nota(edTitulo.getText().toString(), edTexto.getText().toString());
        }

    }

    public void salvarNota(View v) {
        Nota notaNova = new Nota(edTitulo.getText().toString(), edTexto.getText().toString());
        if (nota.getId()!=null){
            controller.atualizaNota(notaNova);
            Toast.makeText(this, "Nota atualizada com sucesso", Toast.LENGTH_SHORT).show();

        }else{
            controller.cadastrarNota(notaNova);
            Toast.makeText(this, "Nota cadastrada com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onResume() {
        super.onResume();
        edTitulo.setText(nota.getTitulo());
        edTexto.setText(nota.getTexto());
    }
}
