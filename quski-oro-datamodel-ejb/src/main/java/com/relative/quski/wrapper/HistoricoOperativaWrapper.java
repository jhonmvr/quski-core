package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

public class HistoricoOperativaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8886027853557259272L;
	
	private Date fechaRegularizacion;
	private String excepcion;
	private String usuario;
	private Long id;
	
	
	
	public HistoricoOperativaWrapper(Long id, Date fechaRegularizacion, String excepcion, String usuario) {
		super();
		this.fechaRegularizacion = fechaRegularizacion;
		this.excepcion = excepcion;
		this.usuario = usuario;
		this.id = id;
	}
	public Date getFechaRegularizacion() {
		return fechaRegularizacion;
	}
	public void setFechaRegularizacion(Date fechaRegularizacion) {
		this.fechaRegularizacion = fechaRegularizacion;
	}
	public String getExcepcion() {
		return excepcion;
	}
	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
