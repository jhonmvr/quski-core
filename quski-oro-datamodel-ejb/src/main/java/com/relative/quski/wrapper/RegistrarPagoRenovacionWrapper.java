package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class RegistrarPagoRenovacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RegistroPagoRenovacionWrapper> pagos;
	private String asesor;
	private Long idCredito;
	
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
	public Long getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}

}
