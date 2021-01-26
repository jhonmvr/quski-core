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
 * The persistent class for the tb_qo_registrar_pago database table.
 * 
 */
@Entity
@Table(name="tb_qo_registrar_pago")
public class TbQoRegistrarPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_REGISTRAR_PAGO_ID_GENERATOR", sequenceName="SEQ_REGISTRAR_PAGO", initialValue = 1, allocationSize = 1) //
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_REGISTRAR_PAGO_ID_GENERATOR")
	private Long id;

	@Column(name="id_comprobante")
	private String idComprobante;

	private String cuentas;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;


	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;


	@Column(name="fecha_creacion")
	private Date fechaCreacion;


	@Column(name="fecha_pago")
	private Date fechaPago;

	@Column(name="institucion_financiera")
	private String institucionFinanciera;

	@Column(name="numero_deposito")
	private BigDecimal numeroDeposito;

	@Column(name="usuario_actualizacion")
	private String usuarioActualizacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="valor_pagado")
	private BigDecimal valorPagado;

	//bi-directional many-to-one association to TbQoClientePago
	@ManyToOne
	@JoinColumn(name="id_pago")
	private TbQoClientePago tbQoClientePago;
	
	//bi-directional many-to-one association to TbQoClientePago
	@ManyToOne
	@JoinColumn(name="id_credito")
	private TbQoCreditoNegociacion tbQoCreditoNegociacion;
	
	public TbQoRegistrarPago() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdComprobante() {
		return this.idComprobante;
	}

	public void setIdComprobante(String idComprobante) {
		this.idComprobante = idComprobante;
	}

	public String getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(String cuentas) {
		this.cuentas = cuentas;
	}

	public EstadoEnum getEstado() {
		return this.estado;
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

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getInstitucionFinanciera() {
		return this.institucionFinanciera;
	}

	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

	public BigDecimal getNumeroDeposito() {
		return this.numeroDeposito;
	}

	public void setNumeroDeposito(BigDecimal numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
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

	public BigDecimal getValorPagado() {
		return this.valorPagado;
	}

	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}

	public TbQoClientePago getTbQoClientePago() {
		return this.tbQoClientePago;
	}

	public void setTbQoClientePago(TbQoClientePago tbQoClientePago) {
		this.tbQoClientePago = tbQoClientePago;
	}

	public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
		return tbQoCreditoNegociacion;
	}

	public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
		this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
	}

}