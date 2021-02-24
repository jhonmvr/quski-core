package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoProceso;

public class ProcesoDevolucionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbQoDevolucion devolucion;
	private TbQoProceso proceso;
	
	
	public TbQoDevolucion getDevolucion() {
		return devolucion;
	}
	public void setDevolucion(TbQoDevolucion devolucion) {
		this.devolucion = devolucion;
	}
	public TbQoProceso getProceso() {
		return proceso;
	}
	public void setProceso(TbQoProceso proceso) {
		this.proceso = proceso;
	}

}
