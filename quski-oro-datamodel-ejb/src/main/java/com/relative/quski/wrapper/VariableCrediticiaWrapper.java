package com.relative.quski.wrapper;

import java.io.Serializable;

public class VariableCrediticiaWrapper implements Serializable {
	private int orden;
	private String nombre;
	private String valor;
	
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	


}
