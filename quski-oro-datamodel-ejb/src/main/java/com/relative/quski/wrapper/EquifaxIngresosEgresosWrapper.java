package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class EquifaxIngresosEgresosWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<EquifaxRubroWrapper> rubros;
	
	public List<EquifaxRubroWrapper> getRubros() {
		return rubros;
	}
	public void setRubros(List<EquifaxRubroWrapper> rubros) {
		this.rubros = rubros;
	}

}
