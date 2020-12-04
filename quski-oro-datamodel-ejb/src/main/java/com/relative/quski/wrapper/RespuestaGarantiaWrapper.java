package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class RespuestaGarantiaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GarantiaOperacionWrapper> garantias; 
	private Boolean existeError;
	private String mensaje;
	private String descripcion;
	private String codigoServicio;
	private String validaciones;
	
	
	public List<GarantiaOperacionWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<GarantiaOperacionWrapper> garantias) {
		this.garantias = garantias;
	}
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
