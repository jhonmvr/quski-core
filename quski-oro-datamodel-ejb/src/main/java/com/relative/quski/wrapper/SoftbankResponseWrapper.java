package com.relative.quski.wrapper;

import java.io.Serializable;

public class SoftbankResponseWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public SoftbankResponseWrapper( ) {
	}
	
	private Boolean ExisteError;
	private String Mensaje ;
	private String CodigoServicio;
	private String Validaciones;
	public Boolean getExisteError() {
		return ExisteError;
	}
	public void setExisteError(Boolean existeError) {
		ExisteError = existeError;
	}
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public String getCodigoServicio() {
		return CodigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		CodigoServicio = codigoServicio;
	}
	public String getValidaciones() {
		return Validaciones;
	}
	public void setValidaciones(String validaciones) {
		Validaciones = validaciones;
	}
	
}
