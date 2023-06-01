
package com.ifsc.myapplication.model;

public class Nota {
	
	Integer idNota;
	String titulo, txt;
	
	public Nota(Integer id, String titulo, String txt){
		this.idNota = id;
		this.titulo = titulo;
		this.txt = txt;
	}
	
	public Nota(String titulo, String txt){
		this.titulo = titulo;
		this.txt = txt;
	}
	
	public Integer getIdNota() {
		return this.idNota;
	}

	// EXTRA
	public void setIdNota(Integer idNota) {
		this.idNota = idNota;
	}
	
	public String getTitulo() {
		return this.titulo;
	}

	// estranho ter que retornar boolean...
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTexto() {
		return this.txt;
	}
	
	// estranho ter que retornar boolean...
	public void setTexto(String txt) {
		this.txt = txt;
	}
}