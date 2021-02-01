package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_qo_precio_oro database table.
 * 
 */
@Entity
@Table(name = "tb_qo_precio_oro")
public class TbQoPrecioOro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_PRECIO_ORO_ID_GENERATOR", sequenceName = "SEQ_PRECIO_ORO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_PRECIO_ORO_ID_GENERATOR")
	private Long id;
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "peso_neto_estimado")
	private BigDecimal pesoNetoEstimado;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "precio")
	private BigDecimal precio;

	// bi-directional many-to-one association to TbQoCotizador
	@ManyToOne
	@JoinColumn(name = "id_cotizador")
	private TbQoCotizador tbQoCotizador;

	@JoinColumn(name = "tipo_oro")
	private String tipoOro;

	public TbQoPrecioOro() {
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

	public BigDecimal getPesoNetoEstimado() {
		return this.pesoNetoEstimado;
	}

	public void setPesoNetoEstimado(BigDecimal pesoNetoEstimado) {
		this.pesoNetoEstimado = pesoNetoEstimado;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public TbQoCotizador getTbQoCotizador() {
		return this.tbQoCotizador;
	}

	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

	public String getTipoOro() {
		return this.tipoOro;
	}

	public void setTipoOro(String tipoOro) {
		this.tipoOro = tipoOro;
	}

}