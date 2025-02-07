package com.relative.quski.wrapper;

import java.io.Serializable;

public class DatosRegistroWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fecha;
	private String referencia;
	private String codigoUsuario;
	private String canalNegociacion;
	private Long idAgencia;
	
	public DatosRegistroWrapper(String codigoUsuario, Long idAgencia, String fecha,String canalNegociacion,String referencia) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.idAgencia = idAgencia;
		this.fecha = fecha;
		this.canalNegociacion = canalNegociacion;
		this.referencia = referencia;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getCanalNegociacion() {
		return canalNegociacion;
	}
	public void setCanalNegociacion(String canalNegociacion) {
		this.canalNegociacion = canalNegociacion;
	}
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	
}
