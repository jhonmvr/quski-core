package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.enums.ProcesoEnum;

public class TrackingWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private ProcesoEnum proceso;
	private String actividad;
	private String seccion;
	private String codigoBpm;
	private String codigoOperacionSoftbank;
	private String usuarioCreacion;
	//private String nombreAsesor;
	private Date fechaCreacion;
	private Date fechaDesde;
	private Date fechaHasta;

	public TrackingWrapper() {
	

	}
/*
	public String getNombreAsesor() {
		return nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
	}
*/
	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public ProcesoEnum getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getCodigoBpm() {
		return codigoBpm;
	}

	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}

	public String getCodigoOperacionSoftbank() {
		return codigoOperacionSoftbank;
	}

	public void setCodigoOperacionSoftbank(String codigoOperacionSoftbank) {
		this.codigoOperacionSoftbank = codigoOperacionSoftbank;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
