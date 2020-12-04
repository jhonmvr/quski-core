package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class RespuestaConsultaGlobalWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long numeroTotalRegistros;
	private List<OperacionWrapper> operaciones;
	
	
	public Long getNumeroTotalRegistros() {
		return numeroTotalRegistros;
	}
	public void setNumeroTotalRegistros(Long numeroTotalRegistros) {
		this.numeroTotalRegistros = numeroTotalRegistros;
	}
	public List<OperacionWrapper> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<OperacionWrapper> operaciones) {
		this.operaciones = operaciones;
	}

}
