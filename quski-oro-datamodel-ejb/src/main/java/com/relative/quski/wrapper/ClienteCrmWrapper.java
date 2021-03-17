package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoTelefonoCliente;

public class ClienteCrmWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoCliente cliente;
	private TbQoTelefonoCliente tlfMovil;
	private TbQoTelefonoCliente tlfCasa;
	
	public TbQoCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbQoCliente cliente) {
		this.cliente = cliente;
	}
	public TbQoTelefonoCliente getTlfMovil() {
		return tlfMovil;
	}
	public void setTlfMovil(TbQoTelefonoCliente tlfMovil) {
		this.tlfMovil = tlfMovil;
	}
	public TbQoTelefonoCliente getTlfCasa() {
		return tlfCasa;
	}
	public void setTlfCasa(TbQoTelefonoCliente tlfCasa) {
		this.tlfCasa = tlfCasa;
	}

}
