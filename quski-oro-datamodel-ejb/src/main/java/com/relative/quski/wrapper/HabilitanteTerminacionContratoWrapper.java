package com.relative.quski.wrapper;

import java.io.Serializable;

public class HabilitanteTerminacionContratoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HabilitanteTerminacionContratoWrapper() {
		
	}
	
	private String fechaActual;
	private String nombreCompletoCliente;
	private String apoderadoMutualista;
	private String fechaElaboracionContrato;
	private String nombreUsuario;
	private String rolUsuario;
	private String cedulaCliente;
	
	public String getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}
	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}
	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}
	public String getApoderadoMutualista() {
		return apoderadoMutualista;
	}
	public void setApoderadoMutualista(String apoderadoMutualista) {
		this.apoderadoMutualista = apoderadoMutualista;
	}
	public String getFechaElaboracionContrato() {
		return fechaElaboracionContrato;
	}
	public void setFechaElaboracionContrato(String fechaElaboracionContrato) {
		this.fechaElaboracionContrato = fechaElaboracionContrato;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getRolUsuario() {
		return rolUsuario;
	}
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	
	
}
