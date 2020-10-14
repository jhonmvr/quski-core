package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class IntegracionRespuestaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private String token;
	private IntegracionEntidadWrapper entidad;
	private List<IntegracionEntidadWrapper> entidades;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public IntegracionEntidadWrapper getEntidad() {
		return entidad;
	}
	public void setEntidad(IntegracionEntidadWrapper entidad) {
		this.entidad = entidad;
	}
	public List<IntegracionEntidadWrapper> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<IntegracionEntidadWrapper> entidades) {
		this.entidades = entidades;
	}

	

}
