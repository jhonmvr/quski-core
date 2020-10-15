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
	private String Archivo;
	public String getArchivo() {
		return Archivo;
	}
	public void setArchivo(String archivo) {
		Archivo = archivo;
	}
	private byte[] idComprobante;
	private String fileBase64;
	private String estado;
	
	public byte[] getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(byte[] idComprobante) {
		this.idComprobante = idComprobante;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public void setNumeroDeposito(BigDecimal numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}
	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}
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
	

}
