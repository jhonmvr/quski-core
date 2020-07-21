package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoExcepcionEnum;

import java.util.Date;


/**
 * The persistent class for the tb_qo_excepciones database table.
 * 
 */
@Entity
@Table(name="tb_qo_excepciones")
public class TbQoExcepcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_EXCEPCIONE_ID_GENERATOR", sequenceName = "SEQ_EXCEPCIONE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_EXCEPCIONE_ID_GENERATOR")
	private Long id; 
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado_excepcion")
	private EstadoExcepcionEnum estadoExcepcion;


	@Column(name="id_aprobador")
	private Long idAprobador;

	@Column(name="id_asesor")
	private Long idAsesor;

	@Column(name="tipo_excepcion")
	private String tipoExcepcion;
	
	@Column(name="observacion_asesor")
	private String observacionAsesor;
	
	@Column(name="observacion_aprobador")
	private String observacionAprobador;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	//bi-directional many-to-one association to TbQoNegociacion
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;

	public TbQoExcepcione() {
	}

	public EstadoEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public EstadoExcepcionEnum getEstadoExcepcion() {
		return this.estadoExcepcion;
	}

	public void setEstadoExcepcion(EstadoExcepcionEnum estadoExcepcion) {
		this.estadoExcepcion = estadoExcepcion;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAprobador() {
		return this.idAprobador;
	}

	public void setIdAprobador(Long idAprobador) {
		this.idAprobador = idAprobador;
	}

	public Long getIdAsesor() {
		return this.idAsesor;
	}

	public void setIdAsesor(Long idAsesor) {
		this.idAsesor = idAsesor;
	}

	public String getTipoExcepcion() {
		return this.tipoExcepcion;
	}

	public void setTipoExcepcion(String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
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

	public String getObservacionAsesor() {
		return this.observacionAsesor;
	}

	public void setObservacionAsesor(String observacionAsesor) {
		this.observacionAsesor = observacionAsesor;
	}

	public String getObservacionAprobador() {
		return this.observacionAprobador;
	}

	public void setObservacionAprobador(String observacionAprobador) {
		this.observacionAprobador = observacionAprobador;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}
}