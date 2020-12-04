package com.relative.quski.wrapper;

import java.io.Serializable;

public class CatalogoIdWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763330066421548666L;
	
	private Long id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
