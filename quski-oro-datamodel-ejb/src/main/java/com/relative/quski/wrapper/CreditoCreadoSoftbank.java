package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoCreadoSoftbank  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TbQoCreditoNegociacion credito;
	private List<CuotasAmortizacionWrapper>  cuotasAmortizacion;
	
	public CreditoCreadoSoftbank(TbQoCreditoNegociacion credito) {
		super();
		this.credito = credito;
	}public CreditoCreadoSoftbank() {
	}

	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public List<CuotasAmortizacionWrapper> getCuotasAmortizacion() {
		return cuotasAmortizacion;
	}
	public void setCuotasAmortizacion(List<CuotasAmortizacionWrapper> cuotasAmortizacion) {
		this.cuotasAmortizacion = cuotasAmortizacion;
	}
	
}
