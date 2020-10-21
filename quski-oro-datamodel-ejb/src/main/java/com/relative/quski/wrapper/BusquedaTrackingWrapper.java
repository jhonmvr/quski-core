package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;

public class BusquedaTrackingWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8521675590542981249L;
	private ActividadEnum actividad;
	private ProcesoEnum proceso;
	private SeccionEnum seccion;
	private EstadoEnum estado;
	private String codigoBpm;
	private String codigoOperacionSoftbank;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	
	public ActividadEnum getActividad() {
		return actividad;
	}
	public void setActividad(ActividadEnum actividad) {
		this.actividad = actividad;
	}
	public ProcesoEnum getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoEnum proceso) {
		this.proceso = proceso;
	}
	public SeccionEnum getSeccion() {
		return seccion;
	}
	public void setSeccion(SeccionEnum seccion) {
		this.seccion = seccion;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
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
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	
}
