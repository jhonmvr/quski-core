package com.relative.quski.wrapper;

import java.io.Serializable;

public class ConsultaGarantiaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String numeroOperacionMadre;


	public ConsultaGarantiaWrapper(String numeroOperacionMadre) {
		super();
		this.numeroOperacionMadre = numeroOperacionMadre;
	}


	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}


	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}

}
