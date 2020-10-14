package com.relative.quski.wrapper;

import java.io.Serializable;

public class CalculadoraDescuentosOperacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double saldoMontoCreditoAnterior;
	private Double saldoInteresCreditoAnterior;
	private Double moraCreditoAnterior;
	private Double cobranzaCreditoAnterior;
	private String tipoCartera;
	private Double montoFinanciadoCreditoAnterior;
	private Integer plazoCreditoAnterior;
	private String tipoCreditoAnterior;
	private String estadoCreditoAnterior;
	private String fechaEfectivaCreditoAnterior;
	private String fechaVencimientoCreditoAnterior;
	private Double montoSolicitadoCliente;
	private String numeroOperacionMadre;
	private String numeroOperacionRenovar;
	private String referenciaPersonal;
	private String operacionPropia;

	
    
    public void generateMockup() {
    	this.setSaldoMontoCreditoAnterior(0.00);
		this.setSaldoInteresCreditoAnterior(0.00);
		this.setMoraCreditoAnterior(0.00);
		this.setCobranzaCreditoAnterior(0.00);
		this.setTipoCartera("");
		this.setMontoFinanciadoCreditoAnterior(0.00); 
		this.setPlazoCreditoAnterior(0);
		this.setTipoCreditoAnterior("");
		this.setEstadoCreditoAnterior("");
		this.setFechaEfectivaCreditoAnterior("");
		this.setFechaVencimientoCreditoAnterior("");
		this.setMontoSolicitadoCliente(0.00);
		this.setNumeroOperacionMadre("");
		this.setNumeroOperacionRenovar("");
		this.setReferenciaPersonal("");
		this.setOperacionPropia("");
    }



	public Double getSaldoMontoCreditoAnterior() {
		return saldoMontoCreditoAnterior;
	}



	public void setSaldoMontoCreditoAnterior(Double saldoMontoCreditoAnterior) {
		this.saldoMontoCreditoAnterior = saldoMontoCreditoAnterior;
	}



	public Double getSaldoInteresCreditoAnterior() {
		return saldoInteresCreditoAnterior;
	}



	public void setSaldoInteresCreditoAnterior(Double saldoInteresCreditoAnterior) {
		this.saldoInteresCreditoAnterior = saldoInteresCreditoAnterior;
	}



	public Double getMoraCreditoAnterior() {
		return moraCreditoAnterior;
	}



	public void setMoraCreditoAnterior(Double moraCreditoAnterior) {
		this.moraCreditoAnterior = moraCreditoAnterior;
	}



	public Double getCobranzaCreditoAnterior() {
		return cobranzaCreditoAnterior;
	}



	public void setCobranzaCreditoAnterior(Double cobranzaCreditoAnterior) {
		this.cobranzaCreditoAnterior = cobranzaCreditoAnterior;
	}



	public String getTipoCartera() {
		return tipoCartera;
	}



	public void setTipoCartera(String tipoCartera) {
		this.tipoCartera = tipoCartera;
	}



	public Double getMontoFinanciadoCreditoAnterior() {
		return montoFinanciadoCreditoAnterior;
	}



	public void setMontoFinanciadoCreditoAnterior(Double montoFinanciadoCreditoAnterior) {
		this.montoFinanciadoCreditoAnterior = montoFinanciadoCreditoAnterior;
	}



	public Integer getPlazoCreditoAnterior() {
		return plazoCreditoAnterior;
	}



	public void setPlazoCreditoAnterior(Integer plazoCreditoAnterior) {
		this.plazoCreditoAnterior = plazoCreditoAnterior;
	}



	public String getTipoCreditoAnterior() {
		return tipoCreditoAnterior;
	}



	public void setTipoCreditoAnterior(String tipoCreditoAnterior) {
		this.tipoCreditoAnterior = tipoCreditoAnterior;
	}



	public String getEstadoCreditoAnterior() {
		return estadoCreditoAnterior;
	}



	public void setEstadoCreditoAnterior(String estadoCreditoAnterior) {
		this.estadoCreditoAnterior = estadoCreditoAnterior;
	}



	public String getFechaEfectivaCreditoAnterior() {
		return fechaEfectivaCreditoAnterior;
	}



	public void setFechaEfectivaCreditoAnterior(String fechaEfectivaCreditoAnterior) {
		this.fechaEfectivaCreditoAnterior = fechaEfectivaCreditoAnterior;
	}



	public String getFechaVencimientoCreditoAnterior() {
		return fechaVencimientoCreditoAnterior;
	}



	public void setFechaVencimientoCreditoAnterior(String fechaVencimientoCreditoAnterior) {
		this.fechaVencimientoCreditoAnterior = fechaVencimientoCreditoAnterior;
	}



	public Double getMontoSolicitadoCliente() {
		return montoSolicitadoCliente;
	}



	public void setMontoSolicitadoCliente(Double montoSolicitadoCliente) {
		this.montoSolicitadoCliente = montoSolicitadoCliente;
	}



	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}



	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}



	public String getNumeroOperacionRenovar() {
		return numeroOperacionRenovar;
	}



	public void setNumeroOperacionRenovar(String numeroOperacionRenovar) {
		this.numeroOperacionRenovar = numeroOperacionRenovar;
	}



	public String getReferenciaPersonal() {
		return referenciaPersonal;
	}



	public void setReferenciaPersonal(String referenciaPersonal) {
		this.referenciaPersonal = referenciaPersonal;
	}



	public String getOperacionPropia() {
		return operacionPropia;
	}



	public void setOperacionPropia(String operacionPropia) {
		this.operacionPropia = operacionPropia;
	}



	
}
