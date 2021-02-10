package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class RespuestaAprobarWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoServicio;
	private String descripcion;
	private Boolean existeError;
	private String mensaje;
	private BigDecimal montoEntregado;
	private String numeroOperacion;
	private String validaciones;
	
	public String getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public BigDecimal getMontoEntregado() {
		return montoEntregado;
	}
	public void setMontoEntregado(BigDecimal montoEntregado) {
		this.montoEntregado = montoEntregado;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getValidaciones() {
		return validaciones;
	}
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}

}
