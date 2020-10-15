package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
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
	private String observacion;
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name="codigo_bpm")
	private String codigoBpm;

	@Column(name="codigo_operacion_softbank")
	private String codigoOperacionSoftbank;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;

	private Long id;

	@Column(name="nombre_asesor")
	private String nombreAsesor;

	private String proceso;

	private String seccion;

	@Column(name="tiempo_transcurrido")
	private BigDecimal tiempoTranscurrido;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

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

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public String getNombreAsesor() {
		return this.nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		this.nombreAsesor = nombreAsesor;
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

	public BigDecimal getTiempoTranscurrido() {
		return this.tiempoTranscurrido;
	}

	public void setTiempoTranscurrido(BigDecimal tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}

	public String getUsuarioActualizacion() {
		return this.usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

}