package com.relative.quski.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_qo_variables_crediticias database table.
 * 
 */
@Entity
@Table(name = "tb_qo_variables_crediticias")
public class TbQoVariablesCrediticia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_VARIABLES_CREDITICIAS_ID_GENERATOR", sequenceName = "SEG_TB_QO_VARIABLES_CREDITICIAS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_VARIABLES_CREDITICIAS_ID_GENERATOR")
	private Long id;
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	private String nombre;

	private String orden;

	private String valor;

	// bi-directional many-to-one association to TbQoCotizador

	@ManyToOne
	@JoinColumn(name = "id_cotizador")
	private TbQoCotizador tbQoCotizador;

	// bi-directional many-to-one association to TbQoNegociacion

	@ManyToOne
	@JoinColumn(name = "id_negociacion")
	private TbQoNegociacion tbQoNegociacion;

	public TbQoVariablesCrediticia() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TbQoCotizador getTbQoCotizador() {
		return this.tbQoCotizador;
	}

	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return this.tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}

	@Override
	public String toString() {
		return "TbQoVariablesCrediticia [id=" + id + ", estado=" + estado + ", fechaActualizacion=" + fechaActualizacion
				+ ", fechaCreacion=" + fechaCreacion + ", nombre=" + nombre + ", orden=" + orden + ", valor=" + valor
				+ ", tbQoCotizador=" + tbQoCotizador + ", tbQoNegociacion=" + tbQoNegociacion + "]";
	}

}