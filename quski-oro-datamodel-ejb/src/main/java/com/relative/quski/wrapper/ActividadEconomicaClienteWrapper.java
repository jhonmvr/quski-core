package com.relative.quski.wrapper;

import java.io.Serializable;

public class ActividadEconomicaClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8347277449265944235L;
	
	private Long idActividadEconomica;

	public Long getIdActividadEconomica() {
		return idActividadEconomica;
	}

	public void setIdActividadEconomica(Long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}
}
