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

import com.relative.quski.enums.EstadoEnum;


/**
 * The persistent class for the tb_qo_tasacion database table.
 * 
 */
@Entity
@Table(name="tb_qo_tasacion")
public class TbQoTasacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_TASACION_ID_GENERATOR", sequenceName="SEG_TB_QO_TASACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TASACION_ID_GENERATOR")
	private Long id;

	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="descuento_peso_piedra")
	private BigDecimal descuentoPesoPiedra;

	@Column(name="descuento_peso_piedra_retasacion")
	private BigDecimal descuentoPesoPiedraRetasacion;
	

	@Column(name="descuento_suelda")
	private BigDecimal descuentoSuelda;

	@Column(name="descuento_suelda_retasacion")
	private BigDecimal descuentoSueldaRetasacion;

	@Column(name="estado_joya")
	private String estadoJoya;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="numero_piezas")
	private Integer numeroPiezas;

	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;

	@Column(name="peso_bruto_retasacion")
	private BigDecimal pesoBrutoRetasacion;

	@Column(name="peso_neto")
	private BigDecimal pesoNeto;

	@Column(name="peso_neto_retasacion")
	private BigDecimal pesoNetoRetasacion;

	@Column(name="tipo_joya")
	private String tipoJoya;

	@Column(name="valor_avaluo")
	private BigDecimal valorAvaluo;

	@Column(name="valor_avaluo_retasacion")
	private BigDecimal valorAvaluoRetasacion;

	@Column(name="valor_comercial")
	private BigDecimal valorComercial;

	@Column(name="valor_comercial_retasacion")
	private BigDecimal valorComercialRetasacion;

	@Column(name="valor_oro")
	private BigDecimal valorOro;

	@Column(name="valor_oro_retasacion")
	private BigDecimal valorOroRetasacion;

	@Column(name="valor_realizacion")
	private BigDecimal valorRealizacion;

	@Column(name="valor_realizacion_retasacion")
	private BigDecimal valorRealizacionRetasacion;
	
	@Column(name="tipo_oro")
	private String tipoOro;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@ManyToOne
	@JoinColumn(name="id_credito_negociacion")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;


	@ManyToOne
	@JoinColumn(name="id_funda")
	private TbQoFunda tbQoFunda;
	
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public TbQoTasacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDescuentoPesoPiedra() {
		return this.descuentoPesoPiedra;
	}

	public void setDescuentoPesoPiedra(BigDecimal descuentoPesoPiedra) {
		this.descuentoPesoPiedra = descuentoPesoPiedra;
	}

	public BigDecimal getDescuentoPesoPiedraRetasacion() {
		return this.descuentoPesoPiedraRetasacion;
	}

	public void setDescuentoPesoPiedraRetasacion(BigDecimal descuentoPesoPiedraRetasacion) {
		this.descuentoPesoPiedraRetasacion = descuentoPesoPiedraRetasacion;
	}

	public BigDecimal getDescuentoSuelda() {
		return this.descuentoSuelda;
	}

	public void setDescuentoSuelda(BigDecimal descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
	}

	public BigDecimal getDescuentoSueldaRetasacion() {
		return this.descuentoSueldaRetasacion;
	}

	public void setDescuentoSueldaRetasacion(BigDecimal descuentoSueldaRetasacion) {
		this.descuentoSueldaRetasacion = descuentoSueldaRetasacion;
	}

	public String getEstadoJoya() {
		return this.estadoJoya;
	}

	public void setEstadoJoya(String estadoJoya) {
		this.estadoJoya = estadoJoya;
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

	public Integer getNumeroPiezas() {
		return this.numeroPiezas;
	}

	public void setNumeroPiezas(Integer numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}

	public BigDecimal getPesoBruto() {
		return this.pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoBrutoRetasacion() {
		return this.pesoBrutoRetasacion;
	}

	public void setPesoBrutoRetasacion(BigDecimal pesoBrutoRetasacion) {
		this.pesoBrutoRetasacion = pesoBrutoRetasacion;
	}

	public BigDecimal getPesoNeto() {
		return this.pesoNeto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public BigDecimal getPesoNetoRetasacion() {
		return this.pesoNetoRetasacion;
	}

	public void setPesoNetoRetasacion(BigDecimal pesoNetoRetasacion) {
		this.pesoNetoRetasacion = pesoNetoRetasacion;
	}

	public String getTipoJoya() {
		return this.tipoJoya;
	}

	public void setTipoJoya(String tipoJoya) {
		this.tipoJoya = tipoJoya;
	}

	public BigDecimal getValorAvaluo() {
		return this.valorAvaluo;
	}

	public void setValorAvaluo(BigDecimal valorAvaluo) {
		this.valorAvaluo = valorAvaluo;
	}

	public BigDecimal getValorAvaluoRetasacion() {
		return this.valorAvaluoRetasacion;
	}

	public void setValorAvaluoRetasacion(BigDecimal valorAvaluoRetasacion) {
		this.valorAvaluoRetasacion = valorAvaluoRetasacion;
	}

	public BigDecimal getValorComercial() {
		return this.valorComercial;
	}

	public void setValorComercial(BigDecimal valorComercial) {
		this.valorComercial = valorComercial;
	}

	public BigDecimal getValorComercialRetasacion() {
		return this.valorComercialRetasacion;
	}

	public void setValorComercialRetasacion(BigDecimal valorComercialRetasacion) {
		this.valorComercialRetasacion = valorComercialRetasacion;
	}

	public BigDecimal getValorOro() {
		return this.valorOro;
	}

	public void setValorOro(BigDecimal valorOro) {
		this.valorOro = valorOro;
	}

	public BigDecimal getValorOroRetasacion() {
		return this.valorOroRetasacion;
	}

	public void setValorOroRetasacion(BigDecimal valorOroRetasacion) {
		this.valorOroRetasacion = valorOroRetasacion;
	}

	public BigDecimal getValorRealizacion() {
		return this.valorRealizacion;
	}

	public void setValorRealizacion(BigDecimal valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}

	public BigDecimal getValorRealizacionRetasacion() {
		return this.valorRealizacionRetasacion;
	}

	public void setValorRealizacionRetasacion(BigDecimal valorRealizacionRetasacion) {
		this.valorRealizacionRetasacion = valorRealizacionRetasacion;
	}

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return this.tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

	public TbQoFunda getTbQoFunda() {
		return tbQoFunda;
	}

	public void setTbQoFunda(TbQoFunda tbQoFunda) {
		this.tbQoFunda = tbQoFunda;
	}

	public String getTipoOro() {
		return tipoOro;
	}

	public void setTipoOro(String tipoOro) {
		this.tipoOro = tipoOro;
	}


	
	
	
	

}