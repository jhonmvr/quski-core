package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.relative.quski.enums.EstadoProcesoEnum;

public class DevolucionParamsWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9130666936479613446L;
	

	private String  codigoOperacion;
	private String  codigoBpm;
	private String  nombreCliente;
	private String  identificacionCliente;
	private String  estado;
	private BigDecimal  agenciaEntrega;
	private BigDecimal  agenciaRecepcion;
	private Date  fechaCreacionDesde;
	private Date  fechaCreacionHasta;
	private Date  fechaArriboDesde;
	private Date  fechaArriboHasta;
	private Date  fechaEntregaDesde;
	private Date  fechaEntregaHasta;
	private List<EstadoProcesoEnum> estados; 
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
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getAgenciaEntrega() {
		return agenciaEntrega;
	}
	public void setAgenciaEntrega(BigDecimal agenciaEntrega) {
		this.agenciaEntrega = agenciaEntrega;
	}
	public BigDecimal getAgenciaRecepcion() {
		return agenciaRecepcion;
	}
	public void setAgenciaRecepcion(BigDecimal agenciaRecepcion) {
		this.agenciaRecepcion = agenciaRecepcion;
	}
	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}
	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}
	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}
	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}
	public Date getFechaArriboDesde() {
		return fechaArriboDesde;
	}
	public void setFechaArriboDesde(Date fechaArriboDesde) {
		this.fechaArriboDesde = fechaArriboDesde;
	}
	public Date getFechaArriboHasta() {
		return fechaArriboHasta;
	}
	public void setFechaArriboHasta(Date fechaArriboHasta) {
		this.fechaArriboHasta = fechaArriboHasta;
	}
	public Date getFechaEntregaDesde() {
		return fechaEntregaDesde;
	}
	public void setFechaEntregaDesde(Date fechaEntregaDesde) {
		this.fechaEntregaDesde = fechaEntregaDesde;
	}
	public Date getFechaEntregaHasta() {
		return fechaEntregaHasta;
	}
	public void setFechaEntregaHasta(Date fechaEntregaHasta) {
		this.fechaEntregaHasta = fechaEntregaHasta;
	}
	public List<EstadoProcesoEnum> getEstados() {
		return estados;
	}
	public void setEstados(List<EstadoProcesoEnum> estados) {
		this.estados = estados;
	}
	
	

}
