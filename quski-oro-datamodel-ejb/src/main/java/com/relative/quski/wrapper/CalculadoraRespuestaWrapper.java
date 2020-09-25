package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class CalculadoraRespuestaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CalculadoraOpcionWrapper> opciones;
	private List<CalculadoraOpcionExcepcionadaWrapper> opcionesExcepcionada;
	private List<CalculadoraGarantiaWrapper> garantias;
	private List<CalculadoraVariableWrapper> variables;
	private Integer codigoError;
	private String mensaje;
	private String numeroOperacionMadre;
	
	public static CalculadoraRespuestaWrapper generateMockupExcepcionada() {
		CalculadoraRespuestaWrapper tmp = new CalculadoraRespuestaWrapper();
		tmp.setOpcionesExcepcionada( CalculadoraOpcionExcepcionadaWrapper.generateMockupOpcion() );
		tmp.setCodigoError( 0 );
		tmp.setMensaje( "" );
		tmp.setNumeroOperacionMadre( "" );
		return tmp;
	}
	public static CalculadoraRespuestaWrapper generateMockupRiesgo() {
		CalculadoraRespuestaWrapper tmp = new CalculadoraRespuestaWrapper();
		tmp.setOpciones(	CalculadoraOpcionWrapper.generateMockup());
		tmp.setGarantias( 	CalculadoraGarantiaWrapper.generateMockup() );
		tmp.setVariables( 	CalculadoraVariableWrapper.generateMockup() );
		tmp.setCodigoError( 3 );
		tmp.setMensaje( "Requiere excepción riesgo por cliente" );
		tmp.setNumeroOperacionMadre( "" );
		return tmp;
	}
	public static CalculadoraRespuestaWrapper generateMockupEstandar() {
		CalculadoraRespuestaWrapper tmp = new CalculadoraRespuestaWrapper();
		tmp.setOpciones(	CalculadoraOpcionWrapper.generateMockup());
		tmp.setGarantias( 	CalculadoraGarantiaWrapper.generateMockup() );
		tmp.setVariables( 	CalculadoraVariableWrapper.generateMockup() );
		tmp.setCodigoError( 0 );
		tmp.setMensaje( "" );
		tmp.setNumeroOperacionMadre( "" );
		return tmp;
	}
	public List<CalculadoraOpcionWrapper> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<CalculadoraOpcionWrapper> opciones) {
		this.opciones = opciones;
	}
	public List<CalculadoraGarantiaWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<CalculadoraGarantiaWrapper> garantias) {
		this.garantias = garantias;
	}
	public List<CalculadoraOpcionExcepcionadaWrapper> getOpcionesExcepcionada() {
		return opcionesExcepcionada;
	}
	public void setOpcionesExcepcionada(List<CalculadoraOpcionExcepcionadaWrapper> opcionesExcepcionada) {
		this.opcionesExcepcionada = opcionesExcepcionada;
	}
	public List<CalculadoraVariableWrapper> getVariables() {
		return variables;
	}
	public void setVariables(List<CalculadoraVariableWrapper> variables) {
		this.variables = variables;
	}
	public Integer getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}
	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}
}
