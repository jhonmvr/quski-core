package com.relative.quski.wrapper;

import java.io.Serializable;


public class ConsultaTablaWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroOperacion;
    private String UriHabilitantesFirmados;
	private DatosRegistroWrapper datosRegistro;
	
	public ConsultaTablaWrapper(String numeroOperacion, String uriHabilitantesFirmados,
			DatosRegistroWrapper datosRegistro) {
		super();
		this.numeroOperacion = numeroOperacion;
		UriHabilitantesFirmados = uriHabilitantesFirmados;
		this.datosRegistro = datosRegistro;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getUriHabilitantesFirmados() {
		return UriHabilitantesFirmados;
	}
	public void setUriHabilitantesFirmados(String uriHabilitantesFirmados) {
		UriHabilitantesFirmados = uriHabilitantesFirmados;
	}
	public DatosRegistroWrapper getDatosRegistro() {
		return datosRegistro;
	}
	public void setDatosRegistro(DatosRegistroWrapper datosRegistro) {
		this.datosRegistro = datosRegistro;
	} 

}
