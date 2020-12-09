package com.relative.quski.wrapper;

import java.io.Serializable;

public class SoftbankDireccionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2205281473180641618L;
	
	private Long id;
	private String codigoTipoDireccion; 
	private String codigoVivienda; 
	private String codigoSectorVivienda;
	private Long idUbicacion;
	private String callePrincipal; 
	private String calleSecundaria;
	private String numero; 
	private String barrio;
	private String referencia;
	private Boolean esDireccionLegal;
	private Boolean esDireccionEnvio;
	private Boolean activo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoTipoDireccion() {
		return codigoTipoDireccion;
	}
	public void setCodigoTipoDireccion(String codigoTipoDireccion) {
		this.codigoTipoDireccion = codigoTipoDireccion;
	}
	public String getCodigoVivienda() {
		return codigoVivienda;
	}
	public void setCodigoVivienda(String codigoVivienda) {
		this.codigoVivienda = codigoVivienda;
	}
	public String getCodigoSectorVivienda() {
		return codigoSectorVivienda;
	}
	public void setCodigoSectorVivienda(String codigoSectorVivienda) {
		this.codigoSectorVivienda = codigoSectorVivienda;
	}
	public Long getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public String getCallePrincipal() {
		return callePrincipal;
	}
	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}
	public String getCalleSecundaria() {
		return calleSecundaria;
	}
	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Boolean getEsDireccionLegal() {
		return esDireccionLegal;
	}
	public void setEsDireccionLegal(Boolean esDireccionLegal) {
		this.esDireccionLegal = esDireccionLegal;
	}
	public Boolean getEsDireccionEnvio() {
		return esDireccionEnvio;
	}
	public void setEsDireccionEnvio(Boolean esDireccionEnvio) {
		this.esDireccionEnvio = esDireccionEnvio;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
}
