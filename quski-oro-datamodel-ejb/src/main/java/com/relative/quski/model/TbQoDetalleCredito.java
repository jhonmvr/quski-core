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
 * The persistent class for the tb_qo_detalle_credito database table.
 * 
 */
@Entity
@Table(name="tb_qo_detalle_credito")
@NamedQuery(name="TbQoDetalleCredito.findAll", query="SELECT t FROM TbQoDetalleCredito t")
public class TbQoDetalleCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_DETALLE_CREDITO_ID_GENERATOR", sequenceName="SEQ_DETALLE_CREDITO" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_DETALLE_CREDITO_ID_GENERATOR")
	private Long id;

	@Column(name="costo_credito")
	private BigDecimal costoCredito;

	@Column(name="costo_custodia")
	private BigDecimal costoCustodia;

	@Column(name="costo_estimado")
	private BigDecimal costoEstimado;

	@Column(name="costo_nueva_operacion")
	private BigDecimal costoNuevaOperacion;

	@Column(name="costo_resguardado")
	private BigDecimal costoResguardado;

	@Column(name="costo_seguro")
	private BigDecimal costoSeguro;

	@Column(name="costo_transporte")
	private BigDecimal costoTransporte;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="monto_preaprobado")
	private BigDecimal montoPreaprobado;

	@Column(name="plazo_credito")
	private String plazoCredito;

	@Column(name="recibir_cliente")
	private BigDecimal recibirCliente;

	@Column(name="valor_cuota")
	private BigDecimal valorCuota;

	//bi-directional many-to-one association to TbQoCotizador
	@ManyToOne
	@JoinColumn(name="id_cotizador")
	private TbQoCotizador tbQoCotizador;

	public TbQoDetalleCredito() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCostoCredito() {
		return this.costoCredito;
	}

	public void setCostoCredito(BigDecimal costoCredito) {
		this.costoCredito = costoCredito;
	}

	public BigDecimal getCostoCustodia() {
		return this.costoCustodia;
	}

	public void setCostoCustodia(BigDecimal costoCustodia) {
		this.costoCustodia = costoCustodia;
	}

	public BigDecimal getCostoEstimado() {
		return this.costoEstimado;
	}

	public void setCostoEstimado(BigDecimal costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public BigDecimal getCostoNuevaOperacion() {
		return this.costoNuevaOperacion;
	}

	public void setCostoNuevaOperacion(BigDecimal costoNuevaOperacion) {
		this.costoNuevaOperacion = costoNuevaOperacion;
	}

	public BigDecimal getCostoResguardado() {
		return this.costoResguardado;
	}

	public void setCostoResguardado(BigDecimal costoResguardado) {
		this.costoResguardado = costoResguardado;
	}

	public BigDecimal getCostoSeguro() {
		return this.costoSeguro;
	}

	public void setCostoSeguro(BigDecimal costoSeguro) {
		this.costoSeguro = costoSeguro;
	}

	public BigDecimal getCostoTransporte() {
		return this.costoTransporte;
	}

	public void setCostoTransporte(BigDecimal costoTransporte) {
		this.costoTransporte = costoTransporte;
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

	public BigDecimal getMontoPreaprobado() {
		return this.montoPreaprobado;
	}

	public void setMontoPreaprobado(BigDecimal montoPreaprobado) {
		this.montoPreaprobado = montoPreaprobado;
	}

	public String getPlazoCredito() {
		return this.plazoCredito;
	}

	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public BigDecimal getRecibirCliente() {
		return this.recibirCliente;
	}

	public void setRecibirCliente(BigDecimal recibirCliente) {
		this.recibirCliente = recibirCliente;
	}

	public BigDecimal getValorCuota() {
		return this.valorCuota;
	}

	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	public TbQoCotizador getTbQoCotizador() {
		return this.tbQoCotizador;
	}

	public void setTbQoCotizador(TbQoCotizador tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

}