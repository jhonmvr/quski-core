package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCompromisoPago;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class CreditoCompromisoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SoftbankClienteWrapper cliente;
	private SoftbankConsultaPagMedWrapper pagMed;
	private OperacionWrapper credito;
	private List<GarantiaOperacionWrapper> garantias;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private List<TbQoProceso> procesos;
	private List<TbQoCompromisoPago> compromisos;

	
	
	
	public CreditoCompromisoWrapper() {
		super();
	}

	
	
	public CreditoCompromisoWrapper(SoftbankClienteWrapper cliente, SoftbankConsultaPagMedWrapper pagMed,
			OperacionWrapper credito, List<GarantiaOperacionWrapper> garantias, List<TbQoVariablesCrediticia> variables,
			List<TbQoRiesgoAcumulado> riesgos, List<TbQoProceso> procesos, List<TbQoCompromisoPago> compromisos) {
		super();
		this.cliente = cliente;
		this.pagMed = pagMed;
		this.credito = credito;
		this.garantias = garantias;
		this.variables = variables;
		this.riesgos = riesgos;
		this.procesos = procesos;
		this.compromisos = compromisos;
	}




	public List<TbQoProceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<TbQoProceso> procesos) {
		this.procesos = procesos;
	}

	public SoftbankConsultaPagMedWrapper getPagMed() {
		return pagMed;
	}
	public void setPagMed(SoftbankConsultaPagMedWrapper pagMed) {
		this.pagMed = pagMed;
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
	public SoftbankClienteWrapper getCliente() {
		return cliente;
	}
	public void setCliente(SoftbankClienteWrapper cliente) {
		this.cliente = cliente;
	}
	public OperacionWrapper getCredito() {
		return credito;
	}
	public void setCredito(OperacionWrapper credito) {
		this.credito = credito;
	}
	public List<GarantiaOperacionWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<GarantiaOperacionWrapper> garantias) {
		this.garantias = garantias;
	}
	public List<TbQoCompromisoPago> getCompromisos() {
		return compromisos;
	}
	public void setCompromisos(List<TbQoCompromisoPago> compromisos) {
		this.compromisos = compromisos;
	}




}
