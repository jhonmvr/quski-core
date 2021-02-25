package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroPagoRenovacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String intitucionFinanciera;
	private BigDecimal numeroDeposito;
	private BigDecimal valorDepositado;
	private Date fechaPago;
	private String cuenta;
	private ArchivoComprobanteWrapper comprobante;
	private String tipoPago;
	

	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public BigDecimal getNumeroDeposito() {
		return numeroDeposito;
	}
	public void setNumeroDeposito(BigDecimal numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}
	public String getIntitucionFinanciera() {
		return intitucionFinanciera;
	}
	public void setIntitucionFinanciera(String intitucionFinanciera) {
		this.intitucionFinanciera = intitucionFinanciera;
	}
	public BigDecimal getValorDepositado() {
		return valorDepositado;
	}
	public void setValorDepositado(BigDecimal valorDepositado) {
		this.valorDepositado = valorDepositado;
	}
	public ArchivoComprobanteWrapper getComprobante() {
		return comprobante;
	}
	public void setComprobante(ArchivoComprobanteWrapper comprobante) {
		this.comprobante = comprobante;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
}
