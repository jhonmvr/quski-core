package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntegracionIngresosEgresosWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IntegracionRubroWrapper> rubro;

	public List<IntegracionRubroWrapper> getRubros() {
		return rubro;
	}
	public void setRubros(List<IntegracionRubroWrapper> rubro) {
		this.rubro = rubro;
	} 

}
