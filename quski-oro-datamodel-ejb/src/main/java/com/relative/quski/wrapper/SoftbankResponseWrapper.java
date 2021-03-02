package com.relative.quski.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SoftbankResponseWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public SoftbankResponseWrapper( ) {
	}
	@JsonProperty("ExisteError")
	private Boolean existeError;
	@JsonProperty("Mensaje")
	private String mensaje ;
	@JsonProperty("Descripcion")
	private String descripcion;
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
