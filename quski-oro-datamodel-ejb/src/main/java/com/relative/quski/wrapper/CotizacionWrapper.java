package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class CotizacionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<TbQoTasacion> joyas;
	private List<TbQoVariablesCrediticia> variables;
	private List<TbQoRiesgoAcumulado> riesgos;
	private TbQoDetalleCredito creditoCotizacion;
	private TbQoTelefonoCliente telefonoMovil;
	private TbQoTelefonoCliente telefonoDomicilio;
	private List<TipoOroWrapper> tipoOro;
	private String excepcionBre;
	
	
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
	public TbQoDetalleCredito getCreditoCotizacion() {
		return creditoCotizacion;
	}
	public void setCreditoCotizacion(TbQoDetalleCredito creditoCotizacion) {
		this.creditoCotizacion = creditoCotizacion;
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
	public String getExcepcionBre() {
		return excepcionBre;
	}
	public void setExcepcionBre(String excepcionBre) {
		this.excepcionBre = excepcionBre;
	}
	
}
