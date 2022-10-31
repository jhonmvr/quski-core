package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class ListadoOperacionIdNegociacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public ListadoOperacionIdNegociacionWrapper() {
		
	}
	private String codigo;
	private BigDecimal idNegociacion;
	
	
	public ListadoOperacionIdNegociacionWrapper(String codigo, BigDecimal idNegociacion) {
		super();
		this.codigo = codigo;
		this.idNegociacion = idNegociacion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getIdNegociacion() {
		return idNegociacion;
	}
	public void setIdNegociacion(BigDecimal idNegociacion) {
		this.idNegociacion = idNegociacion;
	}
	
	
	
	
	
	

}
