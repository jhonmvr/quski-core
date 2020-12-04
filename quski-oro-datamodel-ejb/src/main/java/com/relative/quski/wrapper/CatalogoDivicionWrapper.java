package com.relative.quski.wrapper;

import java.io.Serializable;

public class CatalogoDivicionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763330066421548666L;
	
	private Long id;
    private String codigo;
    private String nombre;
    private String tipoDivision;
    private Long idPadre;
    
    
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipoDivision() {
		return tipoDivision;
	}
	public void setTipoDivision(String tipoDivision) {
		this.tipoDivision = tipoDivision;
	}
    
    
    
	

}
