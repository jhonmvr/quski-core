package com.relative.quski.wrapper;

import java.io.Serializable;

public class CuentaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String identificacion;
	
	private String tipoPago;
	private String institucionFinanciera;
	private String tipoCuenta;
	private String numeroCuenta;
	private String firmaRegularizada;
	
	public CuentaWrapper(String identificacion) {
		super();
		this.identificacion = identificacion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getInstitucionFinanciera() {
		return institucionFinanciera;
	}
	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getFirmaRegularizada() {
		return firmaRegularizada;
	}
	public void setFirmaRegularizada(String firmaRegularizada) {
		this.firmaRegularizada = firmaRegularizada;
	}

}
