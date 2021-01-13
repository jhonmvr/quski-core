
package com.relative.quski.wrapper;

import java.io.Serializable;

public class HerederoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String nombre;
    private String cedula;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
   
	
	
}
