package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntegracionIngresosEgresosWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("rubro")
	private List<IntegracionRubroWrapper> rubros;
	
	public List<IntegracionRubroWrapper> getRubros() {
		return rubros;
	}
	public void setRubros(List<IntegracionRubroWrapper> rubros) {
		this.rubros = rubros;
	} 

}
