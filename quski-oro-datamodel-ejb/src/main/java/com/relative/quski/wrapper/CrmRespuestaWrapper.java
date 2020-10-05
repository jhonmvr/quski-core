package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class CrmRespuestaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	public CrmProspectoCortoWrapper getEntidad() {
		return entidad;
	}
	public void setEntidad(CrmProspectoCortoWrapper entidad) {
		this.entidad = entidad;
	}
	public List<CrmProspectoCortoWrapper> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<CrmProspectoCortoWrapper> entidades) {
		this.entidades = entidades;
	}
	private CrmProspectoCortoWrapper entidad;
	private List<CrmProspectoCortoWrapper> entidades;


}
