/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class AprobacionNovacionWrapper implements Serializable {
	
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
	private List<TbQoRegistrarPago> pagos;
	private DetalleCreditoWrapper creditoAnterior;
	private Boolean existeError;
	private String mensaje;
	
	
	
	public AprobacionNovacionWrapper(Boolean existeError) {
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
		if( telefonos == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTEN TELEFONOS";
		}
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
			this.existeError = true;
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

	public List<TbQoRegistrarPago> getPagos() {
		return pagos;
	}

	public void setPagos(List<TbQoRegistrarPago> pagos) {
		this.pagos = pagos;
		if( pagos == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTE PAGOS";
		}
	}

	public DetalleCreditoWrapper getCreditoAnterior() {
		return creditoAnterior;
	}

	public void setCreditoAnterior(DetalleCreditoWrapper creditoAnterior) {
		this.creditoAnterior = creditoAnterior;
		if( creditoAnterior == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTE CREDITO ANTERIOR";
		}
	}

	public void setCuenta(TbQoCuentaBancariaCliente cuenta) {
		this.cuenta = cuenta;
		if( cuenta == null) {
			this.existeError = true;
			this.mensaje = "NO EXISTE CUENTA BANCARIA";
		}
	}


}
