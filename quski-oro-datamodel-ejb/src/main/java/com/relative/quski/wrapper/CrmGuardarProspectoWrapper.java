package com.relative.quski.wrapper;

import java.io.Serializable;

public class CrmGuardarProspectoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;


	private CrmEntidadWrapper entidad;

	public CrmEntidadWrapper getEntidad() {
		return entidad;
	}

	public CrmGuardarProspectoWrapper(CrmEntidadWrapper entidad) {
		super();
		this.entidad = entidad;
	}

	public void setEntidad(CrmEntidadWrapper entidad) {
		this.entidad = entidad;
	}

	
}
