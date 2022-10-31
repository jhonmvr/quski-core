package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class WrapperArbol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Long idPadre;
	private String tipoDocumento;
	private List<UsuarioWrapper> usuarios;
	private List<PerfilesWrapper> perfiles;
	private List<WrapperArbol> hijos;
	
	
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
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public List<UsuarioWrapper> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioWrapper> usuarios) {
		this.usuarios = usuarios;
	}
	public List<PerfilesWrapper> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(List<PerfilesWrapper> perfiles) {
		this.perfiles = perfiles;
	}
	public List<WrapperArbol> getHijos() {
		return hijos;
	}
	public void setHijos(List<WrapperArbol> hijos) {
		this.hijos = hijos;
	}

}
