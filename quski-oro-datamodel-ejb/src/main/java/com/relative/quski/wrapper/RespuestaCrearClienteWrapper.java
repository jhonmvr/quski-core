package com.relative.quski.wrapper;

import java.io.Serializable;

public class RespuestaCrearClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -216556034267589986L;
	 
	private Long idCredito;
	private Long numeroCreditos;
	private Long idNegociacion;
	public Long getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}
	public Long getNumeroCreditos() {
		return numeroCreditos;
	}
	public void setNumeroCreditos(Long numeroCreditos) {
		this.numeroCreditos = numeroCreditos;
	}
	public Long getIdNegociacion() {
		return idNegociacion;
	}
	public void setIdNegociacion(Long idNegociacion) {
		this.idNegociacion = idNegociacion;
	}
	
	
}
