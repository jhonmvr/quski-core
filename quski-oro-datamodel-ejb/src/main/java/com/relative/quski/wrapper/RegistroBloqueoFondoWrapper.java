package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroBloqueoFondoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3205114036416526081L;
	
	private String cuentas;
	private Date fechaPago;
	private String institucionFinanciera;
	private BigDecimal numeroDeposito;
	private BigDecimal valorPagado;
	private String nombreArchivo;
	private String archivo;
	
	
	public String getCuentas() {
		return cuentas;
	}
	public void setCuentas(String cuentas) {
		this.cuentas = cuentas;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechadePago(Date fechadePago) {
		this.fechaPago = fechadePago;
	}
	public String getInstitucionFinanciera() {
		return institucionFinanciera;
	}
	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}
	public BigDecimal getNumeroDeposito() {
		return numeroDeposito;
	}
	public void setNumerodeDeposito(BigDecimal numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}
	public BigDecimal getValorPagado() {
		return valorPagado;
	}
	public void setValorpagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
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
