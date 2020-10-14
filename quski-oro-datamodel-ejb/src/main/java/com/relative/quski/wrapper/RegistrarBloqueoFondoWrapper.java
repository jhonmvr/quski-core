package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoClientePago;

public class RegistrarBloqueoFondoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoClientePago cliente;
	private List<RegistroBloqueoFondoWrapper> bloqueos;
	
	
	public List<RegistroBloqueoFondoWrapper> getBloqueos() {
		return bloqueos;
	}
	public void setBloqueos(List<RegistroBloqueoFondoWrapper> bloqueos) {
		this.bloqueos = bloqueos;
	}
	public TbQoClientePago getCliente() {
		return cliente;
	}
	public void setCliente(TbQoClientePago cliente) {
		this.cliente = cliente;
	}
	
	
}