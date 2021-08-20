package com.relative.quski.wrapper;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class HistoricoObservacionWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7615684971189099954L;
	
	private Long id;
	private String usuario;
	private Date fecha;
	private String observacion;
	private Long idCredito;
	// long, java.lang.String, java.util.Date, java.lang.String, long [select new com.relative.quski.wrapper.HistoricoObservacionWrapper(generatedAlias0.id, generatedAlias0.usuario, generatedAlias0.fechaCreacion, generatedAlias0.observacion, generatedAlias0.tbQoCreditoNegociacion.id) from com.relative.quski.model.TbQoHistoricoObservacion as generatedAlias0 where generatedAlias0.tbQoCreditoNegociacion.id=652L]

	public HistoricoObservacionWrapper(Long id, String usuario, Date fecha, String observacion, Long idCredito) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fecha = fecha;
		this.observacion = observacion;
		this.idCredito = idCredito;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Long getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}
	
	
	

}
