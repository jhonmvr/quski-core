package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroPagoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3205114036416526081L;
	
	private String cuentas;
	private Date fechadePago;
	private String institucionFinanciera;
	private BigDecimal numerodeDeposito;
	private BigDecimal valorpagado;
	private String nombreArchivo;
	private String archivo;
	public String getCuentas() {
		return cuentas;
	}
	public void setCuentas(String cuentas) {
		this.cuentas = cuentas;
	}
	public Date getFechadePago() {
		return fechadePago;
	}
	public void setFechadePago(Date fechadePago) {
		this.fechadePago = fechadePago;
	}
	public String getInstitucionFinanciera() {
		return institucionFinanciera;
	}
	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}
	public BigDecimal getNumerodeDeposito() {
		return numerodeDeposito;
	}
	public void setNumerodeDeposito(BigDecimal numerodeDeposito) {
		this.numerodeDeposito = numerodeDeposito;
	}
	public BigDecimal getValorpagado() {
		return valorpagado;
	}
	public void setValorpagado(BigDecimal valorpagado) {
		this.valorpagado = valorpagado;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	

}
