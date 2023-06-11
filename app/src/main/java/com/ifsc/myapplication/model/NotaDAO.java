package com.ifsc.myapplication.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

public class NotaDAO {
	SQLiteDatabase db;
	
	public NotaDAO(Context c){
		db = c.openOrCreateDatabase("dbNotas", c.MODE_PRIVATE, null);
		
		String sql = "CREATE TABLE IF NOT EXISTS notas " +
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, texto VARCHAR)";
		try{
			db.validateSql(sql, null);
			db.execSQL(sql);
		}
		catch(SQLiteException e){
			System.out.println("Exception to create DB tables. Erro: " + e);
		}
	}
	
	public Nota insereNota(Nota nota){
		ContentValues notaValue = new ContentValues();
		notaValue.put("titulo", nota.getTitulo());
		notaValue.put("texto", nota.getTexto());
		
		Integer idAdded = (int) db.insert("notas", null, notaValue);
		nota.setIdNota(idAdded);
		
		if(idAdded < 0){
			System.out.println("Falha ao adicionar entrada no banco. Retorno: " + idAdded);
			return null;
		}
		System.out.println("Fim insereNota");
		return nota;
	}
	
	public Boolean updateNota(Nota nota){
		int id = nota.getIdNota();
		String titulo = nota.getTitulo();
		String texto = nota.getTexto();
		
		ContentValues notaValue = new ContentValues();
		notaValue.put("titulo", titulo);
		notaValue.put("texto", texto);

		int affectedRows = db.update("notas", notaValue, "id=" + id, null);
		if(affectedRows != 1){
			System.out.println("Falha ao atualizar entrada no banco. Quantidade de linhas afetadas" +
					" pela atualizacao: " + affectedRows);
			return false;
		}
		System.out.println("Fim updateNota");
		return true;
	}
	
	public Boolean deleteNota(Nota nota){
		int id = nota.getIdNota();
		
		int affectedRows = db.delete("notas", "id=" + id, null);
		if(affectedRows != 1){
			System.out.println("Falha ao deletar entrada no banco. Quantidade de linhas afetadas" +
					"pela delecao: " + affectedRows);
			return false;
		}
		System.out.println("Fim deleteNota");
		return true;
	}
	
	@SuppressLint("Range")
	public Nota getNota(Integer idNota){
		String sql = "SELECT * FROM notas WHERE id=" + idNota;
		try{
			db.validateSql(sql, null);
		}
		catch(SQLiteException e){
			System.out.println("Exception in SQL to select nota. Erro: " + e);
			return null;
		}
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();

		int qtdRetornada = cursor.getCount();
		if(qtdRetornada != 1){
			System.out.println("Falha ao selecionar nota. Quantidade de entradas" +
					" retornadas é: " + qtdRetornada);
			return null;
		}
		
		idNota = cursor.getInt(cursor.getColumnIndex("id"));
		String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
		String texto = cursor.getString(cursor.getColumnIndex("texto"));
		Nota nota = new Nota(idNota, titulo, texto);
		
		System.out.println("Fim getNota");
		return nota;
	}
	
	public ArrayList<Nota> getListaNotas(String pesquisa){
		ArrayList<Nota> listaNotas = new ArrayList<>();

		String sql ="SELECT * FROM notas WHERE titulo LIKE ?";
		try{
			db.validateSql(sql, null);
		}
		catch(SQLiteException e){
			System.out.println("Exception in SQL to select notas. Erro: " + e);
			return listaNotas; // retornando lista vazia...
		}

		Cursor cursor = db.rawQuery(sql, new String[] {"%" + pesquisa + "%"});
		cursor.moveToFirst();
	
		int qtdRetornada = cursor.getCount();
		System.out.println("Fim qtdRetornada: "+ qtdRetornada);
		for (int i = 0; i < qtdRetornada; i++){
			@SuppressLint("Range") Integer idNota = cursor.getInt(cursor.getColumnIndex("id"));
			@SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
			@SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("texto"));
			Nota nota = new Nota(idNota, titulo, texto);
			listaNotas.add(nota);
			
			cursor.moveToNext();
		}
		System.out.println("Fim getListaNotas");
		return listaNotas;
	}

	public ArrayList<Nota> getListaNotas(){
		ArrayList<Nota> listaNotas = new ArrayList<>();

		String sql = "SELECT * FROM notas";
		try{
			db.validateSql(sql, null);
		}
		catch(SQLiteException e){
			System.out.println("Exception in SQL to select notas. Erro: " + e);
			return listaNotas; // retornando lista vazia...
		}

		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();

		int qtdRetornada = cursor.getCount();
		System.out.println("Fim qtdRetornada: "+ qtdRetornada);
		for (int i = 0; i < qtdRetornada; i++){
			@SuppressLint("Range") Integer idNota = cursor.getInt(cursor.getColumnIndex("id"));
			@SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
			@SuppressLint("Range") String texto = cursor.getString(cursor.getColumnIndex("texto"));
			Nota nota = new Nota(idNota, titulo, texto);
			listaNotas.add(nota);

			cursor.moveToNext();
		}
		System.out.println("Fim getListaNotas");
		return listaNotas;
	}
	
}
