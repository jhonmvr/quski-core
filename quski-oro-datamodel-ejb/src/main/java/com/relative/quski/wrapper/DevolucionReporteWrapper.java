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
	private String fundaMadre;
	private String fundaActual;
	private String asesor;
	private String fechaAprobacion;
	private String fechaAnulacion;
	private BigDecimal id;
	

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
	
	public DevolucionReporteWrapper(String codigoOperacion, String codigoBpm, String nombreCliente,
			String cedulaCliente, String estadoProceso, String proceso, BigDecimal idAgencia, BigDecimal igAgenciaEntrega,
			String fechaCreacion, String fechaArribo, String fechaEngrega, String fundaActual) {
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
		this.fundaActual = fundaActual;
	}
	
	
	//(java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	public DevolucionReporteWrapper(BigDecimal id, String codigoOperacion, String codigoBpm, String nombreCliente,
			String cedulaCliente, String estadoProceso, String proceso, BigDecimal idAgencia,
			BigDecimal igAgenciaEntrega, String fechaCreacion, String fechaArribo, String fechaEngrega,
			String fundaMadre, String fundaActual, String asesor, String fechaAprobacion, String fechaAnulacion) {
		super();
		this.id = id;
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
		this.fundaMadre = fundaMadre;
		this.fundaActual = fundaActual;
		this.asesor = asesor;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaAnulacion = fechaAnulacion;
	}



	public String getFundaMadre() {
		return fundaMadre;
	}
	public void setFundaMadre(String fundaMadre) {
		this.fundaMadre = fundaMadre;
	}
	public String getFundaActual() {
		return fundaActual;
	}
	public void setFundaActual(String fundaActual) {
		this.fundaActual = fundaActual;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public String getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public String getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
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


	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	

}
