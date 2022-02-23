package com.relative.quski.wrapper;

import java.io.Serializable;

public class EntregaGarantiasReporteWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private String codigoOperacion;
    private String codigoBPM;
    private String nombreCliente;
	private String cedulaCliente;
	private String estado;
	private String agenciaEntrega;
	private String agenciaRecepcion;	
	private String fechaCreacion;
    private String fechaArriboAgencia;
	private String fechaEntrega;
	
	
	
	
	
	public EntregaGarantiasReporteWrapper(String codigoOperacion, String codigoBPM, String nombreCliente,
			String cedulaCliente, String estado, String agenciaEntrega, String agenciaRecepcion, String fechaCreacion,
			String fechaArriboAgencia, String fechaEntrega) {
		super();
		this.codigoOperacion = codigoOperacion;
		this.codigoBPM = codigoBPM;
		this.nombreCliente = nombreCliente;
		this.cedulaCliente = cedulaCliente;
		this.estado = estado;
		this.agenciaEntrega = agenciaEntrega;
		this.agenciaRecepcion = agenciaRecepcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaArriboAgencia = fechaArriboAgencia;
		this.fechaEntrega = fechaEntrega;
	}
	



	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getCodigoBPM() {
		return codigoBPM;
	}
	public void setCodigoBPM(String codigoBPM) {
		this.codigoBPM = codigoBPM;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAgenciaEntrega() {
		return agenciaEntrega;
	}
	public void setAgenciaEntrega(String agenciaEntrega) {
		this.agenciaEntrega = agenciaEntrega;
	}
	public String getAgenciaRecepcion() {
		return agenciaRecepcion;
	}
	public void setAgenciaRecepcion(String agenciaRecepcion) {
		this.agenciaRecepcion = agenciaRecepcion;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaArriboAgencia() {
		return fechaArriboAgencia;
	}
	public void setFechaArriboAgencia(String fechaArriboAgencia) {
		this.fechaArriboAgencia = fechaArriboAgencia;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	

	
	
	
	
	
}
