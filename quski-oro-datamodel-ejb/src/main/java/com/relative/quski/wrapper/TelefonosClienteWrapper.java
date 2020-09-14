package com.relative.quski.wrapper;

import java.io.Serializable;

public class TelefonosClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7814077128512924353L;

	private Long id;                                   
	private String codigoTipoTelefono;                  
	private String numero;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoTipoTelefono() {
		return codigoTipoTelefono;
	}
	public void setCodigoTipoTelefono(String codigoTipoTelefono) {
		this.codigoTipoTelefono = codigoTipoTelefono;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
