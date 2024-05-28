/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.*;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class AprobacionWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private TbQoCreditoNegociacion credito;
	private TbQoProceso proceso;
	private List<TbQoTelefonoCliente> telefonos;
	private List<TbQoDireccionCliente> direcciones;
	private List<TbQoDatoTrabajoCliente> trabajos;
	private List<TbQoReferenciaPersonal> referencias;
	private List<TbQoExcepcion> excepciones;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private List<TbQoTasacion> joyas;
	private TbQoCuentaBancariaCliente cuenta;
	private Boolean existeError;
	private String mensaje;

	private TbQoExcepcionOperativa excepcion;
	
	
	
	public AprobacionWrapper(Boolean existeError) {
		super();
		this.existeError = existeError;
	}
	
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;			
		if( credito == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTE CREDITO";
		}
	}
	
	public TbQoProceso getProceso() {
		return proceso;
	}
	public void setProceso(TbQoProceso proceso) {
		this.proceso = proceso;
		if( proceso == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTE PROCESO";
		}else if( proceso.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION){
			this.existeError = true;
			this.mensaje = "EL CREDITO SE ENCUENTRA EN ESTADO: ".concat( proceso.getEstadoProceso().toString() );
		}
	}
	
	public List<TbQoTelefonoCliente> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TbQoTelefonoCliente> telefonos) {
		this.telefonos = telefonos;
	}
	public List<TbQoDireccionCliente> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<TbQoDireccionCliente> direcciones) {
		this.direcciones = direcciones;
		if( direcciones == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTEN DIRECCIONES";
		}
	}
	public List<TbQoDatoTrabajoCliente> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(List<TbQoDatoTrabajoCliente> trabajos) {
		this.trabajos = trabajos;
		if( trabajos == null) {
			this.mensaje = "NO EXISTEN TRABAJOS";
		}
	}
	public List<TbQoReferenciaPersonal> getReferencias() {
		return referencias;
	}
	public void setReferencias(List<TbQoReferenciaPersonal> referencias) {
		this.referencias = referencias;
		if( referencias == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTEN REFERENCIAS";
		}
	}
	public List<TbQoExcepcion> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<TbQoExcepcion> excepciones) {
		this.excepciones = excepciones;
	}
	public List<TbQoVariablesCrediticia> getVariables() {
		return variables;
	}
	public void setVariables(List<TbQoVariablesCrediticia> variables) {
		this.variables = variables;
		if( variables == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTEN VARIABLES CREDITICIAS";
		}
	}
	public List<TbQoRiesgoAcumulado> getRiesgos() {
		return riesgos;
	}
	public void setRiesgos(List<TbQoRiesgoAcumulado> riesgos) {
		this.riesgos = riesgos;
	}
	public List<TbQoTasacion> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbQoTasacion> joyas) {
		this.joyas = joyas;
		if( joyas == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTEN JOYAS";
		}
	}
	public TbQoCuentaBancariaCliente getCuenta() {
		return cuenta;
	}
	public void setCuentas(TbQoCuentaBancariaCliente cuenta) {
		this.cuenta = cuenta;
		if( cuenta == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTE CUENTA BANCARIA";
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

	public TbQoExcepcionOperativa getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(TbQoExcepcionOperativa excepcion) {
		this.excepcion = excepcion;
	}
}
