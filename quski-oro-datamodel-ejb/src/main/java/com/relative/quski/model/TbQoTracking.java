package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_qo_tracking database table.
 * 
 */
@Entity
@Table(name="tb_qo_tracking")
@NamedQuery(name="TbQoTracking.findAll", query="SELECT t FROM TbQoTracking t")
public class TbQoTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String actividad;

	@Column(name="codigo_bpm")
	private String codigoBpm;

	@Column(name="codigo_operacion_softbank")
	private String codigoOperacionSoftbank;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;

	private Long id;

	private String proceso;

	private String seccion;

	@Column(name="tiempo_transcurrido")
	private String tiempoTranscurrido;

	@Column(name="usuario_ejecutor")
	private String usuarioEjecutor;

	public TbQoTracking() {
	}

	public String getActividad() {
		return this.actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getCodigoBpm() {
		return this.codigoBpm;
	}

	public void setCodigoBpm(String codigoBpm) {
		this.codigoBpm = codigoBpm;
	}

	public String getCodigoOperacionSoftbank() {
		return this.codigoOperacionSoftbank;
	}

	public void setCodigoOperacionSoftbank(String codigoOperacionSoftbank) {
		this.codigoOperacionSoftbank = codigoOperacionSoftbank;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProceso() {
		return this.proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getTiempoTranscurrido() {
		return this.tiempoTranscurrido;
	}

	public void setTiempoTranscurrido(String tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}

	public String getUsuarioEjecutor() {
		return this.usuarioEjecutor;
	}

	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}

}