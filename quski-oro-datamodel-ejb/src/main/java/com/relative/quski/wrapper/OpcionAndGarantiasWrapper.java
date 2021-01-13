package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlOpcionesRenovacion.OpcionesRenovacion.Opcion;
import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlGarantias.Garantias.Garantia;

public class OpcionAndGarantiasWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private Opcion opcion;
	 private List<Garantia> garantias;
	 
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	public List<Garantia> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<Garantia> garantias) {
		this.garantias = garantias;
	}

}
