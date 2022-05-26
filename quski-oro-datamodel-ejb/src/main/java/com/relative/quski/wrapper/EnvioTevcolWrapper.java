package com.relative.quski.wrapper;

import java.io.Serializable;

public class EnvioTevcolWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4175137655893431610L;
	
	private String codigoOperacion;
	private String cedulaCliente;
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	
	
	
}
