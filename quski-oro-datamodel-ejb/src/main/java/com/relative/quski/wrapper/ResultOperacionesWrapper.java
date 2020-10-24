package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class ResultOperacionesWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long   result;
    private List<OperacionesWrapper> operaciones;
    
	public Long getResult() {
		return result;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	public List<OperacionesWrapper> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<OperacionesWrapper> operaciones) {
		this.operaciones = operaciones;
	}

    

}
