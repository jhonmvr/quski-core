package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoTasacion;

public class RenovacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TbQoCreditoNegociacion credito;
	public RenovacionWrapper(TbQoCreditoNegociacion credito, TbQoProceso proceso) {
		super();
		this.credito = credito;
		this.proceso = proceso;
	}
	private TbQoProceso proceso;
	private List<TbQoExcepcion> excepciones;
	
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public TbQoProceso getProceso() {
		return proceso;
	}
	public void setProceso(TbQoProceso proceso) {
		this.proceso = proceso;
	}
	public List<TbQoExcepcion> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<TbQoExcepcion> excepciones) {
		this.excepciones = excepciones;
	}
	public List<TbQoTasacion> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbQoTasacion> joyas) {
		this.joyas = joyas;
	}
	public Boolean getExisteError() {
		return existeError;
	}
	public void setExisteError(Boolean existeError) {
		this.existeError = existeError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	private List<TbQoTasacion> joyas;
	private Boolean existeError;
	private String mensaje;
}
