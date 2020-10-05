package com.relative.quski.wrapper;

import java.io.Serializable;

public class EquifaxVariableWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orden;
	private String codigo;
	private String nombre;
	private String valor;
	
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
