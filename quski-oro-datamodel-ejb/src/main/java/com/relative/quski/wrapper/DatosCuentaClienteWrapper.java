package com.relative.quski.wrapper;

import java.io.Serializable;

public class DatosCuentaClienteWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idBanco;
	private String  numero;
    private Boolean esAhorros;
    
    
    public DatosCuentaClienteWrapper(Long idBanco, String numero, Boolean esAhorros) {
		super();
		this.idBanco = idBanco;
		this.numero = numero;
		this.esAhorros = esAhorros;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Boolean getEsAhorros() {
		return esAhorros;
	}
	public void setEsAhorros(Boolean esAhorros) {
		this.esAhorros = esAhorros;
	}

}
