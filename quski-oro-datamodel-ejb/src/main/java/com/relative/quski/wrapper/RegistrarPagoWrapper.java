package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoRegistrarPago;

public class RegistrarPagoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoClientePago cliente;
	private List<TbQoRegistrarPago> pagos;
	
	public List<TbQoRegistrarPago> getPagos() {
		return pagos;
	}
	public void setPagos(List<TbQoRegistrarPago> pagos) {
		this.pagos = pagos;
	}
	public TbQoClientePago getCliente() {
		return cliente;
	}
	public void setCliente(TbQoClientePago cliente) {
		this.cliente = cliente;
	}
	
}