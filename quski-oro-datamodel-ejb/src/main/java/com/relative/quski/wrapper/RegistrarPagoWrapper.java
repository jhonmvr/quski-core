package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoClientePago;

public class RegistrarPagoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoClientePago cliente;
	private List<RegistroPagoWrapper> pagos;
	

	public TbQoClientePago getCliente() {
		return cliente;
	}
	public void setCliente(TbQoClientePago cliente) {
		this.cliente = cliente;
	}
	public List<RegistroPagoWrapper> getPagos() {
		return pagos;
	}
	public void setPagos(List<RegistroPagoWrapper> pagos) {
		this.pagos = pagos;
	}
	
}