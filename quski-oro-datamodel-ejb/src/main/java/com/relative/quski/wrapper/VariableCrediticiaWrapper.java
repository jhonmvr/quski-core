package com.relative.quski.wrapper;

import java.io.Serializable;

public class VariableCrediticiaWrapper implements Serializable {
	private int orden;
	private String variable;
	private String valor;
	
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	


}
