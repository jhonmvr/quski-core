package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;


public class ResultOperacionesAprobarWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long   result;
    private List<OpPorAprobarWrapper> operaciones;
    
	public Long getResult() {
		return result;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	public List<OpPorAprobarWrapper> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<OpPorAprobarWrapper> operaciones) {
		this.operaciones = operaciones;
	}

    

}
