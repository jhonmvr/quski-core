package com.relative.quski.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SoftbankResponseWrapper {
	public SoftbankResponseWrapper( ) {
	}
	@JsonProperty("ExisteError")
	private Boolean existeError;
	
	@JsonProperty("Mensaje")
	private String mensaje ;
	
	@JsonProperty("Descripcion")
	private String descripcion;
	
	@JsonProperty("Validaciones")
	private String validaciones;
	
	@JsonProperty("CodigoServicio")
	private String codigoServicio;
	
	
	
	
	@JsonProperty("ExisteError")
	public Boolean getExisteError() {
		return existeError;
	}
	public void setExisteError(Boolean existeError) {
		this.existeError = existeError;
	}
	@JsonProperty("Mensaje")
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@JsonProperty("Descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@JsonProperty("Validaciones")
	public String getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}
	@JsonProperty("CodigoServicio")
	public String getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	
	
}
