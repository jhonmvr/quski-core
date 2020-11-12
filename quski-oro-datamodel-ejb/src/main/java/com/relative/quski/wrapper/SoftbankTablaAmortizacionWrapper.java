package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class SoftbankTablaAmortizacionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8347277449265944235L;
	
	private List<CuotasAmortizacionWrapper> cuotas;
	private Boolean existeError;
	private String codigoError;
	
	public List<CuotasAmortizacionWrapper> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<CuotasAmortizacionWrapper> cuotas) {
		this.cuotas = cuotas;
	}
	public Boolean getExisteError() {
		return existeError;
	}
	public void setExisteError(Boolean existeError) {
		this.existeError = existeError;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
}
