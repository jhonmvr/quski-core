package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlOpcionesRenovacion.OpcionesRenovacion.Opcion;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlGarantias.Garantias.Garantia;

public class OpcionAndGarantiasWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Opcion opcion;
	private List<Garantia> garantias;
	private List<TbQoVariablesCrediticia> variablesInternas;
    private String nombreApoderado;
    private String identificacionApoderado;
    private Date fechaNacimientoApoderado;
    private Date fechaNacimientoCodeudor;
    private String tipoCliente;
    private String nombreCodeudor;
    private String identificacionCodeudor;
	 
	public String getNombreApoderado() {
		return nombreApoderado;
	}
	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}
	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}
	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}
	public Date getFechaNacimientoApoderado() {
		return fechaNacimientoApoderado;
	}
	public void setFechaNacimientoApoderado(Date fechaNacimientoApoderado) {
		this.fechaNacimientoApoderado = fechaNacimientoApoderado;
	}
	public Date getFechaNacimientoCodeudor() {
		return fechaNacimientoCodeudor;
	}
	public void setFechaNacimientoCodeudor(Date fechaNacimientoCodeudor) {
		this.fechaNacimientoCodeudor = fechaNacimientoCodeudor;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getNombreCodeudor() {
		return nombreCodeudor;
	}
	public void setNombreCodeudor(String nombreCodeudor) {
		this.nombreCodeudor = nombreCodeudor;
	}
	public String getIdentificacionCodeudor() {
		return identificacionCodeudor;
	}
	public void setIdentificacionCodeudor(String identificacionCodeudor) {
		this.identificacionCodeudor = identificacionCodeudor;
	}
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
	public List<TbQoVariablesCrediticia> getVariablesInternas() {
		return variablesInternas;
	}
	public void setVariablesInternas(List<TbQoVariablesCrediticia> variablesInternas) {
		this.variablesInternas = variablesInternas;
	}
	

}
