package com.relative.quski.wrapper;

import java.io.Serializable;

public class SoftbankCuentasBancariasWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 130733777631732469L;
	
	private Long id;
    private Long idBanco;
    private String cuenta;
    private Boolean activo;
    private Boolean esAhorros;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Boolean getEsAhorros() {
		return esAhorros;
	}
	public void setEsAhorros(Boolean esAhorros) {
		this.esAhorros = esAhorros;
	}
	
}
