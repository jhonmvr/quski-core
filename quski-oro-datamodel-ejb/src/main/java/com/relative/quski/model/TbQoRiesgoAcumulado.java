package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_qo_riesgo_acumulado database table.
 * 
 */
@Entity
@Table(name="tb_qo_riesgo_acumulado")
public class TbQoRiesgoAcumulado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_RIESGO_ACUMULADO_ID_GENERATOR", sequenceName="SEG_TB_QO_RIESGO_ACUMULADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_RIESGO_ACUMULADO_ID_GENERATOR")
	private Long id;

	@Column(name="capital_cuota_atrasada")
	private BigDecimal capitalCuotaAtrasada;

	@Column(name="capital_inicial")
	private BigDecimal capitalInicial;

	@Column(name="cobertura_actual")
	private BigDecimal coberturaActual;

	@Column(name="cobertura_anterior")
	private BigDecimal coberturaAnterior;

	@Column(name="cuenta_individual")
	private String cuentaIndividual;

	private BigDecimal cuota;

	private String custodia;

	@Column(name="dias_mora")
	private BigDecimal diasMora;

	private String estado;

	@Column(name="estatus_credito")
	private BigDecimal estatusCredito;

	private String estatusmediacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_aprobacion")
	private Date fechaAprobacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final_credito")
	private Date fechaFinalCredito;

	@Column(name="gestion_cobranza")
	private String gestionCobranza;

	@Column(name="interes_cuota_atrasada")
	private BigDecimal interesCuotaAtrasada;

	private String mora;

	@Column(name="motivo_bloque")
	private String motivoBloque;

	@Column(name="n_coutas_impagadas")
	private BigDecimal nCoutasImpagadas;

	@Column(name="numero_prestamo")
	private BigDecimal numeroPrestamo;

	private BigDecimal plazo;

	private String retanqueo;

	@Column(name="saldo_capital")
	private BigDecimal saldoCapital;

	@Column(name="tipo_credito")
	private String tipoCredito;

	@Column(name="total_deuda")
	private BigDecimal totalDeuda;

	@Column(name="ult_div_pagado")
	private BigDecimal ultDivPagado;

	//bi-directional many-to-one association to TbQoCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbQoCliente tbQoCliente;

	public TbQoRiesgoAcumulado() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCapitalCuotaAtrasada() {
		return this.capitalCuotaAtrasada;
	}

	public void setCapitalCuotaAtrasada(BigDecimal capitalCuotaAtrasada) {
		this.capitalCuotaAtrasada = capitalCuotaAtrasada;
	}

	public BigDecimal getCapitalInicial() {
		return this.capitalInicial;
	}

	public void setCapitalInicial(BigDecimal capitalInicial) {
		this.capitalInicial = capitalInicial;
	}

	public BigDecimal getCoberturaActual() {
		return this.coberturaActual;
	}

	public void setCoberturaActual(BigDecimal coberturaActual) {
		this.coberturaActual = coberturaActual;
	}

	public BigDecimal getCoberturaAnterior() {
		return this.coberturaAnterior;
	}

	public void setCoberturaAnterior(BigDecimal coberturaAnterior) {
		this.coberturaAnterior = coberturaAnterior;
	}

	public String getCuentaIndividual() {
		return this.cuentaIndividual;
	}

	public void setCuentaIndividual(String cuentaIndividual) {
		this.cuentaIndividual = cuentaIndividual;
	}

	public BigDecimal getCuota() {
		return this.cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public String getCustodia() {
		return this.custodia;
	}

	public void setCustodia(String custodia) {
		this.custodia = custodia;
	}

	public BigDecimal getDiasMora() {
		return this.diasMora;
	}

	public void setDiasMora(BigDecimal diasMora) {
		this.diasMora = diasMora;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getEstatusCredito() {
		return this.estatusCredito;
	}

	public void setEstatusCredito(BigDecimal estatusCredito) {
		this.estatusCredito = estatusCredito;
	}

	public String getEstatusmediacion() {
		return this.estatusmediacion;
	}

	public void setEstatusmediacion(String estatusmediacion) {
		this.estatusmediacion = estatusmediacion;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaAprobacion() {
		return this.fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaFinalCredito() {
		return this.fechaFinalCredito;
	}

	public void setFechaFinalCredito(Date fechaFinalCredito) {
		this.fechaFinalCredito = fechaFinalCredito;
	}

	public String getGestionCobranza() {
		return this.gestionCobranza;
	}

	public void setGestionCobranza(String gestionCobranza) {
		this.gestionCobranza = gestionCobranza;
	}

	public BigDecimal getInteresCuotaAtrasada() {
		return this.interesCuotaAtrasada;
	}

	public void setInteresCuotaAtrasada(BigDecimal interesCuotaAtrasada) {
		this.interesCuotaAtrasada = interesCuotaAtrasada;
	}

	public String getMora() {
		return this.mora;
	}

	public void setMora(String mora) {
		this.mora = mora;
	}

	public String getMotivoBloque() {
		return this.motivoBloque;
	}

	public void setMotivoBloque(String motivoBloque) {
		this.motivoBloque = motivoBloque;
	}

	public BigDecimal getNCoutasImpagadas() {
		return this.nCoutasImpagadas;
	}

	public void setNCoutasImpagadas(BigDecimal nCoutasImpagadas) {
		this.nCoutasImpagadas = nCoutasImpagadas;
	}

	public BigDecimal getNumeroPrestamo() {
		return this.numeroPrestamo;
	}

	public void setNumeroPrestamo(BigDecimal numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public BigDecimal getPlazo() {
		return this.plazo;
	}

	public void setPlazo(BigDecimal plazo) {
		this.plazo = plazo;
	}

	public String getRetanqueo() {
		return this.retanqueo;
	}

	public void setRetanqueo(String retanqueo) {
		this.retanqueo = retanqueo;
	}

	public BigDecimal getSaldoCapital() {
		return this.saldoCapital;
	}

	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public String getTipoCredito() {
		return this.tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public BigDecimal getTotalDeuda() {
		return this.totalDeuda;
	}

	public void setTotalDeuda(BigDecimal totalDeuda) {
		this.totalDeuda = totalDeuda;
	}

	public BigDecimal getUltDivPagado() {
		return this.ultDivPagado;
	}

	public void setUltDivPagado(BigDecimal ultDivPagado) {
		this.ultDivPagado = ultDivPagado;
	}

	public TbQoCliente getTbQoCliente() {
		return this.tbQoCliente;
	}

	public void setTbQoCliente(TbQoCliente tbQoCliente) {
		this.tbQoCliente = tbQoCliente;
	}

}