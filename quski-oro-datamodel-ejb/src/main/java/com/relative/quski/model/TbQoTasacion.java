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
	@SequenceGenerator(name="TB_QO_TASACION_ID_GENERATOR", sequenceName="SEQ_TASACION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_TASACION_ID_GENERATOR")
	private Long id;

	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name="descuento_peso_piedra")
	private BigDecimal descuentoPesoPiedra;

	@Column(name="descuento_suelda")
	private BigDecimal descuentoSuelda;

	@Column(name="estado_joya")
	private String estadoJoya;
	

	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="numero_piezas")
	private Long numeroPiezas;

	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;

	@Column(name="peso_neto")
	private BigDecimal pesoNeto;

	@Column(name="tipo_joya")
	private String tipoJoya;

	@Column(name="valor_avaluo")
	private BigDecimal valorAvaluo;

	@Column(name="valor_comercial")
	private BigDecimal valorComercial;

	@Column(name="valor_oro")
	private BigDecimal valorOro;

	@Column(name="valor_realizacion")
	private BigDecimal valorRealizacion;

	@Column(name="tipo_oro")
	private String tipoOro;
	
	@Column(name="tiene_piedras")
	private Boolean tienePiedras;
	
	@Column(name="detalle_piedras")
	private String detallePiedras;
	
	@Column(name="numero_garantia")
	private Long numeroGarantia;
	
	@Column(name="sub_tipo_garantia")
	private String subTipoGarantia;
	
	public Long getNumeroGarantia() {
		return numeroGarantia;
	}

	public void setNumeroGarantia(Long numeroGarantia) {
		this.numeroGarantia = numeroGarantia;
	}

	public Long getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(Long numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public String getTipoGarantia() {
		return tipoGarantia;
	}

	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}

	@Column(name="numero_expediente")
	private Long numeroExpediente;
	
	@Column(name="tipo_garantia")
	private String tipoGarantia;

	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@ManyToOne
	@JoinColumn(name="id_credito_negociacion")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;
	
	
	//bi-directional many-to-one association to TbQoCreditoNegociacion
	@ManyToOne
	@JoinColumn(name="id_detalle_credito")
	private TbQoDetalleCredito tbQoDetalleCredito;
	
	
	
	public EstadoEnum getEstado() {
		return estado;
	}

	public String getSubTipoGarantia() {
		return subTipoGarantia;
	}

	public void setSubTipoGarantia(String subTipoGarantia) {
		this.subTipoGarantia = subTipoGarantia;
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

	public BigDecimal getDescuentoSuelda() {
		return this.descuentoSuelda;
	}

	public void setDescuentoSuelda(BigDecimal descuentoSuelda) {
		this.descuentoSuelda = descuentoSuelda;
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

	public Long getNumeroPiezas() {
		return this.numeroPiezas;
	}

	public void setNumeroPiezas(Long numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}

	public BigDecimal getPesoBruto() {
		return this.pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	
	public BigDecimal getPesoNeto() {
		return this.pesoNeto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
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
	
	public BigDecimal getValorComercial() {
		return this.valorComercial;
	}

	public void setValorComercial(BigDecimal valorComercial) {
		this.valorComercial = valorComercial;
	}

	public BigDecimal getValorOro() {
		return this.valorOro;
	}

	public void setValorOro(BigDecimal valorOro) {
		this.valorOro = valorOro;
	}

	public BigDecimal getValorRealizacion() {
		return this.valorRealizacion;
	}

	public void setValorRealizacion(BigDecimal valorRealizacion) {
		this.valorRealizacion = valorRealizacion;
	}

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return this.tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

	public String getTipoOro() {
		return tipoOro;
	}

	public void setTipoOro(String tipoOro) {
		this.tipoOro = tipoOro;
	}

	public Boolean getTienePiedras() {
		return tienePiedras;
	}

	public void setTienePiedras(Boolean tienePiedras) {
		this.tienePiedras = tienePiedras;
	}

	public String getDetallePiedras() {
		return detallePiedras;
	}

	public void setDetallePiedras(String detallePiedras) {
		this.detallePiedras = detallePiedras;
	}

	public TbQoDetalleCredito getTbQoDetalleCredito() {
		return tbQoDetalleCredito;
	}

	public void setTbQoDetalleCredito(TbQoDetalleCredito tbQoDetalleCredito) {
		this.tbQoDetalleCredito = tbQoDetalleCredito;
	}

}