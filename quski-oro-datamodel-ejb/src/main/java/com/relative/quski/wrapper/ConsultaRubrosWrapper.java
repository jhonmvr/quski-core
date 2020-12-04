package com.relative.quski.wrapper;

import java.io.Serializable;

public class ConsultaRubrosWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroOperacion;

	public ConsultaRubrosWrapper(String numeroOperacion) {
		super();
		this.numeroOperacion = numeroOperacion;
	}

	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

}
