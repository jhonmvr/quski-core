package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class ConsultaGlobalRespuestaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OperacionGlobalWrapper> operaciones;
	private Long numeroTotalRegistros;
	private Boolean existeError;
	private String mensaje;
	public List<OperacionGlobalWrapper> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<OperacionGlobalWrapper> operaciones) {
		this.operaciones = operaciones;
	}
	public Long getNumeroTotalRegistros() {
		return numeroTotalRegistros;
	}
	public void setNumeroTotalRegistros(Long numeroTotalRegistros) {
		this.numeroTotalRegistros = numeroTotalRegistros;
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

}
