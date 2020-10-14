package com.relative.quski.wrapper;

import java.io.Serializable;

import com.relative.quski.enums.TipoExcepcionEnum;

public class ExceptionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String apellido;
	private String cedula;
	private TipoExcepcionEnum tipoExcepcion;
	public ExceptionWrapper(Long id, TipoExcepcionEnum tipoExcepcion, String cedula, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.tipoExcepcion = tipoExcepcion;
	}
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public TipoExcepcionEnum getTipoExcepcion() {
		return tipoExcepcion;
	}
	public void setTipoExcepcion(TipoExcepcionEnum tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}
	
	
	
}
