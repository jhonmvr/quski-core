package com.relative.quski.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SoftbankResponseWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public SoftbankResponseWrapper( ) {
	}
	@JsonProperty("WebUrl")
	private Boolean existeError;
	@JsonProperty("WebUrl")
	private String mensaje ;
	@JsonProperty("WebUrl")
	private String codigoServicio;
	@JsonProperty("WebUrl")
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
