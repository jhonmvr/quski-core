package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoReferido;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class NegociacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<TbQoTasacion> joyas;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private List<TbQoExcepcion> excepciones;
	private TbQoCreditoNegociacion credito;
	private TbQoProceso proceso;
	private String excepcionBre;
	private Long codigoExcepcionBre;
	private Boolean respuesta;
	private TbQoTelefonoCliente telefonoMovil;
	private TbQoTelefonoCliente telefonoDomicilio;
	private List<TipoOroWrapper> tipoOro;
	private TbQoReferido referedio;
	
	

	public NegociacionWrapper(Boolean respuesta) {
		super();
		this.respuesta = respuesta;
	}
	public NegociacionWrapper() {
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
	public TbQoCreditoNegociacion getCredito() {
		return credito;
	}
	public void setCredito(TbQoCreditoNegociacion credito) {
		this.credito = credito;
	}
	public Boolean getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Boolean respuesta) {
		this.respuesta = respuesta;
	}
	public String getExcepcionBre() {
		return excepcionBre;
	}
	public void setExcepcionBre(String excepcionBre) {
		this.excepcionBre = excepcionBre;
	}
	public List<TbQoTasacion> getJoyas() {
		return joyas;
	}
	public void setJoyas(List<TbQoTasacion> joyas) {
		this.joyas = joyas;
	}
	public List<TbQoExcepcion> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<TbQoExcepcion> excepciones) {
		this.excepciones = excepciones;
	}
	public TbQoProceso getProceso() {
		return proceso;
	}
	public void setProceso(TbQoProceso proceso) {
		this.proceso = proceso;
	}
	public TbQoTelefonoCliente getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(TbQoTelefonoCliente telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public TbQoTelefonoCliente getTelefonoDomicilio() {
		return telefonoDomicilio;
	}
	public void setTelefonoDomicilio(TbQoTelefonoCliente telefonoDomicilio) {
		this.telefonoDomicilio = telefonoDomicilio;
	}
	public List<TipoOroWrapper> getTipoOro() {
		return tipoOro;
	}
	public void setTipoOro(List<TipoOroWrapper> tipoOro) {
		this.tipoOro = tipoOro;
	}
	public Long getCodigoExcepcionBre() {
		return codigoExcepcionBre;
	}
	public void setCodigoExcepcionBre(Long codigoExcepcionBre) {
		this.codigoExcepcionBre = codigoExcepcionBre;
	}
	public TbQoReferido getReferedio() {
		return referedio;
	}
	public void setReferedio(TbQoReferido referedio) {
		this.referedio = referedio;
	}
}
