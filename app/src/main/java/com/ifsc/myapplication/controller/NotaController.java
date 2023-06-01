package com.ifsc.myapplication.controller;

import android.content.Context;

import com.ifsc.myapplication.model.Nota;
import com.ifsc.myapplication.model.NotaDAO;

import java.util.ArrayList;


public class NotaController {
	Context mContext;
	NotaDAO notaDAO;
	
	public NotaController(Context c){
		this.mContext = c;
		// controlador é quem deve falar com o DB...
		this.notaDAO = new NotaDAO(this.mContext);
	}
	
	public Nota cadastrarNovaNota(Nota nota){
		return this.notaDAO.insereNota(nota);
	}
	
	public Boolean atualizaNota(Nota nota){
		return this.notaDAO.updateNota(nota);
	}
	
	public Boolean excluirNota(Nota nota) {
		return this.notaDAO.deleteNota(nota);
	}
	
	public Nota getNota(Integer idNota) {
		return this.notaDAO.getNota(idNota);
	}
	
	public ArrayList<Nota> getListaNotas() {
		return this.notaDAO.getListaNotas();
	}
}