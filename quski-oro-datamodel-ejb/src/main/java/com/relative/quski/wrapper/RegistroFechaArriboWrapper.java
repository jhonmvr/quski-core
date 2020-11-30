/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;



public class RegistroFechaArriboWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Long> idDevoluciones;
	private String fechaArribo;
	
	

	public RegistroFechaArriboWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Long> getIdDevoluciones() {
		return idDevoluciones;
	}


	public void setIdDevoluciones(List<Long> idDevoluciones) {
		this.idDevoluciones = idDevoluciones;
	}


	public String getFechaArribo() {
		return fechaArribo;
	}


	public void setFechaArribo(String fechaArribo) {
		this.fechaArribo = fechaArribo;
	}
	



}
