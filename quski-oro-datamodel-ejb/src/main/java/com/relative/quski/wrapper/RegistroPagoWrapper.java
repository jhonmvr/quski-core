package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroPagoWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3205114036416526081L;
	
	private String institucionFinanciera;
	private String cuentas;
	private Date fechaPago;
	private BigDecimal numeroDeposito;
	private BigDecimal valorPagado;
	private byte[] idComprobante;
	private String fileBase64;
	private String nombreArchivo;
	private String archivo;
	
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	private String estado;
	
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	public byte[] getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(byte[] idComprobante) {
		this.idComprobante = idComprobante;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
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
	public void setNumeroDeposito(BigDecimal numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}
	public BigDecimal getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	
	

}
