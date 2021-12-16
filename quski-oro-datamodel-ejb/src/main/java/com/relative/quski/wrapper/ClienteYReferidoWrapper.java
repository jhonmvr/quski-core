package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoReferido;

public class ClienteYReferidoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteYReferidoWrapper() {
		
	}
    private TbQoCliente cliente;
    private TbQoReferido referido;
    private Boolean bandera;
    
	public TbQoCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbQoCliente cliente) {
		this.cliente = cliente;
	}
	public TbQoReferido getReferido() {
		return referido;
	}
	public void setReferido(TbQoReferido referido) {
		this.referido = referido;
	}
	public Boolean getBandera() {
		return bandera;
	}
	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}
    
    
	
}
