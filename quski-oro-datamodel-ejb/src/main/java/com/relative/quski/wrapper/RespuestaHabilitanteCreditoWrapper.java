package com.relative.quski.wrapper;

import java.io.Serializable;

public class RespuestaHabilitanteCreditoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 729571428093235111L;

	private String numeroOperacion;
	private String uriHabilitantes;
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getUriHabilitantes() {
		return uriHabilitantes;
	}
	public void setUriHabilitantes(String uriHabilitantes) {
		this.uriHabilitantes = uriHabilitantes;
	}
	
	
}
