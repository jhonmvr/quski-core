package com.relative.quski.wrapper;

import java.io.Serializable;

public class FileLocalStorage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552178687524913163L;
	
	private String nombreArchivo;
	private String archivo;
	private String proceso;
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	
	
	

}
