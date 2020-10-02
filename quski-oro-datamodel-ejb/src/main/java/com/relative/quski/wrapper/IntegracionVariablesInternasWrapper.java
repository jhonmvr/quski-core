package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class IntegracionVariablesInternasWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<IntegracionVariableWrapper> variable;

	public List<IntegracionVariableWrapper> getVariables() {
		return variable;
	}

	public void setVariables(List<IntegracionVariableWrapper> variables) {
		this.variable = variables;
	} 
	

}
