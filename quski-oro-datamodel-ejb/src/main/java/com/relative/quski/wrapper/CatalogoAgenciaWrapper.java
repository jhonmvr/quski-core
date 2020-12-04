package com.relative.quski.wrapper;

import java.io.Serializable;

public class CatalogoAgenciaWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5763330066421548666L;
	
	private Long id;
    private String codigo;
    private String nombre;
    private String direccion;
    private Long idResidencia;
    private Long idUbicacionTevcol;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getIdResidencia() {
		return idResidencia;
	}
	public void setIdResidencia(Long idResidencia) {
		this.idResidencia = idResidencia;
	}
	public Long getIdUbicacionTevcol() {
		return idUbicacionTevcol;
	}
	public void setIdUbicacionTevcol(Long idUbicacionTevcol) {
		this.idUbicacionTevcol = idUbicacionTevcol;
	}
	
	

}
