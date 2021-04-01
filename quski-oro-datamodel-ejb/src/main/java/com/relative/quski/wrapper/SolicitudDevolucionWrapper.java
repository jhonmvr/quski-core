package com.relative.quski.wrapper;

import java.io.Serializable;

public class SolicitudDevolucionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SolicitudDevolucionWrapper() {
		
	}
	
	private String fechaSolicitud;
	private String agenciaSolicitante;
	private String nombreCliente;
	private String cedulaCliente;
	private String numeroOperacion;
	private String numeroFunda;
	private String agenciaEntrega;
	
	
	private String asesor;
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getAgenciaSolicitante() {
		return agenciaSolicitante;
	}
	public void setAgenciaSolicitante(String agenciaSolicitante) {
		this.agenciaSolicitante = agenciaSolicitante;
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
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getNumeroFunda() {
		return numeroFunda;
	}
	public void setNumeroFunda(String numeroFunda) {
		this.numeroFunda = numeroFunda;
	}
	public String getAgenciaEntrega() {
		return agenciaEntrega;
	}
	public void setAgenciaEntrega(String agenciaEntrega) {
		this.agenciaEntrega = agenciaEntrega;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	
	
	
}
