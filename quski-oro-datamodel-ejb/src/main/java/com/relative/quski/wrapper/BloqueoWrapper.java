package com.relative.quski.wrapper;

import java.io.Serializable;

public class BloqueoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3036574059489918269L;

	private Long idTipoIdentificacion;
	private String identificacion;
	private String codigoMotivoBloqueo;
	private String numeroOperacion;
	private String referenciaBpm;
	private Boolean esBloqueo;
	private String codigoUsuario;
	
	
	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
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
	public String getCodigoMotivoBloqueo() {
		return codigoMotivoBloqueo;
	}
	public void setCodigoMotivoBloqueo(String codigoMotivoBloqueo) {
		this.codigoMotivoBloqueo = codigoMotivoBloqueo;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getReferenciaBpm() {
		return referenciaBpm;
	}
	public void setReferenciaBpm(String referenciaBpm) {
		this.referenciaBpm = referenciaBpm;
	}
	public Boolean getEsBloqueo() {
		return esBloqueo;
	}
	public void setEsBloqueo(Boolean esBloqueo) {
		this.esBloqueo = esBloqueo;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	
}
