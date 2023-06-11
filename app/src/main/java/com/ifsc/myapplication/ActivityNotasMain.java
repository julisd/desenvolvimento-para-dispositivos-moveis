package com.ifsc.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.ifsc.myapplication.controller.NotaController;
import com.ifsc.myapplication.model.Nota;

import java.util.ArrayList;

public class ActivityNotasMain extends AppCompatActivity implements AdapterView.OnItemLongClickListener, SearchView.OnQueryTextListener {
	ListView listView;
	NotaController notaController;
	SearchView searchView;

	NotaAdapterListView notaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		notaController = new NotaController(getApplicationContext());
		listView = findViewById(R.id.listView);
		searchView = findViewById(R.id.searchView);
		notaAdapter = new NotaAdapterListView(
				getApplicationContext(),
				R.layout.list_notas,
				new ArrayList()
		);
		listView.setAdapter(notaAdapter);
		listView.setOnItemLongClickListener(this);
		searchView.setOnQueryTextListener(this);
	}
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				final Nota nota = (Nota) adapterView.getItemAtPosition(i);
				System.out.println("Click longo: nota.getIdNota(): "+ nota.getIdNota());
				System.out.println("Click normal: position: "+ i);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNotasMain.this);
				builder.setTitle("Selecione a Acao: ")
						.setItems(new String[]{"Editar", "Excluir"}, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								switch(which){
									case 0:
										editaNota(view, nota.getIdNota());
										break;
									case 1:
										excluirNota(view, nota.getIdNota());
										break;
									default:
										System.out.println("Descarte...");
										break;
								}
							}
						});
				builder.create();
				builder.show();
				return true;
			}
	
	protected void onStart() {
		super.onStart();
		showNotas();
	}

	public void novaNota(View v){
		Intent intent = new Intent(this, ActivityAddNota.class);
		startActivity(intent);
	}
	
	public void editaNota(View v, Integer idNota){
		Nota nota = notaController.getNota(idNota);
		Intent intent = new Intent(this, ActivityUpdateNota.class);

		intent.putExtra("id_nota", nota.getIdNota().toString());
		intent.putExtra("titulo_nota", nota.getTitulo());
		intent.putExtra("texto_nota", nota.getTexto());
		startActivity(intent);
	}
	
	public void excluirNota(View v, Integer idNota){
		Nota nota = notaController.getNota(idNota);
		Boolean resultado = notaController.excluirNota(nota);
		if(resultado == true){
			Toast.makeText(this, "Nota excluida com sucesso!", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Erro ao deletar nota...", Toast.LENGTH_SHORT).show();
		}
		showNotas();
	}
	
	public void exibirNota(View v, Integer idNota){
		Nota nota = notaController.getNota(idNota);
		
		Intent intent = new Intent(this, ActivityGetNota.class);
		intent.putExtra("id_nota", nota.getIdNota().toString());
		intent.putExtra("titulo_nota", nota.getTitulo());
		intent.putExtra("texto_nota", nota.getTexto());
		startActivity(intent);
	}
	
	public void showNotas(){
		notaAdapter.clear();
		notaAdapter.addAll(notaController.getListaNotas());
	}

	@Override
	public boolean onQueryTextSubmit(String s) {
		return false;
	}

	@Override
	public boolean onQueryTextChange(String s) {
		listView.setAdapter(
				new NotaAdapterListView(
						getApplicationContext(),
						R.layout.list_notas,
						notaController.getListaNotas(s)
				)
		);
		return false;

	}
}
