package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_qo_tracking database table.
 * 
 */
@Entity
@Table(name="tb_qo_tracking")
public class TbQoTracking implements Serializable {
	private static final long serialVersionUID = 1L;
	public TbQoTracking() {}

	@Id
	@SequenceGenerator(name="TB_QO_TRACKING_ID_GENERATOR", sequenceName="TB_QO_TRACKING")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TRACKING_ID_GENERATOR")
	private Long id;

	private String actividad;

	@Column(name="codigo_registro")
	private BigDecimal codigoRegistro;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_asignacion")
	private Date fechaAsignacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio_atencion")
	private Date fechaInicioAtencion;

	private String observacion;

	private String proceso;

	@Temporal(TemporalType.TIME)
	@Column(name="total_tiempo")
	private Date tiempoTotal;

	private String usuario;

	

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

	public Date getFechaAsignacion() {
		return this.fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaInicioAtencion() {
		return this.fechaInicioAtencion;
	}

	public void setFechaInicioAtencion(Date fechaInicioAtencion) {
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

	public Date getTiempoTotal() {
		return this.tiempoTotal;
	}

	public void setTiempoTotal(Date tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}