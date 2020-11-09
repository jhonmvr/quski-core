/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoTasacion;

public class OperacionCreditoNuevoWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<TbQoExcepcion> excepciones;
	private TbQoCreditoNegociacion credito;
	private List<TbQoTasacion> joyas;
	private TbQoProceso proceso;
	private String mensaje;
	private Boolean existe;
	
	
	
	
	public OperacionCreditoNuevoWrapper(TbQoCreditoNegociacion credito) {
		super();
		if(credito != null) {
			this.credito = credito;
			this.existe = true;
		}else {
			this.credito = null;
			this.mensaje = "NO EXISTE OPERACION";
			this.existe = false;
		}
	}
	
	
	public List<TbQoExcepcion> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<TbQoExcepcion> excepciones) {
		this.excepciones = excepciones;
	}
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public List<TbQoTasacion> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbQoTasacion> joyas) {
		this.joyas = joyas;
	}
	public TbQoProceso getProceso() {
		return proceso;
	}
	public void setProceso(TbQoProceso proceso) {
		this.proceso = proceso;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Boolean getExiste() {
		return existe;
	}
	public void setExiste(Boolean existe) {
		this.existe = existe;
	}

	
}
