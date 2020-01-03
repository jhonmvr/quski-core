package com.relative.quski.wrapper;

import java.io.Serializable;

public class ProcessInputWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1748289748013850719L;
	
	
	
	private Integer idSiniestro;
	private String numerTramite;
	private String estadoSiniestro;
	private String reservaNumeroICORE;
	private String estadoPoliza;
	
	
	
	public ProcessInputWrapper(Integer idSiniestro, String numerTramite, String estadoSiniestro, String reservaNumeroICORE, String estadoPoliza) {
		super();
		this.idSiniestro = idSiniestro;
		this.numerTramite = numerTramite;
		this.estadoSiniestro = estadoSiniestro;
		this.reservaNumeroICORE=reservaNumeroICORE;
		this.setEstadoPoliza(estadoPoliza);
	}
	
	
	public Integer getIdSiniestro() {
		return idSiniestro;
	}
	public void setIdSiniestro(Integer idSiniestro) {
		this.idSiniestro = idSiniestro;
	}
	public String getNumerTramite() {
		return numerTramite;
	}
	public void setNumerTramite(String numerTramite) {
		this.numerTramite = numerTramite;
	}
	public String getEstadoSiniestro() {
		return estadoSiniestro;
	}
	public void setEstadoSiniestro(String estadoSiniestro) {
		this.estadoSiniestro = estadoSiniestro;
	}


	public String getReservaNumeroICORE() {
		return reservaNumeroICORE;
	}


	public void setReservaNumeroICORE(String reservaNumeroICORE) {
		this.reservaNumeroICORE = reservaNumeroICORE;
	}


	public String getEstadoPoliza() {
		return estadoPoliza;
	}


	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}
	
	
}
