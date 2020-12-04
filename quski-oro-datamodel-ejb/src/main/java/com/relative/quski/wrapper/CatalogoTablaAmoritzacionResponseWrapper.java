package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class CatalogoTablaAmoritzacionResponseWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2944859038807620286L;

	private List<CatalogoTablaAmortizacionWrapper> tablaAmortizacion;
	private Boolean esId;
	private Boolean existeError;
	private String mensaje;
	private String descripcion;
	private String codigoServicio;
	private String validaciones;

	public List<CatalogoTablaAmortizacionWrapper> getTablaAmortizacion() {
		return tablaAmortizacion;
	}
	public void setTablaAmortizacion(List<CatalogoTablaAmortizacionWrapper> tablaAmortizacion) {
		this.tablaAmortizacion = tablaAmortizacion;
	}
	public Boolean getEsId() {
		return esId;
	}
	public void setEsId(Boolean esId) {
		this.esId = esId;
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
