package com.relative.quski.wrapper;

import java.io.Serializable;

public class IntegracionEntidadWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long identificacion;
	private IntegracionDatosClienteWrapper datoscliente;
    private IntegracionIngresosEgresosWrapper ingresosegresos;
    private IntegracionXmlVariablesInternasWrapper xmlVariablesInternas;
    private Long codigoError;
    private String mensaje;
    private Long ejecucion;
    
    
	public Long getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}
	public IntegracionDatosClienteWrapper getDatoscliente() {
		return datoscliente;
	}
	public void setDatoscliente(IntegracionDatosClienteWrapper datoscliente) {
		this.datoscliente = datoscliente;
	}
	public IntegracionIngresosEgresosWrapper getIngresosegresos() {
		return ingresosegresos;
	}
	public void setIngresosegresos(IntegracionIngresosEgresosWrapper ingresosegresos) {
		this.ingresosegresos = ingresosegresos;
	}
	public IntegracionXmlVariablesInternasWrapper getXmlVariablesInternas() {
		return xmlVariablesInternas;
	}
	public void setXmlVariablesInternas(IntegracionXmlVariablesInternasWrapper xmlVariablesInternas) {
		this.xmlVariablesInternas = xmlVariablesInternas;
	}
	public Long getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(Long codigoError) {
		this.codigoError = codigoError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Long getEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(Long ejecucion) {
		this.ejecucion = ejecucion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
