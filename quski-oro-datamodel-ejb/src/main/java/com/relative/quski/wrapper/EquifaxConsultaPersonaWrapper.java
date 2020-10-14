package com.relative.quski.wrapper;

import java.io.Serializable;

public class EquifaxConsultaPersonaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public EquifaxConsultaPersonaWrapper( String identificacion ) {
		this.identificacion = identificacion;
		this.calificacion = "N";
		this.tipoConsulta = "CC";
		this.tipoIdentificacion = "C";
		
	}
	private String tipoIdentificacion;
	private String identificacion;
	private String tipoConsulta;
	private String calificacion;
	
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

}
