/**
 * 
 */
package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoVariablesCrediticia;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class AprobacionWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<TbQoTasacion> joyas;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private List<TbQoReferenciaPersonal> referencias;
	private List<TbQoIngresoEgresoCliente>ingresosEgresos;
	private List<TbQoDocumentoHabilitante>habilitantes;
	private List <TbQoDireccionCliente>direcciones;
	private List <TbQoPatrimonio> patrimonios;
	private TbQoCliente cliente;
	private TbQoCreditoNegociacion credito;
	private TbQoProceso proceso;
	private String mensaje;
	private List<TbQoExcepcion> excepciones;

	public AprobacionWrapper() {
		super();
	}
	
	
	public List<TbQoExcepcion> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<TbQoExcepcion> excepciones) {
		this.excepciones = excepciones;
	}

	public List<TbQoTasacion> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbQoTasacion> joyas) {
		this.joyas = joyas;
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
	public List<TbQoReferenciaPersonal> getReferencias() {
		return referencias;
	}
	public void setReferencias(List<TbQoReferenciaPersonal> referencias) {
		this.referencias = referencias;
	}
	public List<TbQoIngresoEgresoCliente> getIngresosEgresos() {
		return ingresosEgresos;
	}
	public void setIngresosEgresos(List<TbQoIngresoEgresoCliente> ingresosEgresos) {
		this.ingresosEgresos = ingresosEgresos;
	}
	public List<TbQoDocumentoHabilitante> getHabilitantes() {
		return habilitantes;
	}
	public void setHabilitantes(List<TbQoDocumentoHabilitante> habilitantes) {
		this.habilitantes = habilitantes;
	}
	public List<TbQoDireccionCliente> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<TbQoDireccionCliente> direcciones) {
		this.direcciones = direcciones;
	}
	public List<TbQoPatrimonio> getPatrimonios() {
		return patrimonios;
	}
	public void setPatrimonios(List<TbQoPatrimonio> patrimonios) {
		this.patrimonios = patrimonios;
	}
	public TbQoCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbQoCliente cliente) {
		this.cliente = cliente;
	}
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
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
}
