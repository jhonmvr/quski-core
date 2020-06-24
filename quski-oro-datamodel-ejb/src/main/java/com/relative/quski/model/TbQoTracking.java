package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_qo_tracking database table.
 * 
 */
@Entity
@Table(name="tb_qo_tracking")
public class TbQoTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TRACKING_ID_GENERATOR", sequenceName="SEQ_TRACKING", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TRACKING_ID_GENERATOR")
	private Long id;

	private String actividad;

	@Column(name="codigo_registro")
	private BigDecimal codigoRegistro;

	private String estado;
	
	private String situacion;

	@Column(name="fecha_asignacion")
	private Timestamp fechaAsignacion;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;

	@Column(name="fecha_inicio_atencion")
	private Timestamp fechaInicioAtencion;

	private String observacion;

	private String proceso;

	@Column(name="total_tiempo")
	private Time totalTiempo;

	private String usuario;

	public TbQoTracking() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActividad() {
		return this.actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public BigDecimal getCodigoRegistro() {
		return this.codigoRegistro;
	}

	public void setCodigoRegistro(BigDecimal codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaAsignacion() {
		return this.fechaAsignacion;
	}

	public void setFechaAsignacion(Timestamp fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
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

	public Timestamp getFechaInicioAtencion() {
		return this.fechaInicioAtencion;
	}

	public void setFechaInicioAtencion(Timestamp fechaInicioAtencion) {
		this.fechaInicioAtencion = fechaInicioAtencion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getProceso() {
		return this.proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Time getTotalTiempo() {
		return this.totalTiempo;
	}

	public void setTotalTiempo(Time totalTiempo) {
		this.totalTiempo = totalTiempo;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

}