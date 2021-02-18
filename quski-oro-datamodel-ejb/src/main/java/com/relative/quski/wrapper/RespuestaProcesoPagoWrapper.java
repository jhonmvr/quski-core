package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRegistrarPago;

public class RespuestaProcesoPagoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoProceso proceso;
	private TbQoClientePago cliente;
	private List<TbQoRegistrarPago> pagos;
	
	public TbQoProceso getProceso() {
		return proceso;
	}
	public void setProceso(TbQoProceso proceso) {
		this.proceso = proceso;
	}
	public TbQoClientePago getCliente() {
		return cliente;
	}
	public void setCliente(TbQoClientePago cliente) {
		this.cliente = cliente;
	}
	public List<TbQoRegistrarPago> getPagos() {
		return pagos;
	}
	public void setPagos(List<TbQoRegistrarPago> pagos) {
		this.pagos = pagos;
	}
}
