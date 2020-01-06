package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_qo_precio_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_precio_oro")
@NamedQuery(name="TbQoPrecioOro.findAll", query="SELECT t FROM TbQoPrecioOro t")
public class TbQoPrecioOro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_PRECIO_ORO_ID_GENERATOR", sequenceName="SEQ_PRECIO_ORO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_PRECIO_ORO_ID_GENERATOR")
	private Long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="peso_neto_estimado")
	private BigDecimal pesoNetoEstimado;

	private BigDecimal precio;

	//bi-directional many-to-one association to TbQoCotizador
	@ManyToOne
	@JoinColumn(name="id_cotizador")
	private TbQoCotizador tbQoCotizador;

	//bi-directional many-to-one association to TbQoTipoOro
	@ManyToOne
	@JoinColumn(name="id_tipo_oro")
	private TbQoTipoOro tbQoTipoOro;

	public TbQoPrecioOro() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TbQoTipoOro getTbQoTipoOro() {
		return this.tbQoTipoOro;
	}

	public void setTbQoTipoOro(TbQoTipoOro tbQoTipoOro) {
		this.tbQoTipoOro = tbQoTipoOro;
	}

}