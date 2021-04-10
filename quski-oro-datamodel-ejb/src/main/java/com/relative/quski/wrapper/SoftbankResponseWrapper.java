package com.relative.quski.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SoftbankResponseWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "SoftbankResponseWrapper [existeError=" + ExisteError + ", mensaje=" + Mensaje + ", descripcion="
				+ Descripcion + ", validaciones=" + Validaciones + ", codigoServicio=" + CodigoServicio + "]";
	}
	@JsonProperty("ExisteError")
	private Boolean ExisteError;
	
	@JsonProperty("Mensaje")
	private String Mensaje ;
	
	@JsonProperty("Descripcion")
	private String Descripcion;
	
	@JsonProperty("Validaciones")
	private String Validaciones;
	
	@JsonProperty("CodigoServicio")
	private String CodigoServicio;
	
	
	
	
	@JsonGetter("ExisteError")
	public Boolean getExisteError() {
		return ExisteError;
	}
	public void setExisteError(Boolean existeError) {
		this.ExisteError = existeError;
	}
    @JsonGetter("Mensaje")
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		this.Mensaje = mensaje;
	}
	@JsonGetter("Descripcion")
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	@JsonGetter("Validaciones")
	public String getValidaciones() {
		return Validaciones;
	}
	public void setValidaciones(String validaciones) {
		this.Validaciones = validaciones;
	}
	@JsonGetter("CodigoServicio")
	public String getCodigoServicio() {
		return CodigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.CodigoServicio = codigoServicio;
	}
	
	
}
