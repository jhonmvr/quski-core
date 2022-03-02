package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class DevolucionReporteWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2681801495603772954L;
	
	private String codigoOperacion;
	private String codigoBpm;
	private String nombreCliente;
	private String cedulaCliente;
	private String estadoProceso;
	private String proceso;
	private BigDecimal idAgencia;
	private BigDecimal igAgenciaEntrega;
	private String fechaCreacion;
	private String fechaArribo;
	private String fechaEngrega;
	
	
	// (java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, java.sql.String, java.sql.String, java.sql.String)
	public DevolucionReporteWrapper(String codigoOperacion, String codigoBpm, String nombreCliente,
			String cedulaCliente, String estadoProceso, String proceso, BigDecimal idAgencia, BigDecimal igAgenciaEntrega,
			String fechaCreacion, String fechaArribo, String fechaEngrega) {
		super();
		this.codigoOperacion = codigoOperacion;
		this.codigoBpm = codigoBpm;
		this.nombreCliente = nombreCliente;
		this.cedulaCliente = cedulaCliente;
		this.estadoProceso = estadoProceso;
		this.proceso = proceso;
		this.idAgencia = idAgencia;
		this.igAgenciaEntrega = igAgenciaEntrega;
		this.fechaCreacion = fechaCreacion;
		this.fechaArribo = fechaArribo;
		this.fechaEngrega = fechaEngrega;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getCodigoBpm() {
		return codigoBpm;
	}
	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public BigDecimal getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(BigDecimal idAgencia) {
		this.idAgencia = idAgencia;
	}
	public BigDecimal getIgAgenciaEntrega() {
		return igAgenciaEntrega;
	}
	public void setIgAgenciaEntrega(BigDecimal igAgenciaEntrega) {
		this.igAgenciaEntrega = igAgenciaEntrega;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaArribo() {
		return fechaArribo;
	}
	public void setFechaArribo(String fechaArribo) {
		this.fechaArribo = fechaArribo;
	}
	public String getFechaEngrega() {
		return fechaEngrega;
	}
	public void setFechaEngrega(String fechaEngrega) {
		this.fechaEngrega = fechaEngrega;
	}
	
	

}
