package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoTelefonoCliente;

public class ClienteCompletoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String MENSAJE_CLIENTE = "NO EXISTE CLIENTE";
	private static final String MENSAJE_DIRECCION = "NO EXISTEN DIRECCIONES";
	private static final String MENSAJE_REFERENCIA = "NO EXISTEN REFENCIAS";
	private static final String MENSAJE_TELEFONO = "NO EXISTEN TELEFONOS";
	private static final String MENSAJE_TRABAJO = "NO EXISTE TRABAJO CLIENTE";
	private static final String MENSAJE_CUENTA = "NO EXISTEN CUENTAS";

	
	private TbQoCliente cliente;
	private List<TbQoDireccionCliente> direcciones;
	private List<TbQoReferenciaPersonal> referencias;
	private List<TbQoTelefonoCliente> telefonos;
	private TbQoDatoTrabajoCliente datosTrabajo;
	private List<TbQoCuentaBancariaCliente> cuentas;

	private Boolean isSoftbank;
	private Boolean existeError;
	private String mensaje;


	
	
	
	public ClienteCompletoWrapper(TbQoCliente cliente) {
		super();
		this.setCliente( cliente );
	}
	public ClienteCompletoWrapper( TbQoCliente cliente, List<TbQoDireccionCliente> direcciones,
			List<TbQoReferenciaPersonal> referencias, List<TbQoTelefonoCliente> telefonos,
			TbQoDatoTrabajoCliente datosTrabajo, List<TbQoCuentaBancariaCliente> cuentas) {
		super();
		this.setCliente( cliente );
		this.setDirecciones(direcciones);
		this.setReferencias(referencias);
		this.setTelefonos(telefonos);
		this.setDatosTrabajo(datosTrabajo);
		this.setCuentas(cuentas);
	}
	public ClienteCompletoWrapper() {
		super();
	}
	
	public TbQoCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbQoCliente cliente) {
		this.cliente = cliente;
		if(cliente == null) {
			this.existeError = true;
			this.mensaje = ClienteCompletoWrapper.MENSAJE_CLIENTE;
		}else {
			this.existeError = false;
		}
	}
	public List<TbQoDireccionCliente> getDirecciones() {
		return direcciones;
		
	}
	public void setDirecciones(List<TbQoDireccionCliente> direcciones) {
		this.direcciones = direcciones;
		if(direcciones == null) {
			this.mensaje = ClienteCompletoWrapper.MENSAJE_DIRECCION;
		}
	}
	public List<TbQoReferenciaPersonal> getReferencias() {
		return referencias;
	}
	public void setReferencias(List<TbQoReferenciaPersonal> referencias) {
		this.referencias = referencias;
		if(referencias == null) {
			this.mensaje = ClienteCompletoWrapper.MENSAJE_REFERENCIA;
		}
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
		if(telefonos == null) {
			this.mensaje = ClienteCompletoWrapper.MENSAJE_TELEFONO;
		}
	}

	public TbQoDatoTrabajoCliente getDatosTrabajo() {
		return datosTrabajo;
	}

	public void setDatosTrabajo(TbQoDatoTrabajoCliente datosTrabajo) {
		this.datosTrabajo = datosTrabajo;
		if(datosTrabajo == null) {
			this.mensaje = ClienteCompletoWrapper.MENSAJE_TRABAJO;
		}
	}

	public List<TbQoCuentaBancariaCliente> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<TbQoCuentaBancariaCliente> cuentas) {
		this.cuentas = cuentas;
		if(cuentas == null) {
			this.mensaje = ClienteCompletoWrapper.MENSAJE_CUENTA;
		}
	}
	public Boolean getExisteError() {
		return existeError;
	}
	public void setExisteError(Boolean existeError) {
		this.existeError = existeError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	

}
