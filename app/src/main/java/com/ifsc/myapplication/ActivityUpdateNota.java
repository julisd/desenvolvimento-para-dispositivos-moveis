package com.ifsc.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ifsc.myapplication.controller.NotaController;
import com.ifsc.myapplication.model.Nota;

public class ActivityUpdateNota extends AppCompatActivity {
	NotaController notaController;
	EditText etTitulo, etTexto, etId;
	Button btSalvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibe_nota);
		
		notaController = new NotaController(getApplicationContext());
		
		etId = findViewById(R.id.editTextId);
		etTitulo = findViewById(R.id.editTextTitulo);
		etTexto = findViewById(R.id.editTextTexto);
		btSalvar = findViewById(R.id.buttonNota);
		
		etId.setEnabled(false);
		btSalvar.setText("Atualiza Nota");
		
		btSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				atualizaNota(v);
			}
		});
		
		Intent intent = getIntent();
		etId.setText(intent.getStringExtra("id_nota"));
		etTitulo.setText(intent.getStringExtra("titulo_nota"));
		etTexto.setText(intent.getStringExtra("texto_nota"));
	}
	
	public void atualizaNota(View v){
		boolean resultado = notaController.atualizaNota(new Nota(Integer.parseInt(etId.getText().toString()), etTitulo.getText().toString(), etTexto.getText().toString()));
		if(resultado == false){
			Toast.makeText(this, "Erro ao atualizar nota...", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Nota atualizada com sucesso!", Toast.LENGTH_SHORT).show();
			btSalvar.setEnabled(false);
			finish();
		}
	}
}