package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class SoftbankRiesgoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -365985272182192369L;
	private List<SoftbankOperacionWrapper> operaciones;
	private Boolean existeError;
	private String mensaje;
	private String descripcion;
	private int codigoServicio;
	private String validaciones;
	
	public List<SoftbankOperacionWrapper> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<SoftbankOperacionWrapper> operaciones) {
		this.operaciones = operaciones;
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
	public int getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(int codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}

	
	                      
	             
}