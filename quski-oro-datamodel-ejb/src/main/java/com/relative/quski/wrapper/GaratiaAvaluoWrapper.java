package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class GaratiaAvaluoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3083644948983699020L;

	private Long numeroGarantia;
	private Long numeroExpediente;
	private String fechaAvaluo;
	private BigDecimal valorComercial;
	private BigDecimal valorAvaluo;
	private BigDecimal valorRealizacion;
	private BigDecimal valorOro;
	public Long getNumeroGarantia() {
		return numeroGarantia;
	}
	public void setNumeroGarantia(Long numeroGarantia) {
		this.numeroGarantia = numeroGarantia;
	}
	public Long getNumeroExpediente() {
		return numeroExpediente;
	}
	public void setNumeroExpediente(Long numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	public String getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(String fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}
	public BigDecimal getValorComercial() {
		return valorComercial;
	}
	public void setValorComercial(BigDecimal valorComercial) {
		this.valorComercial = valorComercial;
	}
	public BigDecimal getValorAvaluo() {
		return valorAvaluo;
	}
	public void setValorAvaluo(BigDecimal valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}
	public BigDecimal getValorRealizacion() {
		return valorRealizacion;
	}
	public void setValorRealizacion(BigDecimal valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}
	public BigDecimal getValorOro() {
		return valorOro;
	}
	public void setValorOro(BigDecimal valorOro) {
		this.valorOro = valorOro;
	}

	
}
