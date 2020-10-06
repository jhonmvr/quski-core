package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class CrmRespuestaPersistWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	public List<CrmProspectoWrapper> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<CrmProspectoWrapper> entidades) {
		this.entidades = entidades;
	}
	public CrmProspectoWrapper getEntidad() {
		return entidad;
	}

	public void setEntidad(CrmProspectoWrapper entidad) {
		this.entidad = entidad;
	}
	
	private List<CrmProspectoWrapper> entidades;
	private CrmProspectoWrapper entidad;


}
