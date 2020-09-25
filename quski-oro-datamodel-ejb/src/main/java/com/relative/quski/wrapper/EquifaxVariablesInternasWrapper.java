package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class EquifaxVariablesInternasWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<EquifaxVariableWrapper> variables;

	public List<EquifaxVariableWrapper> getVariables() {
		return variables;
	}

	public void setVariables(List<EquifaxVariableWrapper> variables) {
		this.variables = variables;
	}


}
