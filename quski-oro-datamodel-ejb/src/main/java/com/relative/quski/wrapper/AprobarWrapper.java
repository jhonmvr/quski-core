package com.relative.quski.wrapper;

import java.io.Serializable;

public class AprobarWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroOperacion;
	private String uriHabilitantesFirmados;
	private DatosRegistroWrapper datosRegistro;
	
	
	
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getUriHabilitantesFirmados() {
		return uriHabilitantesFirmados;
	}
	public void setUriHabilitantesFirmados(String uriHabilitantesFirmados) {
		this.uriHabilitantesFirmados = uriHabilitantesFirmados;
	}
	public DatosRegistroWrapper getDatosRegistro() {
		return datosRegistro;
	}
	public void setDatosRegistro(DatosRegistroWrapper datosRegistro) {
		this.datosRegistro = datosRegistro;
	}
	
}
