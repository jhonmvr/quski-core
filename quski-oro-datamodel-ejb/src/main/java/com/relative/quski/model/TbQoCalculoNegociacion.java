package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_mi_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_calculo_negociacion")
public class TbQoCalculoNegociacion implements Serializable {
private static final long serialVersionUID = 1L;

@Id
@SequenceGenerator(name="TB_QO_CALCULO_NEGOCIACION_ID_GENERATOR", sequenceName="SEQ_calculo_negociacion",allocationSize=1)
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_CALCULO_NEGOCIACION_ID_GENERATOR")
	private Long id;
	@Column(name="peso_neto_estimado")
	private BigDecimal pesoNetoEstimado;
	private BigDecimal precio;
	@Column(name="estado")
	private String estado;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	

	//bi-directional many-to-one association to TbMiCliente
	@ManyToOne
	@JoinColumn(name="id_tipo_oro")
	private TbQoTipoOro tbQoTipoOro;
	@ManyToOne
	@JoinColumn(name="id_negociacion")
	private TbQoNegociacion tbQoNegociacion;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPesoNetoEstimado() {
		return pesoNetoEstimado;
	}

	public void setPesoNetoEstimado(BigDecimal pesoNetoEstimado) {
		this.pesoNetoEstimado = pesoNetoEstimado;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public TbQoTipoOro getTbQoTipoOro() {
		return tbQoTipoOro;
	}

	public void setTbQoTipoOro(TbQoTipoOro tbQoTipoOro) {
		this.tbQoTipoOro = tbQoTipoOro;
	}

	public TbQoNegociacion getTbQoNegociacion() {
		return tbQoNegociacion;
	}

	public void setTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		this.tbQoNegociacion = tbQoNegociacion;
	}


	
}
