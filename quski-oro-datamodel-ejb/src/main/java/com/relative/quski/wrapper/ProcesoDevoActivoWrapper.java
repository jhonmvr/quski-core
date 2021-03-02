package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProcesoDevoActivoWrapper implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal   id;
	private String codigo;
	private String estadoProceso;
	
	public ProcesoDevoActivoWrapper(BigDecimal id, String codigo, String estadoProceso) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.estadoProceso = estadoProceso;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
}
