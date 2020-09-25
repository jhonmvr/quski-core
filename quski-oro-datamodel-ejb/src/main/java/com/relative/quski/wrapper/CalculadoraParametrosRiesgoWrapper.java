package com.relative.quski.wrapper;

import java.io.Serializable;

public class CalculadoraParametrosRiesgoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

    private Integer perfilRiesgo;
    private String origenOperacion;
    private Double riesgoTotal;
    private String fechaNacimiento;
    private String perfilPreferencia;
    private String agenciaOriginacion;
    private String identificacionCliente;
    private String calificacionMupi;
    private Integer coberturaExcepcionada;
    
    public void generateMockup() {
    	this.setPerfilRiesgo( 4 );
    	this.setOrigenOperacion("N");
    	this.setRiesgoTotal( 15323.65 ); 
    	this.setFechaNacimiento( "28/07/1990" );
    	this.setPerfilPreferencia( "B" );
    	this.setAgenciaOriginacion( "014" );
    	this.setIdentificacionCliente( "1722668702" );
    	this.setCalificacionMupi( "S" );
    	this.setCoberturaExcepcionada( 80 );
    }
	public Integer getPerfilRiesgo() {
		return perfilRiesgo;
	}
	public void setPerfilRiesgo(Integer perfilRiesgo) {
		this.perfilRiesgo = perfilRiesgo;
	}
	public String getOrigenOperacion() {
		return origenOperacion;
	}
	public void setOrigenOperacion(String origenOperacion) {
		this.origenOperacion = origenOperacion;
	}
	public Double getRiesgoTotal() {
		return riesgoTotal;
	}
	public void setRiesgoTotal(Double riesgoTotal) {
		this.riesgoTotal = riesgoTotal;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPerfilPreferencia() {
		return perfilPreferencia;
	}
	public void setPerfilPreferencia(String perfilPreferencia) {
		this.perfilPreferencia = perfilPreferencia;
	}
	public String getAgenciaOriginacion() {
		return agenciaOriginacion;
	}
	public void setAgenciaOriginacion(String agenciaOriginacion) {
		this.agenciaOriginacion = agenciaOriginacion;
	}
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public String getCalificacionMupi() {
		return calificacionMupi;
	}
	public void setCalificacionMupi(String calificacionMupi) {
		this.calificacionMupi = calificacionMupi;
	}
	public Integer getCoberturaExcepcionada() {
		return coberturaExcepcionada;
	}
	public void setCoberturaExcepcionada(Integer coberturaExcepcionada) {
		this.coberturaExcepcionada = coberturaExcepcionada;
	}
}
