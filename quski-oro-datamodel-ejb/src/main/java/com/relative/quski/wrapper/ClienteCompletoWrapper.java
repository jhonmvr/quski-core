package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoTelefonoCliente;

public class ClienteCompletoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TbQoCliente cliente;
	private List<TbQoDireccionCliente> direcciones;
	private List<TbQoIngresoEgresoCliente> ingresos;
	private List<TbQoPatrimonio> patrimonios;
	private List<TbQoReferenciaPersonal> referencias;
	private List<TbQoTelefonoCliente> telefonos;
	private TbQoDatoTrabajoCliente datosTrabajo;
	private List<TbQoCuentaBancariaCliente> cuentas;

	private Boolean isSoftbank;

	
	
	
	public ClienteCompletoWrapper(TbQoCliente cliente) {
		super();
		this.cliente = cliente;
	}
	public ClienteCompletoWrapper() {
		super();
	}
	
	public TbQoCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbQoCliente cliente) {
		this.cliente = cliente;
	}
	public List<TbQoDireccionCliente> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<TbQoDireccionCliente> direcciones) {
		this.direcciones = direcciones;
	}
	public List<TbQoIngresoEgresoCliente> getIngresos() {
		return ingresos;
	}
	public void setIngresos(List<TbQoIngresoEgresoCliente> ingresos) {
		this.ingresos = ingresos;
	}
	public List<TbQoPatrimonio> getPatrimonios() {
		return patrimonios;
	}
	public void setPatrimonios(List<TbQoPatrimonio> patrimonios) {
		this.patrimonios = patrimonios;
	}
	public List<TbQoReferenciaPersonal> getReferencias() {
		return referencias;
	}
	public void setReferencias(List<TbQoReferenciaPersonal> referencias) {
		this.referencias = referencias;
	}

	public Boolean getIsSoftbank() {
		return isSoftbank;
	}

	public void setIsSoftbank(Boolean isSoftbank) {
		this.isSoftbank = isSoftbank;
	}

	public List<TbQoTelefonoCliente> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<TbQoTelefonoCliente> telefonos) {
		this.telefonos = telefonos;
	}

	public TbQoDatoTrabajoCliente getDatosTrabajo() {
		return datosTrabajo;
	}

	public void setDatosTrabajo(TbQoDatoTrabajoCliente datosTrabajo) {
		this.datosTrabajo = datosTrabajo;
	}

	public List<TbQoCuentaBancariaCliente> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<TbQoCuentaBancariaCliente> cuentas) {
		this.cuentas = cuentas;
	}

	

}
