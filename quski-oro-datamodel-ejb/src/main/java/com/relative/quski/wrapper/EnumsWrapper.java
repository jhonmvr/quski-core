package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class EnumsWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> procesos;
	private List<String> actividades;
	private List<String> secciones;
	
	public List<String> getProcesos() {
		return procesos;
	}
	public void setProcesos(List<String> procesos) {
		this.procesos = procesos;
	}
	public List<String> getActividades() {
		return actividades;
	}
	public void setActividades(List<String> actividades) {
		this.actividades = actividades;
	}
	public List<String> getSecciones() {
		return secciones;
	}
	public void setSecciones(List<String> secciones) {
		this.secciones = secciones;
	}
	

}
