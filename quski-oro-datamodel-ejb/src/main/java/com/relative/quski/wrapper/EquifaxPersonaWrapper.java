package com.relative.quski.wrapper;

import java.io.Serializable;

public class EquifaxPersonaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public EquifaxPersonaWrapper( ) {
	
	}
	private String identificacion;
	private EquifaxDatosClienteWrapper datoscliente;
	private EquifaxVariablesInternasWrapper variablesInternas;
	private EquifaxIngresosEgresosWrapper ingresosEgresos;

	private String codigoError;
	private String mensaje;
	private String ejecucion;
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public EquifaxDatosClienteWrapper getDatoscliente() {
		return datoscliente;
	}
	public void setDatoscliente(EquifaxDatosClienteWrapper datoscliente) {
		this.datoscliente = datoscliente;
	}
	public EquifaxVariablesInternasWrapper getVariablesInternas() {
		return variablesInternas;
	}
	public void setVariablesInternas(EquifaxVariablesInternasWrapper variablesInternas) {
		this.variablesInternas = variablesInternas;
	}
	public EquifaxIngresosEgresosWrapper getIngresosEgresos() {
		return ingresosEgresos;
	}
	public void setIngresosEgresos(EquifaxIngresosEgresosWrapper ingresosEgresos) {
		this.ingresosEgresos = ingresosEgresos;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(String ejecucion) {
		this.ejecucion = ejecucion;
	}

}
