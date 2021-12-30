package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InicioProcesoBloqueoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RegistroPagoRenovacionWrapper> pagos;
	private String asesor; 
	private Long agencia; 
	private String cedula;  
	private String mailAsesor;  
	private String nombreCompleto; 
	private String observacion;
	private BigDecimal valorDepositado;
	private Long idBanco;
	private BigDecimal montoCredito;
	private String plazoCredito;
	private String numeroCuentaCliente;
	private String nombreAsesor;
	
	
	public BigDecimal getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}
	public String getPlazoCredito() {
		return plazoCredito;
	}
	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}
	public String getNumeroCuentaCliente() {
		return numeroCuentaCliente;
	}
	public void setNumeroCuentaCliente(String numeroCuentaCliente) {
		this.numeroCuentaCliente = numeroCuentaCliente;
	}
	public String getNombreAsesor() {
		return nombreAsesor;
	}
	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}
	public List<RegistroPagoRenovacionWrapper> getPagos() {
		return pagos;
	}
	public void setPagos(List<RegistroPagoRenovacionWrapper> pagos) {
		this.pagos = pagos;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public Long getAgencia() {
		return agencia;
	}
	public void setAgencia(Long agencia) {
		this.agencia = agencia;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public BigDecimal getValorDepositado() {
		return valorDepositado;
	}
	public void setValorDepositado(BigDecimal valorDepositado) {
		this.valorDepositado = valorDepositado;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public String getMailAsesor() {
		return mailAsesor;
	}
	public void setMailAsesor(String mailAsesor) {
		this.mailAsesor = mailAsesor;
	}
	
}
