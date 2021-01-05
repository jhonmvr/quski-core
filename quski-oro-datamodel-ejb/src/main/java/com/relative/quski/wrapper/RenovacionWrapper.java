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

	private DetalleCreditoWrapper operacionAnterior;
	
	private TbQoCreditoNegociacion credito;
	private List<TbQoTasacion> tasacion;
	private TbQoProceso proceso;
	private List<TbQoExcepcion> excepciones;
	
	public RenovacionWrapper(DetalleCreditoWrapper operacionAnterior) {
		super();
		this.operacionAnterior = operacionAnterior;
	}
	
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

	public List<TbQoTasacion> getTasacion() {
		return tasacion;
	}

	public void setTasacion(List<TbQoTasacion> tasacion) {
		this.tasacion = tasacion;
	}

	public DetalleCreditoWrapper getOperacionAnterior() {
		return operacionAnterior;
	}

	public void setOperacionAnterior(DetalleCreditoWrapper operacionAnterior) {
		this.operacionAnterior = operacionAnterior;
	}
}
