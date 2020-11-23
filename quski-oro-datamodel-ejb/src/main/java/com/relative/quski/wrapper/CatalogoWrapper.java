package com.relative.quski.wrapper;

import java.io.Serializable;

public class CatalogoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763330066421548666L;
	
	private String codigo;
	private String nombre;
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
	
	

}
