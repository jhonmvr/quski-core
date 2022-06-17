package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class ActualizarGaratiaWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8941448047901738947L;
	
	private String codigoUsuario;
	private String fechaAvaluo;
	private String numeroOperacionMadre;
	private String numeroOperacion;
	private List<GaratiaAvaluoWrapper> garantiaAvaluo;
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(String fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}
	public String getNumeroOperacionMadre() {
		return numeroOperacionMadre;
	}
	public void setNumeroOperacionMadre(String numeroOperacionMadre) {
		this.numeroOperacionMadre = numeroOperacionMadre;
	}
	public List<GaratiaAvaluoWrapper> getGarantiaAvaluo() {
		return garantiaAvaluo;
	}
	public void setGarantiaAvaluo(List<GaratiaAvaluoWrapper> garantiaAvaluo) {
		this.garantiaAvaluo = garantiaAvaluo;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	
	
}
