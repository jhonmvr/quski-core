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
	
	


}
