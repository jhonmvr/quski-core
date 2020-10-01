package com.relative.quski.wrapper;

import java.io.Serializable;

public class CrmConsultaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	public CrmConsultaWrapper(String cedula) {
		super();
		this.cedula = cedula;
	}

	private String cedula;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}
