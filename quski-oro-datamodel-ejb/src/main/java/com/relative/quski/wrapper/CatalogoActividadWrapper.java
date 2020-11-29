package com.relative.quski.wrapper;

import java.io.Serializable;

public class CatalogoActividadWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763330066421548666L;
	
	private Long id;
    private String nombre;
    private Long idPadre;
    private Boolean esPorDefecto;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
	public Boolean getEsPorDefecto() {
		return esPorDefecto;
	}
	public void setEsPorDefecto(Boolean esPorDefecto) {
		this.esPorDefecto = esPorDefecto;
	}
    
    
    
	

}
