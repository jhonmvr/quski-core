package com.relative.quski.wrapper;

import java.io.Serializable;

public class DatosBloqueoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoMotivoBloqueo;
	private String descripcion;
	private Boolean esTransitorio;
	
	
	public String getCodigoMotivoBloqueo() {
		return codigoMotivoBloqueo;
	}
	public void setCodigoMotivoBloqueo(String codigoMotivoBloqueo) {
		this.codigoMotivoBloqueo = codigoMotivoBloqueo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEsTransitorio() {
		return esTransitorio;
	}
	public void setEsTransitorio(Boolean esTransitorio) {
		this.esTransitorio = esTransitorio;
	}
	
	
}
