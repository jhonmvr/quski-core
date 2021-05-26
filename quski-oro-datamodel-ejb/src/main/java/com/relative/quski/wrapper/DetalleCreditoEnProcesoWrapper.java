/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.model.TbQoVariablesCrediticia;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class DetalleCreditoEnProcesoWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private TbQoCreditoNegociacion credito;
	private TbQoProceso proceso;
	private List<TbQoTelefonoCliente> telefonos;
	private List<TbQoExcepcion> excepciones;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private List<TbQoTasacion> joyas;
	private Boolean existeError;
	private String mensaje;
	
	
	
	public DetalleCreditoEnProcesoWrapper(Boolean existeError) {
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
		}
	}
	
	public List<TbQoTelefonoCliente> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TbQoTelefonoCliente> telefonos) {
		this.telefonos = telefonos;
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
