package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class RespuestaBooleanaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Boolean bandera;
	private String mensaje;
	
	
	public Boolean getBandera() {
		return bandera;
	}
	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
