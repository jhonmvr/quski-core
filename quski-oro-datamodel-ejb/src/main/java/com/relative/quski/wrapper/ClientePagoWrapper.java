package com.relative.quski.wrapper;

import java.io.Serializable;

public class ClientePagoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClientePagoWrapper() {
		
	}
    private String cedula;
    private String codigoOperacion;
    private String codigoOperacionMupi;
    private String nombreCliente;
    private String tipoCredito;
    private String observacion;
    
   	public String getObservacion() {
   		return observacion;
   	}
   	public void setObservacion(String observacion) {
   		this.observacion = observacion;
   	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getCodigoOperacionMupi() {
		return codigoOperacionMupi;
	}
	public void setCodigoOperacionMupi(String codigoOperacionMupi) {
		this.codigoOperacionMupi = codigoOperacionMupi;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
}