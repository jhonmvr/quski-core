package com.relative.quski.wrapper;

import java.io.Serializable;

public class ActaEntregaRecepcionApoderadoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ActaEntregaRecepcionApoderadoWrapper() {
		
	}
	private String ciudad;
	private String fechaDevolucion;
	private String nombreCompletoCliente;
	private String numeroFunda;
	private String numeroOperacion;
	private String nombreApoderado;
	private String cedulaApoderado;
	private String cedulaCliente;
	private String nombreAsesor;
	
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}
	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}
	public String getNumeroFunda() {
		return numeroFunda;
	}
	public void setNumeroFunda(String numeroFunda) {
		this.numeroFunda = numeroFunda;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getNombreAsesor() {
		return nombreAsesor;
	}
	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}
	public String getNombreApoderado() {
		return nombreApoderado;
	}
	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}
	public String getCedulaApoderado() {
		return cedulaApoderado;
	}
	public void setCedulaApoderado(String cedulaApoderado) {
		this.cedulaApoderado = cedulaApoderado;
	}
	
}
