package com.relative.quski.wrapper;

import java.io.Serializable;

public class TelefonosContactoClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1633750440658513896L;
	
	private String codigoTipoTelefono;
    private String numero;
    
    
	public TelefonosContactoClienteWrapper(String codigoTipoTelefono, String numero) {
		super();
		this.codigoTipoTelefono = codigoTipoTelefono;
		this.numero = numero;
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
