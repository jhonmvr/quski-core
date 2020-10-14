package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcione;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class NegociacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<TbQoTasacion> joyas;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private List<TbQoExcepcione> excepciones;
	private TbQoCreditoNegociacion credito;
	private String excepcionBre;
	private Boolean respuesta;

	public NegociacionWrapper(Boolean respuesta) {
		super();
		this.respuesta = respuesta;
	}
	public NegociacionWrapper() {
	}
	public List<TbQoVariablesCrediticia> getVariables() {
		return variables;
	}
	public void setVariables(List<TbQoVariablesCrediticia> variables) {
		this.variables = variables;
	}
	public List<TbQoRiesgoAcumulado> getRiesgos() {
		return riesgos;
	}
	public void setRiesgos(List<TbQoRiesgoAcumulado> riesgos) {
		this.riesgos = riesgos;
	}
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public Boolean getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Boolean respuesta) {
		this.respuesta = respuesta;
	}
	public String getExcepcionBre() {
		return excepcionBre;
	}
	public void setExcepcionBre(String excepcionBre) {
		this.excepcionBre = excepcionBre;
	}
	public List<TbQoTasacion> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbQoTasacion> joyas) {
		this.joyas = joyas;
	}
	public List<TbQoExcepcione> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<TbQoExcepcione> excepciones) {
		this.excepciones = excepciones;
	}
}
