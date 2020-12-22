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

}
