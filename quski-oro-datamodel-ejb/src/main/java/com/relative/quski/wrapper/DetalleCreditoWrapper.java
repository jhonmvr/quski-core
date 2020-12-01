package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class DetalleCreditoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SoftbankClienteWrapper cliente;
	private OperacionWrapper credito;
	private List<RubroOperacionWrapper> rubros;
	private List<GarantiaOperacionWrapper> garantias;
	private String habilitantes;
	
	
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
	public List<RubroOperacionWrapper> getRubros() {
		return rubros;
	}
	public void setRubros(List<RubroOperacionWrapper> rubros) {
		this.rubros = rubros;
	}
	public List<GarantiaOperacionWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<GarantiaOperacionWrapper> garantias) {
		this.garantias = garantias;
	}
	public String getHabilitantes() {
		return habilitantes;
	}
	public void setHabilitantes(String habilitantes) {
		this.habilitantes = habilitantes;
	}

}
