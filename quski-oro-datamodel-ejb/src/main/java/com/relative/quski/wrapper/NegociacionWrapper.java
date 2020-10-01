package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class NegociacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TbQoNegociacion negociacion;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private TbQoCreditoNegociacion credito;

	public TbQoNegociacion getNegociacion() {
		return negociacion;
	}
	public void setNegociacion(TbQoNegociacion negociacion) {
		this.negociacion = negociacion;
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
}
