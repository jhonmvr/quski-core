package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class OpcionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long plazo;
	private BigDecimal montoCredito;
	private BigDecimal riesgoAcumulado;
	private BigDecimal valorDesembolso;
	private BigDecimal cuota;
	
	public Long getPlazo() {
		return plazo;
	}
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}
	public BigDecimal getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}
	public BigDecimal getRiesgoAcumulado() {
		return riesgoAcumulado;
	}
	public void setRiesgoAcumulado(BigDecimal riesgoAcumulado) {
		this.riesgoAcumulado = riesgoAcumulado;
	}
	public BigDecimal getValorDesembolso() {
		return valorDesembolso;
	}
	public void setValorDesembolso(BigDecimal valorDesembolso) {
		this.valorDesembolso = valorDesembolso;
	}
	public BigDecimal getCuota() {
		return cuota;
	}
	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}
	
	
	
}
