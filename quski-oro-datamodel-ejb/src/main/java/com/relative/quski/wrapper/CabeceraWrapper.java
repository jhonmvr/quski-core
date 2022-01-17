package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class CabeceraWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2639635178977088451L;
	
	private String nombre;
	private String cedula;
	private String numeroCredito;
	private String codigoBPM;
	private String monto;
	private String plazo;
	private String tipoCredito;
	private String numeroCuenta;
	private String nombreAsesor;
	private String numeroCreditoAnterior;
	
	
	
	public CabeceraWrapper(String nombre, String cedula, String numeroCredito, String codigoBPM, String monto,
			String plazo, String tipoCredito, String numeroCuenta, String nombreAsesor) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.numeroCredito = numeroCredito;
		this.codigoBPM = codigoBPM;
		this.monto = monto;
		this.plazo = plazo;
		this.tipoCredito = tipoCredito;
		this.numeroCuenta = numeroCuenta;
		this.nombreAsesor = nombreAsesor;
	}
	
	public CabeceraWrapper(String nombre, String cedula, String numeroCredito, String codigoBPM, BigDecimal monto,
			BigDecimal plazo, String tipoCredito, String numeroCuenta, String nombreAsesor) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.numeroCredito = numeroCredito;
		this.codigoBPM = codigoBPM;
		this.monto = String.valueOf(monto);
		this.plazo = String.valueOf(plazo);
		this.tipoCredito = tipoCredito;
		this.numeroCuenta = numeroCuenta;
		this.nombreAsesor = nombreAsesor;
	}
	public CabeceraWrapper(String nombre, String cedula, String numeroCredito, String codigoBPM, BigDecimal monto,
			BigDecimal plazo, String tipoCredito, String numeroCuenta, String nombreAsesor, String numeroCreditoAnterior) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.numeroCredito = numeroCredito;
		this.codigoBPM = codigoBPM;
		this.monto = String.valueOf(monto);
		this.plazo = String.valueOf(plazo);
		this.tipoCredito = tipoCredito;
		this.numeroCuenta = numeroCuenta;
		this.nombreAsesor = nombreAsesor;
		this.numeroCreditoAnterior  = numeroCreditoAnterior;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNumeroCredito() {
		return numeroCredito;
	}
	public void setNumeroCredito(String numeroCredito) {
		this.numeroCredito = numeroCredito;
	}
	public String getCodigoBPM() {
		return codigoBPM;
	}
	public void setCodigoBPM(String codigoBPM) {
		this.codigoBPM = codigoBPM;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getNombreAsesor() {
		return nombreAsesor;
	}
	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}

	public String getNumeroCreditoAnterior() {
		return numeroCreditoAnterior;
	}

	public void setNumeroCreditoAnterior(String numeroCreditoAnterior) {
		this.numeroCreditoAnterior = numeroCreditoAnterior;
	}

	
	
}
