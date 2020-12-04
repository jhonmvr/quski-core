package com.relative.quski.wrapper;

import java.io.Serializable;

public class ConsultaOperacionGlobalWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroOperacion;
	private Long numeroPagina;
	private Long tamanioPagina;
	
	
	public ConsultaOperacionGlobalWrapper(String numeroOperacion) {
		super();
		this.numeroOperacion = numeroOperacion;
		this.numeroPagina = Long.valueOf( 1 );
		this.tamanioPagina = Long.valueOf( 5 );
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Long getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(Long numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public Long getTamanioPagina() {
		return tamanioPagina;
	}
	public void setTamanioPagina(Long tamanioPagina) {
		this.tamanioPagina = tamanioPagina;
	}
	

}
