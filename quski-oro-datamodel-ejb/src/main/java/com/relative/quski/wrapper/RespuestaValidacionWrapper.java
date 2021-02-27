package com.relative.quski.wrapper;

import java.io.Serializable;

public class RespuestaValidacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean existe;
	private String  mensaje;
	
	public Boolean getExiste() {
		return existe;
	}
	public void setExiste(Boolean existe) {
		this.existe = existe;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	} 

}
