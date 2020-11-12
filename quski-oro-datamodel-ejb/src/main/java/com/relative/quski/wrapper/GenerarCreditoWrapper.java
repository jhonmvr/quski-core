package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.model.TbQoCreditoNegociacion;

public class GenerarCreditoWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	 private  TbQoCreditoNegociacion credito;

	 private  String identificacionCodeudor;
	 private  String nombreCompletoCodeudor;
	 private  Date   fechaNacimientoCodeudor;
 
	 private  String identificacionApoderado;
	 private  String nombreCompletoApoderado;
	 private  Date   fechaNacimientoApoderado;
	 
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public String getIdentificacionCodeudor() {
		return identificacionCodeudor;
	}
	public void setIdentificacionCodeudor(String identificacionCodeudor) {
		this.identificacionCodeudor = identificacionCodeudor;
	}
	public String getNombreCompletoCodeudor() {
		return nombreCompletoCodeudor;
	}
	public void setNombreCompletoCodeudor(String nombreCompletoCodeudor) {
		this.nombreCompletoCodeudor = nombreCompletoCodeudor;
	}
	public Date getFechaNacimientoCodeudor() {
		return fechaNacimientoCodeudor;
	}
	public void setFechaNacimientoCodeudor(Date fechaNacimientoCodeudor) {
		this.fechaNacimientoCodeudor = fechaNacimientoCodeudor;
	}
	public String getIdentificacionApoderado() {
		return identificacionApoderado;
	}
	public void setIdentificacionApoderado(String identificacionApoderado) {
		this.identificacionApoderado = identificacionApoderado;
	}
	public String getNombreCompletoApoderado() {
		return nombreCompletoApoderado;
	}
	public void setNombreCompletoApoderado(String nombreCompletoApoderado) {
		this.nombreCompletoApoderado = nombreCompletoApoderado;
	}
	public Date getFechaNacimientoApoderado() {
		return fechaNacimientoApoderado;
	}
	public void setFechaNacimientoApoderado(Date fechaNacimientoApoderado) {
		this.fechaNacimientoApoderado = fechaNacimientoApoderado;
	}

	

}
