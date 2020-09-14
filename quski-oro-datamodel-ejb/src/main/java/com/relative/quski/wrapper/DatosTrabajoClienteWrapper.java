package com.relative.quski.wrapper;

import java.io.Serializable;

public class DatosTrabajoClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2431772836728135381L;
	private Long id;
	private Long idActividadEconomica;
	private String codigoActividadEconomicaClienteMupi;
	private Boolean esRelacionDependencia;
	private String codigoOrigenIngreso;
	private String codigoOcupacion;
	private String cargo;
	private Boolean esPrincipal;
	private Boolean activo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdActividadEconomica() {
		return idActividadEconomica;
	}
	public void setIdActividadEconomica(Long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}
	public String getCodigoActividadEconomicaClienteMupi() {
		return codigoActividadEconomicaClienteMupi;
	}
	public void setCodigoActividadEconomicaClienteMupi(String codigoActividadEconomicaClienteMupi) {
		this.codigoActividadEconomicaClienteMupi = codigoActividadEconomicaClienteMupi;
	}
	public Boolean getEsRelacionDependencia() {
		return esRelacionDependencia;
	}
	public void setEsRelacionDependencia(Boolean esRelacionDependencia) {
		this.esRelacionDependencia = esRelacionDependencia;
	}
	public String getCodigoOrigenIngreso() {
		return codigoOrigenIngreso;
	}
	public void setCodigoOrigenIngreso(String codigoOrigenIngreso) {
		this.codigoOrigenIngreso = codigoOrigenIngreso;
	}
	public String getCodigoOcupacion() {
		return codigoOcupacion;
	}
	public void setCodigoOcupacion(String codigoOcupacion) {
		this.codigoOcupacion = codigoOcupacion;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Boolean getEsPrincipal() {
		return esPrincipal;
	}
	public void setEsPrincipal(Boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
