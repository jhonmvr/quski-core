package com.relative.quski.wrapper;

import java.io.Serializable;

public class SoftbankRespuestaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public SoftbankRespuestaWrapper( ) {
	}
	
	private Boolean existeError;
	private String mensaje ;
	private String codigoServicio;
	private String validaciones;
	
	public Boolean getExisteError() {
		return existeError;
	}
	public void setExisteError(Boolean existeError) {
		this.existeError = existeError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}
}
