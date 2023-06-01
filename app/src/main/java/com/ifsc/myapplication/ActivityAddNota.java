package com.ifsc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ifsc.myapplication.controller.NotaController;
import com.ifsc.myapplication.model.Nota;

public class ActivityAddNota extends AppCompatActivity {
	NotaController notaController;
	EditText etTitulo, etTexto, etId;
	TextView tvId;
	Button btSalvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibe_nota);

		notaController = new NotaController(getApplicationContext());
		
		tvId = findViewById(R.id.textViewId);
		etId = findViewById(R.id.editTextId);
		etTitulo = findViewById(R.id.editTextTitulo);
		etTexto = findViewById(R.id.editTextTexto);
		btSalvar = findViewById(R.id.buttonNota);
		
		tvId.setEnabled(false);
		tvId.setVisibility(View.INVISIBLE);
		etId.setEnabled(false);
		etId.setVisibility(View.INVISIBLE);
		btSalvar.setText("Salva Nota");
		
		btSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				salvarNota(v);
			}
		});
	}

	public void salvarNota(View v){
		Nota nota = notaController.cadastrarNovaNota(new Nota(etTitulo.getText().toString(), etTexto.getText().toString()));
		if(nota == null){
			Toast.makeText(this, "Erro ao inserir nota...", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Nota adicionada com sucesso!", Toast.LENGTH_SHORT).show();
			btSalvar.setEnabled(false);
			finish();
		}
	}
}