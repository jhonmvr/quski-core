package com.relative.quski.wrapper;

import java.io.Serializable;

public class GaranteWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idTipoIdentificacion;
	private String identificacion;
	private String codigoTipoCliente;
	private String nombre;
	
	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public GaranteWrapper(Long idTipoIdentificacion, String identificacion, String codigoTipoCliente, String nombre) {
		super();
		this.idTipoIdentificacion = idTipoIdentificacion;
		this.identificacion = identificacion;
		this.codigoTipoCliente = codigoTipoCliente;
		this.nombre = nombre;
	}
	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
