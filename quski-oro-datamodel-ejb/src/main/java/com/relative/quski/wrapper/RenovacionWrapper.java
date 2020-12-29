package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class RenovacionWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DetalleCreditoWrapper detalle;
	
	private TbQoCreditoNegociacion credito;
	private List<TbQoTasacion> tasacion;
	private List<TbQoVariablesCrediticia> variablesCrediticias;
	private List<TbQoRiesgoAcumulado> riesgoAcumulado;
	private TbQoProceso proceso;
	private List<TbQoExcepcion> excepciones;
	
	public RenovacionWrapper(DetalleCreditoWrapper detalle) {
		super();
		this.detalle = detalle;
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

	public DetalleCreditoWrapper getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleCreditoWrapper detalle) {
		this.detalle = detalle;
	}

	public List<TbQoTasacion> getTasacion() {
		return tasacion;
	}

	public void setTasacion(List<TbQoTasacion> tasacion) {
		this.tasacion = tasacion;
	}

	public List<TbQoVariablesCrediticia> getVariablesCrediticias() {
		return variablesCrediticias;
	}

	public void setVariablesCrediticias(List<TbQoVariablesCrediticia> variablesCrediticias) {
		this.variablesCrediticias = variablesCrediticias;
	}

	public List<TbQoRiesgoAcumulado> getRiesgoAcumulado() {
		return riesgoAcumulado;
	}

	public void setRiesgoAcumulado(List<TbQoRiesgoAcumulado> riesgoAcumulado) {
		this.riesgoAcumulado = riesgoAcumulado;
	}
}
