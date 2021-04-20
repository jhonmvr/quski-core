package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;

public class CrearRenovacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoCreditoNegociacion credito;
	private List<RegistroPagoRenovacionWrapper> pagos;
	private String asesor;
	
	
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public List<RegistroPagoRenovacionWrapper> getPagos() {
		return pagos;
	}
	public void setPagos(List<RegistroPagoRenovacionWrapper> pagos) {
		this.pagos = pagos;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
}
