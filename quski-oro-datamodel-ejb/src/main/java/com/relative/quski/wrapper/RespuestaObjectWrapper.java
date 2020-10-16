package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.enums.EstadoEnum;

public class RespuestaObjectWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552178687524913163L;

	private String token;
	private String entidad;
	private String entidades;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getEntidades() {
		return entidades;
	}
	public void setEntidades(String entidades) {
		this.entidades = entidades;
	}
}